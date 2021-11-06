package com.contaduria.movimientofinanciero.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.math.BigDecimal
import java.time.LocalDate

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class MovementDto {
    var id: Long? = null

    var movementCode:Long = 0

    var description:String = ""

    lateinit var date:LocalDate

    lateinit var dateCertificate: LocalDate

    var certificateNumber:Int = 0

    var numOrder:Int = 0

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var fundRequest: FundRequestDto? =null

    var orderCod:Int = 0

    var fundClass:Int = 0

    var imputationCode:Int = 0

    var movementAmount: BigDecimal = BigDecimal.ZERO


}