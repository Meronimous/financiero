package com.contaduria.movimientofinanciero

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
open class MovimientofinancieroApplication : WebMvcConfigurer {
    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(SpecificationArgumentResolver())
        argumentResolvers.add(PageableHandlerMethodArgumentResolver())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MovimientofinancieroApplication::class.java, *args)
        }
    }
}
