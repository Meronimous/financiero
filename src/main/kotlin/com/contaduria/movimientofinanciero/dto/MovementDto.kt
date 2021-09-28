package com.contaduria.movimientofinanciero.dto

import com.contaduria.movimientofinanciero.entities.FundRequest
import com.contaduria.movimientofinanciero.entities.FinancialUser
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.validator.constraints.Range
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.validation.constraints.Positive

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class MovementDto {
    var id: Long? = null

    var movementCode:Long = 0

    var description:String = ""

    lateinit var dateCertificate: LocalDate

    var certificateNumber:Int = 0

    var numOrder:Int = 0

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var fundRequest: FundRequestDto? =null

    var orderCod:Int = 0

    var fundClass:Int = 0

    var imputationCode:Int = 0

    var movementAmount: BigDecimal = BigDecimal.ZERO


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    lateinit var user: UserDto

}