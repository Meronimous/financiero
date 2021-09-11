package com.efip.financiero.entities

import javax.persistence.*

@Entity
class FundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    var numero:Int? = null
    var year: Int? =null
}