package com.efip.financiero

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController {
    @PostMapping("/person")
     @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody person: Person) : Person {
        return person
    }
}