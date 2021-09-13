package com.contaduria.movimientofinanciero.entities
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    var codMovimiento:Long? = null

    @OneToOne
    @JoinColumn(name = "administrative_document_id")
    var expediente:AdministrativeDocument? =null;

    var descripcion:String? =  null;

    @Temporal(TemporalType.DATE)
    var Fecha:Date? = null;

    var comprobanteNumero:Int? = null;

    var ordenNum:Int? = null;

    @OneToOne
    @JoinColumn(name = "fund_request_id")
    var pedidoFondo: FundRequest?=null ;

    var codOrden:Int? = null;

    var claseFondo:Int? = null;

    var codInputacion:Int? = null;

    var importeMovimiento: BigDecimal? = null;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    var usuario: User? = null;
}