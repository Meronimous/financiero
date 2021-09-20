package com.contaduria.movimientofinanciero.entities

import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository
import javax.validation.ConstraintViolationException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase
internal class AdministrativeDocumentTest {
    var administrativeDocument:AdministrativeDocument = AdministrativeDocument()

    @Autowired
    lateinit var administrativeDocumentRepository: AdministrativeDocumentRepository
    @BeforeEach
    fun setUp() {
        administrativeDocument.id = 1
        administrativeDocument.codOrganismo = 3080
        administrativeDocument.yearExpediente = 2020
        administrativeDocument.numero = 123
    }

    @AfterEach
    fun tearDown() {
        this.administrativeDocumentRepository.deleteAll()
    }

//number
@Test
internal fun ShouldThrowExceptionIfNumberIsNegative() {
    administrativeDocument.numero= -1
    assertThrows(ConstraintViolationException::class.java) {
        this.administrativeDocumentRepository.save(administrativeDocument)
    }
}
//codOrganism
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsNegative() {
        administrativeDocument.codOrganismo= -1000
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
}
    @Test
    internal fun ShouldThrowExceptionIfOrganismIsLessThanFourDigits() {
        administrativeDocument.codOrganismo= 999
assertThrows(ConstraintViolationException::class.java) {
    this.administrativeDocumentRepository.save(administrativeDocument)
}
    }

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsMoreThanFourDigits() {
        administrativeDocument.codOrganismo= 10001
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
    }

//    Year
@Test
internal fun ShouldThrowExceptionIfYearIsBelowRange() {
    administrativeDocument.yearExpediente= 2015
    assertThrows(ConstraintViolationException::class.java) {
        this.administrativeDocumentRepository.save(administrativeDocument)
    }
}

    @Test
    internal fun ShouldThrowExceptionIfOrganismIsAboveRange() {
        administrativeDocument.yearExpediente= 2100
        assertThrows(ConstraintViolationException::class.java) {
            this.administrativeDocumentRepository.save(administrativeDocument)
        }
    }

}