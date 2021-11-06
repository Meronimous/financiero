package com.contaduria.movimientofinanciero.services

import com.contaduria.movimientofinanciero.dto.MovementDto
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument
import com.contaduria.movimientofinanciero.entities.FundRequest
import com.contaduria.movimientofinanciero.entities.User
import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository
import com.contaduria.movimientofinanciero.repositories.FundRequestRepository
import com.contaduria.movimientofinanciero.repositories.MovementRepository
import org.assertj.core.api.Assertions.assertThat
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
import java.util.ArrayList

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureTestDatabase
class  MovementServiceTest {
    var movementDto: MovementDto = MovementDto()

    @Autowired
    lateinit var movementRepository: MovementRepository

    @Autowired
    lateinit var converService:ConvertService

    @Autowired
    lateinit var movementService: MovementService

    @Autowired
    lateinit var fundRequestRepository: FundRequestRepository



    @Autowired
    lateinit var administrativeDocumentRepository: AdministrativeDocumentRepository

    private val ID: Long = 1
    private val DATE: LocalDate = LocalDate.of(2020, 10, 1)
    private val ORDERCOD: Int = 1
    private val FUNDCLASS: Int = 2
    private val IMPUTATIONCODE: Int = 1
    private val CERTIFICATENUMBER: Int = 123
    private val MOVEMENTAMOUNT: BigDecimal = BigDecimal.valueOf(1234.55)
    private val DESCRIPTION: String = "Descripción test"
    private val NUMORDER: Int = 123
    private val MOVEMENTCODE: Long = 645645
    private val ADMINISTRATIVEDOCUMENT: AdministrativeDocument = AdministrativeDocument()
    private val FUNDREQUEST: FundRequest = FundRequest()
    private val USERDNI:Long = 12345678
    private val USERPASSWORD:String = "123456878"
    private val USERNAMES:String = "UserNames"

    private val USER:User = User()

    @BeforeEach
    fun setUp() {
        USER.userDni= USERDNI
        USER.password = USERPASSWORD
        USER.names = USERNAMES
//        USER.lastName=USERLASTNAM== 200E



        ADMINISTRATIVEDOCUMENT.codOrganism = 3080
        ADMINISTRATIVEDOCUMENT.description = " anjkbsdb"
        ADMINISTRATIVEDOCUMENT.year = 2020
        ADMINISTRATIVEDOCUMENT.number = 123
        ADMINISTRATIVEDOCUMENT.fundRequests= ArrayList()

        FUNDREQUEST.administrativeDocument = this.administrativeDocumentRepository.save(ADMINISTRATIVEDOCUMENT)
        FUNDREQUEST.number = 123
        FUNDREQUEST.year = 2021
        FUNDREQUEST.organismCode = 3223
        FUNDREQUEST.date = DATE

        movementDto.fundRequest = this.converService.convertToDto(this.fundRequestRepository.save(FUNDREQUEST))
        movementDto.id = ID
        movementDto.dateCertificate = DATE
        movementDto.orderCod = ORDERCOD
        movementDto.fundClass = FUNDCLASS
        movementDto.imputationCode = IMPUTATIONCODE
        movementDto.certificateNumber = CERTIFICATENUMBER
        movementDto.movementAmount = MOVEMENTAMOUNT
        movementDto.description = DESCRIPTION
        movementDto.numOrder = NUMORDER
        movementDto.movementCode = MOVEMENTCODE

    }

    @AfterEach
    fun tearDown() {
        this.movementRepository.deleteAll()
        this.fundRequestRepository.deleteAll()
        this.administrativeDocumentRepository.deleteAll()
    }

    //save
    @Test
    internal fun ShouldSaveEntity() {
        var movementDtoRetrieved: MovementDto = this.movementService.create(movementDto)
        assertThat(movementDtoRetrieved)
            .hasFieldOrProperty("id")

    }
}