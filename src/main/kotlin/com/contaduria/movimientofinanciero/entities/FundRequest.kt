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
@Table( uniqueConstraints = [(UniqueConstraint(columnNames = ["number","year","organismCode"]))])
class FundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Positive(message="el número debe ser positivo mayor a cero")
    var number:Int = 0

    @Range(min=2016,max=2099)
    var year: Int = 0

    @Range(min=1000,max=9999)
    var organismCode:Int =0

    var date: LocalDate = LocalDate.now()

    @ManyToOne
    @JoinColumn(name = "administrative_document_id")
    var administrativeDocument:AdministrativeDocument? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "fundRequest")
    var movements: List<Movement>? = null
//Pedidos de Fondos
}