package com.contaduria.movimientofinanciero.entities

import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository
import com.contaduria.movimientofinanciero.repositories.FundRequestRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import javax.validation.ConstraintViolationException

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureTestDatabase
internal class FundRequestTest {
    private val ID:Long = 1
    private val NUMBER:Int = 123
    private val DATE: LocalDate = LocalDate.of(2020,10,1)
    private val ORGANISMCODE:Int = 1323
    private val YEAR:Int = 2020
    private val ADMINISTRATIVEDOCUMENT:AdministrativeDocument = AdministrativeDocument()

    var fundRequest: FundRequest = FundRequest()

    @Autowired
    lateinit var administrativeDocumentRepository: AdministrativeDocumentRepository

    @Autowired
    lateinit var fundRequestRepository: FundRequestRepository


    @BeforeEach
    fun setUp() {

        ADMINISTRATIVEDOCUMENT.id = 0
        ADMINISTRATIVEDOCUMENT.codOrganism = 3080
        ADMINISTRATIVEDOCUMENT.description = " anjkbsdb"
        ADMINISTRATIVEDOCUMENT.year = 2020
        ADMINISTRATIVEDOCUMENT.number = 123

        fundRequest.id = ID
        fundRequest.number = NUMBER
        fundRequest.year = YEAR
        fundRequest.organismCode = ORGANISMCODE
        fundRequest.date = DATE
       fundRequest.administrativeDocument = this.administrativeDocumentRepository.save(ADMINISTRATIVEDOCUMENT)

    }

    @AfterEach
    fun tearDown() {
        this.fundRequestRepository.deleteAll()
        this.administrativeDocumentRepository.deleteAll()
    }

    //save
    @Test
    internal fun ShouldSaveEntity() {
        var fundRequestRetrieved: FundRequest =
            this.fundRequestRepository.save(fundRequest)
        assertThat(fundRequestRetrieved)
            .hasFieldOrProperty("id")
            .hasFieldOrPropertyWithValue("number", fundRequest.number)
            .hasFieldOrPropertyWithValue("organismCode", fundRequest.organismCode)
            .hasFieldOrPropertyWithValue("year", fundRequest.year)
    }

    //findById
    @Test
    internal fun ShouldfindEntityById() {
        var fundRequestRetrieved: FundRequest =
            this.fundRequestRepository.save(fundRequest)
        this.fundRequestRepository.findById(fundRequestRetrieved.id).get()
        assertThat(fundRequestRetrieved)
            .hasFieldOrProperty("id")
            .hasFieldOrPropertyWithValue("number", fundRequest.number)
            .hasFieldOrPropertyWithValue("organismCode", fundRequest.organismCode)
            .hasFieldOrPropertyWithValue("year", fundRequest.year)

    }

    //delete
    @Test
    internal fun ShouldDeleteEntity() {

        var fundRequestRetrieved: FundRequest =
            this.fundRequestRepository.save(fundRequest)
        this.fundRequestRepository.deleteById(fundRequestRetrieved.id)
    }
    //DeleteAll

    //number
    @Test
    internal fun ShouldThrowExceptionIfNumberIsNegative() {
        fundRequest.number= -1
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }

    //codOrganism
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsNegative() {
        fundRequest.organismCode= -1000
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsLessThanFourDigits() {
        fundRequest.organismCode= 999
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsMoreThanFourDigits() {
        fundRequest.organismCode= 10001
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }
    //    Year
    @Test
    internal fun ShouldThrowExceptionIfYearIsBelowRange() {
        fundRequest.year= 2015
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsAboveRange() {
        fundRequest.year= 2100
        assertThrows(ConstraintViolationException::class.java) {
            this.fundRequestRepository.save(fundRequest)
        }
    }

}


