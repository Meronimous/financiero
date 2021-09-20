package com.contaduria.movimientofinanciero.entities
import lombok.*
import org.hibernate.validator.constraints.Range
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Positive

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0

    var movementCode:Long = 0


    var description:String = ""

    lateinit var date:LocalDate

    @Positive
    var certificateNumber:Int = 0

    @Positive
    var numOrder:Int = 0

    @OneToOne
    @JoinColumn(name = "fund_request_id")
    lateinit var fundRequest: FundRequest

    @Range(min=1,max=9)
    var orderCod:Int = 0
    @Range(min=1,max=9)
    var fundClass:Int = 0
    @Range(min=1,max=9)
    var imputationCode:Int = 0

    @Positive
    var movementAmount: BigDecimal = BigDecimal.ZERO

    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var user: User

    //Movimientos
}