package com.contaduria.movimientofinanciero.entities

import lombok.*
import org.hibernate.validator.constraints.Range
import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table( uniqueConstraints = [(UniqueConstraint(columnNames = ["number","year","codOrganism"]))])
class AdministrativeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @NotNull
    @Range(min=1000,max=9999)
    var codOrganism:Int = 0

    @NotNull
    @Positive(message="el número debe ser positivo mayor a cero")
    var number:Int = 0

    @NotNull
    @Range(min=2016,max=2099)
    var year:Int =0

    @NotNull
    @NotBlank(message="La descripción no debe estar vacía")
    var description:String =""

    @OneToMany(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "administrative_document_id")
    var fundRequests: List<FundRequest>? = null


//Expedientes

}