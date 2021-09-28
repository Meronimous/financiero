package com.contaduria.movimientofinanciero.services.impl;

import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.dto.UserDto;
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import com.contaduria.movimientofinanciero.entities.FundRequest;
import com.contaduria.movimientofinanciero.entities.Movement;
import com.contaduria.movimientofinanciero.entities.FinancialUser;
import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository;
import com.contaduria.movimientofinanciero.repositories.FundRequestRepository;
import com.contaduria.movimientofinanciero.repositories.MovementRepository;
import com.contaduria.movimientofinanciero.repositories.UserRepository;
import com.contaduria.movimientofinanciero.services.ConvertService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class ConvertServiceImpl implements ConvertService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdministrativeDocumentRepository administrativeDocumentRepository;

    @Autowired
    private FundRequestRepository fundRequestRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private UserRepository userRepository;


//  <Expedientes>
    @Override
    public AdministrativeDocument convertToEntity(AdministrativeDocumentDto administrativeDocumentDto) {
        this.logger.debug("START convertToEntity(administrativeDocumentDto id={})", administrativeDocumentDto.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(administrativeDocumentDto, AdministrativeDocument.class);
    }

    @Override
    public AdministrativeDocumentDto convertToDto(AdministrativeDocument administrativeDocument) {
        this.logger.debug("START convertToDto(administrativeDocument id={})",administrativeDocument.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(administrativeDocument, AdministrativeDocumentDto.class);
    }

    @Override
    public HashMap<String, Object> convertToFormatAdministrativeDocument(Page<AdministrativeDocument> administrativeDocuments) {
        this.logger.debug("START convertToFormatAdministrativeDocument(administrativeDocuments)");
        HashMap<String, Object> map = new HashMap<>();
        map.put("metadata", getMetadataAdministrativeDocument(administrativeDocuments));
        map.put("records", administrativeDocuments.map(administrativeDocument -> this.convertToDto(administrativeDocument)).getContent());
        return map;
    }

    private HashMap<String, Object> getMetadataAdministrativeDocument(Page<AdministrativeDocument> administrativeDocuments) {
        this.logger.debug("START getMetadata( administrativeDocuments)");
        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("total", this.administrativeDocumentRepository.count());
        metadata.put("filteredCount", administrativeDocuments.getTotalElements());
        metadata.put("page", administrativeDocuments.getNumber());
        metadata.put("pageSize", administrativeDocuments.getSize());
        return metadata;
    }

//  </Expedientes>
// <Pedido de Fondos>
    @Override
    public FundRequest convertToEntity(FundRequestDto fundRequestDto) {
        this.logger.debug("START convertToEntity(FundRequestDto id={})", fundRequestDto.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(fundRequestDto, FundRequest.class);
    }

    @Override
    public FundRequestDto convertToDto(FundRequest fundRequest) {
        this.logger.debug("START convertToDto(fundRequest id={})",fundRequest.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(fundRequest, FundRequestDto.class);
    }

    @Override
    public HashMap<String, Object> convertToFormatFundRequest(Page<FundRequest> fundRequests) {
        this.logger.debug("START convertToFormatAdministrativeDocument(administrativeDocuments)");
        HashMap<String, Object> map = new HashMap<>();
        map.put("metadata", getMetadataFundRequest(fundRequests));
        map.put("records", fundRequests.map(fundRequest -> this.convertToDto(fundRequest)).getContent());
        return map;
    }

    private HashMap<String, Object> getMetadataFundRequest(Page<FundRequest> fundRequests) {
        this.logger.debug("START getMetadata( administrativeDocuments)");
        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("total", this.fundRequestRepository.count());
        metadata.put("filteredCount", fundRequests.getTotalElements());
        metadata.put("page", fundRequests.getNumber());
        metadata.put("pageSize", fundRequests.getSize());
        return metadata;
    }
    // </Pedido de Fondos>
    // <Movimientos>

    @Override
    public Movement convertToEntity(MovementDto movementDto) {
        this.logger.debug("START convertToEntity(FundRequestDto id={})", movementDto.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movementDto, Movement.class);
    }

    @Override
    public MovementDto convertToDto(Movement movement) {
        this.logger.debug("START convertToDto(fundRequest id={})",movement.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movement, MovementDto.class);
    }

    @Override
    public HashMap<String, Object> convertToFormatMovement(Page<Movement> movements) {
        this.logger.debug("START convertToFormatAdministrativeDocument(administrativeDocuments)");
        HashMap<String, Object> map = new HashMap<>();
        map.put("metadata", getMetadataMovement(movements));
        map.put("records", movements.map(movement -> this.convertToDto(movement)).getContent());
        return map;
    }

    private HashMap<String, Object> getMetadataMovement(Page<Movement> movements) {
        this.logger.debug("START getMetadata( movements)");
        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("total", this.movementRepository.count());
        metadata.put("filteredCount", movements.getTotalElements());
        metadata.put("page", movements.getNumber());
        metadata.put("pageSize", movements.getSize());
        return metadata;
    }
    // </Movimientos>

    // <Usuario>

    @Override
    public FinancialUser convertToEntity(UserDto userDto) {
        this.logger.debug("START convertToEntity(UserDto id={})", userDto.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, FinancialUser.class);
    }

    @Override
    public UserDto convertToDto(FinancialUser user) {
        this.logger.debug("START convertToDto(User id={})",user.getId());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public HashMap<String, Object> convertToFormatUser(Page<FinancialUser> users) {
        this.logger.debug("START convertToFormatAdministrativeDocument(administrativeDocuments)");
        HashMap<String, Object> map = new HashMap<>();
        map.put("metadata", getMetadataUser(users));
        map.put("records", users.map(user -> this.convertToDto(user)).getContent());
        return map;
    }

    private HashMap<String, Object> getMetadataUser(Page<FinancialUser> users) {
        this.logger.debug("START getMetadata( users)");
        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("total", this.userRepository.count());
        metadata.put("filteredCount", users.getTotalElements());
        metadata.put("page", users.getNumber());
        metadata.put("pageSize", users.getSize());
        return metadata;
    }
    // </Usuario>




}
