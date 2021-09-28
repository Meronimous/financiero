package com.contaduria.movimientofinanciero.dto

import com.fasterxml.jackson.annotation.*
import lombok.*
import java.time.LocalDate

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class FundRequestDto {
    var id: Long? = null

    var number:Int? = null

    var year: Int? = null

    var organismCode:Int? = null

    var date: LocalDate? = null


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var administrativeDocument: AdministrativeDocumentDto?=null
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var movements:List<MovementDto>? = null
}