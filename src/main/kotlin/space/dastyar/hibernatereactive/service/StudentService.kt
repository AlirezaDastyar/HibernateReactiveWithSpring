package space.dastyar.hibernatereactive.service

import space.dastyar.hibernatereactive.model.Student
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.springframework.stereotype.Service
import space.dastyar.hibernatereactive.persistence.DAO

@Service
class StudentService(private val dao: DAO<Student,Long>) {

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