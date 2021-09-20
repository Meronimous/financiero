package com.contaduria.movimientofinanciero.entities

import lombok.*
import org.jetbrains.annotations.NotNull
import javax.persistence.*
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Positive


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
class AdministrativeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @NotNull
    @Range(min=1000,max=9999)
    var codOrganismo:Int = 0
    @NotNull
    @Positive
    var numero:Int = 0
    @NotNull
    @Range(min=2016,max=2099)
    var yearExpediente:Int =0

}