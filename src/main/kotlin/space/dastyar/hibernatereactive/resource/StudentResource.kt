package space.dastyar.hibernatereactive.resource

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import space.dastyar.hibernatereactive.model.Student
import space.dastyar.hibernatereactive.service.StudentService
import java.net.URI

@RestController
@RequestMapping("students")
class StudentResource(val students: StudentService) {

    @GetMapping
    fun getStudents(): Multi<Student?> {
        return students.getAll();
    }

    @PostMapping
    fun addStudent(@RequestBody student: Student): Uni<ResponseEntity<Any>> {
        return students.add(student)
            .flatMap { stu -> Uni.createFrom().item(created(URI.create("/students/" + stu.id)).build()) };
    }

    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): Uni<ResponseEntity<Student?>> {
        return students.get(id).flatMap { stu -> Uni.createFrom().item(ok().body(stu)) };
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long): Uni<ResponseEntity<Any>> {
        return students.delete(id).flatMap { stu -> Uni.createFrom().item(noContent().build()) };
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody student: Student): Uni<ResponseEntity<Any>> {
        return students.update(student.apply { this.id = id }).flatMap { stu -> Uni.createFrom().item(noContent().build()) };
    }
}