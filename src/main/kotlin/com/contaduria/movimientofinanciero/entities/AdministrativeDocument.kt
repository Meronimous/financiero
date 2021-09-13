package com.contaduria.movimientofinanciero.entities

import lombok.*
import javax.persistence.*

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
class AdministrativeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null

    var codOrganismo:Int? = null
    var numero:Int? = null
    var YearExpediente:Int? =null

}