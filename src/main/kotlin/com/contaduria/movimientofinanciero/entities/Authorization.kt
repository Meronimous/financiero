package com.contaduria.movimientofinanciero.entities

import javax.persistence.*

@Entity
class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

}