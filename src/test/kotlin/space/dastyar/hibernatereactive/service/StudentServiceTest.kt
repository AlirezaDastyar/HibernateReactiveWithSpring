package space.dastyar.hibernatereactive.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.helpers.test.AssertSubscriber
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import space.dastyar.hibernatereactive.model.Student
import space.dastyar.hibernatereactive.persistence.StudentDAO


@ExtendWith(SpringExtension::class)
internal class StudentServiceTest {
    companion object {
        fun sampleStudents(): List<Student> {
            val student = Student().apply {
                name = "Test student"
                age = 11
                grade = 10
            };
            return listOf(student, student.copy(grade = 1), student.copy(grade = 4))
        }
    }

    @MockK
    private lateinit var dao: StudentDAO;

    @InjectMockKs
    private lateinit var students: StudentService;

    @Test
    fun getAll() {
        val list = Companion.sampleStudents()
        every { dao.getAll() } returns Multi.createFrom().items(list.stream())
        val multi = students.getAll();
        val subscriber = multi.subscribe().withSubscriber(AssertSubscriber.create(10))
        subscriber.assertCompleted()
            .assertItems(*list.toTypedArray())
    }

    @Test
    fun getAllNoStudent() {
        every { dao.getAll() } returns Multi.createFrom().items()
        val multi = students.getAll();
        val subscriber = multi.subscribe().withSubscriber(AssertSubscriber.create(0))
        subscriber.assertCompleted()
            .assertItems()
    }

    @Test
    fun add() {
        val student = Companion.sampleStudents().first()
        every { dao.get(any()) } returns Uni.createFrom().item(student)
        val uni = students.get(1);
        val subscriber = uni.subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.assertCompleted()
            .assertItem(student)
    }

    @Test
    fun update() {
        val student = Companion.sampleStudents().first()
        every { dao.update(any()) } returns Uni.createFrom().item(student)
        val uni = students.update(student);
        val subscriber = uni.subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.assertCompleted()
            .assertItem(student)
    }

    @Test
    fun delete() {
        val student = Companion.sampleStudents().first()
        every { dao.delete(any()) } returns Uni.createFrom().item(student)
        val uni = students.delete(1);
        val subscriber = uni.subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.assertCompleted()
            .assertItem(student)
    }

    @Test
    fun get() {
        val student = Companion.sampleStudents().first()
        every { dao.get(any()) } returns Uni.createFrom().item(student)
        val uni = students.get(1);
        val subscriber = uni.subscribe().withSubscriber(UniAssertSubscriber.create())
        subscriber.assertCompleted()
            .assertItem(student)
    }


}