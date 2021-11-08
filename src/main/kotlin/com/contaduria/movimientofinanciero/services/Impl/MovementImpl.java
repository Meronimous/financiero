package com.contaduria.movimientofinanciero.services.impl;

import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import com.contaduria.movimientofinanciero.entities.Movement;
import com.contaduria.movimientofinanciero.exceptions.NotEnoughFundsException;
import com.contaduria.movimientofinanciero.exceptions.ValidationException;
import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository;
import com.contaduria.movimientofinanciero.repositories.FundRequestRepository;
import com.contaduria.movimientofinanciero.repositories.MovementRepository;
import com.contaduria.movimientofinanciero.repositories.UserRepository;
import com.contaduria.movimientofinanciero.services.FundRequestService;
import com.contaduria.movimientofinanciero.services.MovementService;
import com.contaduria.movimientofinanciero.services.ConvertService;
import com.contaduria.movimientofinanciero.specifications.MovementSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

@Service
public class MovementImpl implements MovementService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AdministrativeDocumentRepository administrativeDocumentRepository;

    @Autowired
    private FundRequestRepository fundRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundRequestService fundRequestService;

    @Autowired
    private ConvertService convertService;

    @Override
    public MovementDto create(MovementDto movementDto) {
        this.logger.debug("START create(" + movementDto + ")");
        movementDto.setId(null);
        movementDto.setFundRequest(fundRequestService.findByNumberYearOrganismCode(movementDto.getFundRequest()));

        validateFunds(movementDto,false);
        return this.convertService.convertToDto(this.movementRepository.save(this.convertService.convertToEntity(movementDto)));
    }

    private void validateFunds(MovementDto movementDto,Boolean edicion){
        if (!validateMovement(movementDto,edicion)) {
            throw new NotEnoughFundsException(" El monto ingresado supera los ingresos recibidos, por favor verifique.");
        }
    }

    private boolean validateMovement(MovementDto movementDto,Boolean edicion) {
        if (movementDto.getImputationCode()==1 && !edicion ) {
            AdministrativeDocument administrativeDocument = getAdministrativeDocument(movementDto);
//RC02

            Double amountIncome = (amountSpent(administrativeDocument,3L));

            Double amountSpent = (amountSpent(administrativeDocument,1L));

            Double modifiedAmount = (movementDto.getImputationCode()==3)?movementDto.getMovementAmount().doubleValue() *-1 :movementDto.getMovementAmount().doubleValue();

            return (amountIncome >= (modifiedAmount + amountSpent));
        } else {
        if (edicion) {
            AdministrativeDocument administrativeDocument = getAdministrativeDocument(movementDto);
//RC02
            Movement movement = movementRepository.findById(movementDto.getId()).get();

            Double amountIncome = (amountSpent(administrativeDocument,3L));

            Double amountSpent = (amountSpent(administrativeDocument,1L));

            Double modifiedAmount = 0d;
            if (movement.getImputationCode() == movementDto.getImputationCode()){
                modifiedAmount = (movementDto.getImputationCode()==3)?((movement.getMovementAmount().doubleValue() - movementDto.getMovementAmount().doubleValue()))  :movementDto.getMovementAmount().doubleValue() - movement.getMovementAmount().doubleValue();
            } else {
                if (movement.getImputationCode() == 3) {
                    modifiedAmount = (movementDto.getImputationCode()==1)?(movementDto.getMovementAmount().doubleValue() *-1) + movement.getMovementAmount().doubleValue() :movementDto.getMovementAmount().doubleValue();
                }
            }


            return (amountIncome >= (modifiedAmount + amountSpent));
        }
        }
        return true;
    };

    private AdministrativeDocument getAdministrativeDocument(MovementDto movementDto) {
        return  administrativeDocumentRepository.getById(fundRequestRepository.getById(movementDto.getFundRequest().getId()).getAdministrativeDocument().getId());
    };
    private Double amountSpent(AdministrativeDocument administrativeDocument,Long imputationCode){
        return administrativeDocument.getFundRequests().stream().mapToDouble(
                fundRequest -> fundRequest.getMovements().stream().mapToDouble(
                        movement -> (movement.getImputationCode() == imputationCode) ? movement.getMovementAmount().doubleValue() : 0).sum()).sum();
    };

    @Override
    public MovementDto edit(Long id, MovementDto movementDto) {
        this.logger.debug("START edit(" + id + ", " + movementDto + ")");
        Movement movement = this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe un movimiento financiero con ID=" + id + "."));
        movementDto.setId(id);
        validateFunds(movementDto,true);
        //RF08
        if (validateMonth(movement)) {
            return this.convertService.convertToDto(this.movementRepository.save(this.convertService.convertToEntity(movementDto)));
        } else {
            throw new ValidationException("El movimiento no puede ser editado, se encuentra fuera de la fecha permitida");
        }

    }

    @Override
    public void delete(Long id) throws Exception {
        this.logger.debug("START delete(" + id + ")");
        Movement movement = this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe un movimiento financiero con ID=" + id + "."));
        validateFunds(this.convertService.convertToDto(movement),true);
        if (validateMonth(movement)) {
            this.movementRepository.deleteById(id);
        } else {
            throw new ValidationException("El movimiento no puede ser editado, se encuentra fuera de la fecha permitida");
        }
    }

    private Boolean validateMonth(Movement movement){

        return (movement.getDate().getMonth() == LocalDate.now().getMonth());
    }

    @Override
    public MovementDto findById(Long id) {
        this.logger.debug("START findById(" + id + ")");
        return this.convertService.convertToDto(this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe un movimiento financiero con ID=" + id + ".")));
    }

    @Override
    public HashMap<String, Object> findAll(MovementSpecification spec, Pageable pageable) {
        return this.convertService.convertToFormatMovement(this.movementRepository.findAll(spec, pageable));
    }

}
