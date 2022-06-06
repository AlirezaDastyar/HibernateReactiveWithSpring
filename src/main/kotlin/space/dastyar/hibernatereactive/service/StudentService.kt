package space.dastyar.hibernatereactive.service

import space.dastyar.hibernatereactive.model.Student
import space.dastyar.hibernatereactive.persistence.StudentDAO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.converters.multi.MultiReactorConverters.toFlux
import io.smallrye.mutiny.converters.uni.UniReactorConverters.toMono
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class StudentService(val dao: StudentDAO) {

    fun getAll(): Multi<Student?> {
        return dao.getAll()
    }

    fun add(student: Student): Uni<Student> {
        return dao.save(student)
    }

    fun update(student: Student): Uni<Student> {
        return dao.update(student)
    }

    fun delete(id: Long): Uni<Student> {
        return dao.delete(id)
    }

    fun get(id: Long): Uni<Student?> {
        return dao.get(id)
    }
}