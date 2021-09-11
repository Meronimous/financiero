package com.efip.financiero.entities

import javax.persistence.*

@Entity
class AdministrativeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null

    var codOrganismo:Int? = null
    var numero:Int? = null
    var YearExpediente:Int? =null

}