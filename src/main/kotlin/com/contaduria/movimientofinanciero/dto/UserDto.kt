package com.contaduria.movimientofinanciero.dto

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators


class UserDto {
    var id:Long = 0
    var userDni:Long = 0
    var lastName:String? = null
    var names:String? = null
    var address:String? = null
    var telephone:String?= null
    var password:String? = null

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var movementDto: MovementDto?=null

}