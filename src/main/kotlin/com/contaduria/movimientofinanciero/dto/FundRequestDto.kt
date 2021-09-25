package com.contaduria.movimientofinanciero.dto

import com.contaduria.movimientofinanciero.entities.AdministrativeDocument
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDate

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class FundRequestDto {
    var id: Long = 0

    var number:Int = 0

    var year: Int = 0

    var organismCode:Int =0

    lateinit var date: LocalDate

    lateinit var administrativeDocument: AdministrativeDocumentDto
}