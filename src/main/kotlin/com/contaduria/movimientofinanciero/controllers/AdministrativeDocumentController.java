package com.contaduria.movimientofinanciero.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.specifications.AdministrativeDocumentSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Expedientes", tags = "Expedientes")
public interface AdministrativeDocumentController {

    @ApiOperation(value = "Crear una orden de entrega.")
    public ResponseEntity<AdministrativeDocumentDto> create(@Valid @RequestBody AdministrativeDocumentDto administrativeDocumentDto) throws Exception;

    @ApiOperation(value = "Buscar una orden de entrega.")
    public ResponseEntity<AdministrativeDocumentDto> findById(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Editar una orden de entrega.")
    public ResponseEntity<AdministrativeDocumentDto> edit(@PathVariable Long id, @Valid  @RequestBody AdministrativeDocumentDto administrativeDocumentDto) throws Exception;

    @ApiOperation(value = "Borrar una orden de entrega.")
    public ResponseEntity<AdministrativeDocumentDto> deleteById(@PathVariable Long id) throws Exception;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Número de página (0..N).", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", dataType = "integer", paramType = "query", value = "Criterio de ordenamiento en formato: propiedades[ ,asc | ,desc ].\n"
                    + "Orden por defecto es ID ASC.\n"
                    + "Ordenamiento con múltiples criterios es soportado.", defaultValue = "id,asc")})
    @ApiOperation(value = "Obtener todas las ordenes de entrega.")
    public HashMap<String, Object> findAll(AdministrativeDocumentSpecification spec,@ApiIgnore @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable);
}