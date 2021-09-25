package com.contaduria.movimientofinanciero.controllers;

import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.specifications.AdministrativeDocumentSpecification;
import com.contaduria.movimientofinanciero.specifications.FundRequestSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;

@Api(value = "Pedidos de Fondos", tags = "Pedidos de Fondos")
public interface FundRequestController {

    @ApiOperation(value = "Crear una Pedido de Fondos.")
    public ResponseEntity<FundRequestDto> create(@Valid @RequestBody FundRequestDto fundRequestDto) throws Exception;

    @ApiOperation(value = "Buscar una Pedido de Fondos.")
    public ResponseEntity<FundRequestDto> findById(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Editar una Pedido de Fondos.")
    public ResponseEntity<FundRequestDto> edit(@PathVariable Long id, @Valid  @RequestBody FundRequestDto fundRequestDto) throws Exception;

    @ApiOperation(value = "Borrar una Pedido de Fondos.")
    public ResponseEntity<FundRequestDto> deleteById(@PathVariable Long id) throws Exception;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Número de página (0..N).", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", dataType = "integer", paramType = "query", value = "Criterio de ordenamiento en formato: propiedades[ ,asc | ,desc ].\n"
                    + "Orden por defecto es ID ASC.\n"
                    + "Ordenamiento con múltiples criterios es soportado.", defaultValue = "id,asc")})
    @ApiOperation(value = "Obtener todos las Pedidos de Fondos.")
    public HashMap<String, Object> findAll(FundRequestSpecification spec,@ApiIgnore @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable);
}