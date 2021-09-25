package com.contaduria.movimientofinanciero.dto

import com.contaduria.movimientofinanciero.entities.FundRequest
import com.contaduria.movimientofinanciero.entities.User
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
    open var id: Long = 0

    var movementCode:Long = 0

    var description:String = ""

    lateinit var date: LocalDate

    var certificateNumber:Int = 0

    var numOrder:Int = 0

    lateinit var fundRequest: FundRequestDto

    var orderCod:Int = 0

    var fundClass:Int = 0

    var imputationCode:Int = 0

    var movementAmount: BigDecimal = BigDecimal.ZERO

    lateinit var user: UserDto

}