package com.contaduria.movimientofinanciero.dto

import com.contaduria.movimientofinanciero.entities.FundRequest
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import lombok.*

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
class AdministrativeDocumentDto {

    var id:Long? = null
    var codOrganism:Int? = null
    var number:Int? = null
    var year:Int? = null
    var description:String? = null
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    var fundRequests:List<FundRequestDto>? = null
}