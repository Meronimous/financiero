package com.contaduria.movimientofinanciero.entities

import com.contaduria.movimientofinanciero.repositories.FundRequestRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.ConstraintViolationException

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
    lateinit var fundRequestRepository: FundRequestRepository

    @BeforeEach
    fun setUp() {
        fundRequest.id = ID
        fundRequest.number = NUMBER
        fundRequest.year = YEAR
        fundRequest.organismCode = ORGANISMCODE
        fundRequest.date = DATE
        fundRequest.administrativeDocument = ADMINISTRATIVEDOCUMENT

    }

    @AfterEach
    fun tearDown() {
        this.fundRequestRepository.deleteAll()
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


