package com.contaduria.movimientofinanciero.entities

import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureTestDatabase
class  MovementTest {
    var movement:Movement = Movement()

//    @Autowired
//    lateinit var movementRepository: MovementRepository
//
//    @Autowired
//    lateinit var fundRequestRepository: FundRequestRepository

    @Autowired
    lateinit var administrativeDocumentRepository: AdministrativeDocumentRepository

    private val ID:Long = 1
    private val DATE:LocalDate = LocalDate.of(2020,10,1)
    private val ORDERCOD:Int = 1
    private val FUNDCLASS:Int = 2
    private val IMPUTATIONCODE:Int = 1
    private val CERTIFICATENUMBER:Int = 123
    private val MOVEMENTAMOUNT:BigDecimal = BigDecimal.valueOf(1234.55)
    private val DESCRIPTION:String = "Descripción test"
    private val NUMORDER:Int = 123
    private val MOVEMENTCODE:Long = 645645

    private val ADMINISTRATIVEDOCUMENT:AdministrativeDocument = AdministrativeDocument()
    private val FUNDREQUEST: FundRequest = FundRequest()

    @BeforeEach
    fun setUp() {
        ADMINISTRATIVEDOCUMENT.codOrganism = 3080
        ADMINISTRATIVEDOCUMENT.Description = " anjkbsdb"
        ADMINISTRATIVEDOCUMENT.year = 2020
        ADMINISTRATIVEDOCUMENT.number = 123

        FUNDREQUEST.administrativeDocument = this.administrativeDocumentRepository.save(ADMINISTRATIVEDOCUMENT)
        FUNDREQUEST.number = 123
        FUNDREQUEST.year = 2021
        FUNDREQUEST.organismCode = 3223
        FUNDREQUEST.date = DATE

//        movement.fundRequest =this.fundRequestRepository.save(FUNDREQUEST)
        movement.id = ID
        movement.date = DATE
        movement.orderCod = ORDERCOD
        movement.fundClass = FUNDCLASS
        movement.imputationCode = IMPUTATIONCODE
        movement.certificateNumber = CERTIFICATENUMBER
        movement.movementAmount = MOVEMENTAMOUNT
        movement.description = DESCRIPTION
        movement.numOrder = NUMORDER
        movement.movementCode = MOVEMENTCODE

    }

    @AfterEach
    fun tearDown() {
//        this.movementRepository.deleteAll()
//        this.fundRequestRepository.deleteAll()
        this.administrativeDocumentRepository.deleteAll()
    }
    //save
    @Test
    internal fun ShouldSaveEntity(){
//        var movementRetrieved:Movement = this.movementRepository.save(movement)
//        Assertions.assertThat(movementRetrieved)
//            .hasFieldOrProperty("id")

    }
    //findById
    @Test
    internal fun ShouldfindEntityById(){
//        var movementRetrieved:Movement =this.movementRepository.save(movement)
//        this.movementRepository.findById(movementRetrieved.id).get()
//        Assertions.assertThat(movementRetrieved)
//            .hasFieldOrProperty("id")

    }
    //delete
    @Test
    internal fun ShouldDeleteEntity(){
//
//        var movementRetrieved:Movement =this.movementRepository.save(movement)
//        this.movementRepository.deleteById(movementRetrieved.id)
    }
    //DeleteAll
}