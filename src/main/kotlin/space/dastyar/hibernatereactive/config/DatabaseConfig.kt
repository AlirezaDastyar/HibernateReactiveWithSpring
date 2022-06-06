package space.dastyar.hibernatereactive.config

import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.persistence.Persistence




@Configuration
class DatabaseConfig {

    @Bean
    fun sessionFactory(): Mutiny.SessionFactory? {
        return Persistence.createEntityManagerFactory("app")
            .unwrap(Mutiny.SessionFactory::class.java)
    }
}