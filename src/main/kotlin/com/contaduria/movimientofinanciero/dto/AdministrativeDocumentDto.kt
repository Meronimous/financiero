package com.contaduria.movimientofinanciero.dto

import lombok.*

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
class AdministrativeDocumentDto {

    var id:Long? = null
    var codOrganism:Int = 0
        var number:Int = 0
        var year:Int =0
        var description:String = ""
}