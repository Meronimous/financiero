package com.contaduria.movimientofinanciero.entities

import lombok.*
import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Positive

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
class FundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0
    @Positive(message="el número debe ser positivo mayor a cero")
    var number:Int = 0
    @Range(min=2016,max=2099)
    var year: Int = 0
    @Range(min=1000,max=9999)
    var organismCode:Int =0
    lateinit var date: LocalDate
    @OneToOne
    @JoinColumn(name = "administrative_document_id")
    lateinit var administrativeDocument:AdministrativeDocument
//Pedidos de Fondos
}