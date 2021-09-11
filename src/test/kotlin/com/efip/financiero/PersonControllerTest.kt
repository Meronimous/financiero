package com.efip.financiero

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

@WebMvcTest
class PersonControllerTest(
    @Autowired
    private val mockMvc : MockMvc,
    @Autowired
    private val mapper: ObjectMapper
)
 /*{
@Test
fun `creating a person with Post returns person representation`(){
    val person = Person(1,"Arho")
    mockMvc.post("/person"){
        contentType = MediaType.APPLICATION_JSON
        content = mapper.writeValueAsString(person)
    }.andExpect {
        jsonPath("$.id") {value(1)}
        jsonPath("$.name"){value("Arho")}
    }
}
     @Test
     fun `creating a person with Post returns HTTP Created`(){
         val person = Person(1,"Arho")
         mockMvc.post("/person"){
             contentType = MediaType.APPLICATION_JSON
             content = mapper.writeValueAsString(person)
         }.andExpect {
             status { isCreated() }
         }
         }
     }*/
