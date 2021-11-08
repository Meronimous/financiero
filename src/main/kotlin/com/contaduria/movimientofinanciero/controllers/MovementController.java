package com.contaduria.movimientofinanciero.controllers;

import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.specifications.MovementSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;

@Api(value = "Movimientos", tags = "Movimientos")
public interface MovementController {

    @ApiOperation(value = "Crear un movimiento financiero.", authorizations = { @Authorization(value = "Authorization") })
    public ResponseEntity<MovementDto> create(@Valid @RequestBody MovementDto movementDto) throws Exception;

    @ApiOperation(value = "Buscar un movimiento financiero.", authorizations = { @Authorization(value = "Authorization") })
    public ResponseEntity<MovementDto> findById(@PathVariable Long id) throws Exception;

    @ApiOperation(value = "Editar un movimiento financiero.", authorizations = { @Authorization(value = "Authorization") })
    public ResponseEntity<MovementDto> edit(@PathVariable Long id, @Valid  @RequestBody MovementDto movementDto) throws Exception;

    @ApiOperation(value = "Borrar un movimiento financiero.", authorizations = { @Authorization(value = "Authorization") })
    public ResponseEntity<MovementDto> deleteById(@PathVariable Long id) throws Exception;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Número de página (0..N).", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", dataType = "integer", paramType = "query", value = "Criterio de ordenamiento en formato: propiedades[ ,asc | ,desc ].\n"
                    + "Orden por defecto es ID ASC.\n"
                    + "Ordenamiento con múltiples criterios es soportado.", defaultValue = "id,asc"),
            @ApiImplicitParam(name = "description", dataType = "string", paramType = "query", value = "Buscar por descripción."),
            @ApiImplicitParam(name = "codeOrganism", dataType = "long", paramType = "query", value = "Buscar por código de Organismo."),
            @ApiImplicitParam(name = "number", dataType = "long", paramType = "query", value = "Buscar por número."),
            @ApiImplicitParam(name = "year", dataType = "short", paramType = "query", value = "Buscar por año del ejercicio.")})
    @ApiOperation(value = "Obtener todos los movimientos financieros.", authorizations = { @Authorization(value = "Authorization")})
    public HashMap<String, Object> findAll(MovementSpecification spec, @ApiIgnore @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable);
}