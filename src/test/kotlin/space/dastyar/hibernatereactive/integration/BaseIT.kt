package space.dastyar.hibernatereactive.integration


import org.hibernate.cfg.Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import space.dastyar.hibernatereactive.model.Student


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal  class BaseIT {

    companion object {
        @Container
        protected var postgresDB = PostgreSQLContainer("postgres:14")
            .withDatabaseName("app")
            .withUsername("user")
            .withPassword("password")

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            System.setProperty("jdbc.url",postgresDB.jdbcUrl)
        }
    }

    @Autowired
    protected lateinit var rest: TestRestTemplate;

}