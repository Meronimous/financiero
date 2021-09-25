package com.contaduria.movimientofinanciero.entities

import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository
import javax.validation.ConstraintViolationException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureTestDatabase
internal class AdministrativeDocumentTest {
    var administrativeDocument:AdministrativeDocument = AdministrativeDocument()

    @Autowired
    lateinit var administrativeDocumentRepository: AdministrativeDocumentRepository

    @BeforeEach
    fun setUp() {
        administrativeDocument.id = 0
        administrativeDocument.codOrganism = 3080
        administrativeDocument.Description = " anjkbsdb"
        administrativeDocument.year = 2020
        administrativeDocument.number = 123
    }

    @AfterEach
    fun tearDown() {
        this.administrativeDocumentRepository.deleteAll()
    }
    //save
    @Test
    internal fun ShouldSaveEntity(){
        var administrativeDocumentRetrieved:AdministrativeDocument = this.administrativeDocumentRepository.save(administrativeDocument)
        assertThat(administrativeDocumentRetrieved)
            .hasFieldOrProperty("id")
            .hasFieldOrPropertyWithValue("number",administrativeDocument.number)
            .hasFieldOrPropertyWithValue("codOrganism",administrativeDocument.codOrganism)
            .hasFieldOrPropertyWithValue("year",administrativeDocument.year)
    }
    //findById
    @Test
    internal fun ShouldfindEntityById(){
        var administrativeDocumentRetrieved:AdministrativeDocument =this.administrativeDocumentRepository.save(administrativeDocument)
        this.administrativeDocumentRepository.findById(administrativeDocumentRetrieved.id).get()
        assertThat(administrativeDocumentRetrieved)
            .hasFieldOrProperty("id")
            .hasFieldOrPropertyWithValue("numero",administrativeDocument.number)
            .hasFieldOrPropertyWithValue("codOrganismo",administrativeDocument.codOrganism)
            .hasFieldOrPropertyWithValue("year",administrativeDocument.year)

    }
    //delete
    @Test
    internal fun ShouldDeleteEntity(){

        var administrativeDocumentRetrieved:AdministrativeDocument =this.administrativeDocumentRepository.save(administrativeDocument)
        this.administrativeDocumentRepository.deleteById(administrativeDocumentRetrieved.id)
    }
    //DeleteAll

//number
@Test
internal fun ShouldThrowExceptionIfNumberIsNegative() {
    administrativeDocument.number= -1
    assertThrows(ConstraintViolationException::class.java) {
        this.administrativeDocumentRepository.save(administrativeDocument)
    }
}
//codOrganism
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsNegative() {
        administrativeDocument.codOrganism= -1000
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
}
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsLessThanFourDigits() {
        administrativeDocument.codOrganism= 999
assertThrows(ConstraintViolationException::class.java) {
    this.administrativeDocumentRepository.save(administrativeDocument)
}
    }

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsMoreThanFourDigits() {
        administrativeDocument.codOrganism= 10001
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
    }

//    Year
@Test
internal fun ShouldThrowExceptionIfYearIsBelowRange() {
    administrativeDocument.year= 2015
    assertThrows(ConstraintViolationException::class.java) {
        this.administrativeDocumentRepository.save(administrativeDocument)
    }
}

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsAboveRange() {
        administrativeDocument.year= 2100
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
    }

}