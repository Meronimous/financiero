package com.contaduria.movimientofinanciero.services;

import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import com.contaduria.movimientofinanciero.entities.FundRequest;
import com.contaduria.movimientofinanciero.entities.Movement;
import org.springframework.data.domain.Page;

import java.util.HashMap;

public interface ConvertService {
    public AdministrativeDocument convertToEntity(AdministrativeDocumentDto administrativeDocumentDto);

    public AdministrativeDocumentDto convertToDto(AdministrativeDocument administrativeDocument);

    public HashMap<String, Object> convertToFormatAdministrativeDocument(Page<AdministrativeDocument> administrativeDocuments);

    public FundRequest convertToEntity(FundRequestDto fundRequestDto);

    public FundRequestDto convertToDto(FundRequest fundRequest);

    public HashMap<String, Object> convertToFormatFundRequest(Page<FundRequest> fundRequests);

    public Movement convertToEntity(MovementDto movementDto);

    public MovementDto convertToDto(Movement movement);

    public HashMap<String, Object> convertToFormatMovement(Page<Movement> movements);


}
