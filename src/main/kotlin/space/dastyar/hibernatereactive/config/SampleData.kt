package space.dastyar.hibernatereactive.config

import space.dastyar.hibernatereactive.model.Student
import space.dastyar.hibernatereactive.persistence.StudentDAO
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SampleData(var dao: StudentDAO) : ApplicationRunner{
    override fun run(args: ApplicationArguments?) {
        val student = Student().apply {
            name = "test 1"
            age = 20
            grade = 12
        }

        dao.save(student).subscribe().with {  }
        dao.save(student.copy().apply { name = "test 2" }).subscribe().with {  }
        dao.save(student.copy().apply { name = "test 3" }).subscribe().with {  }
    }
}