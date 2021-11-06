package com.contaduria.movimientofinanciero.entities

import lombok.*
import org.hibernate.validator.constraints.Range
import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table( name="financialuser",uniqueConstraints = [(UniqueConstraint(columnNames = ["userDni"]))])
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @NotNull
    @Range(min=999999,max=99999999)
    var userDni:Long = 0

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    var lastName:String = ""

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    var names:String =""

    var address:String?=null

    var telephone:String?=null

    @NotNull
    @NotBlank
    @Size(min=8,max=18)
    @ToString.Exclude
    var password:String =""

//    @ManyToOne
//    @JoinColumn(name = "movement_id")
//    @ToString.Exclude
//    var movement: Movement?= null

//Usuarios
}