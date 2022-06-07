package space.dastyar.hibernatereactive.integration


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import space.dastyar.hibernatereactive.model.Student


internal class StudentEndPointsIT:BaseIT() {

    @BeforeEach
    fun setUp(){
        // can be used to populate the DB
    }

    @AfterEach
    fun tearDown(){
        // can be used to clear the DB
    }

    @Test
    fun listStudents(){
        val response = rest.getForEntity("/students", Array<Student>::class.java)
        val students = response.body
        assertEquals(3, students?.size);
    }

    @Test
    fun getFirstStudent(){
        val response = rest.getForEntity("/students/1", Student::class.java)
        val student = response.body
        assertEquals(1, student?.id);
    }

    @Test
    fun addStudent(){
        val student = Student().apply {
            name="test"
            age=30
            grade=2
        }
        val response = rest.postForEntity("/students",student ,Student::class.java)
        val location = response.headers.location
        assertEquals(201, response.statusCodeValue);

        val checkResponse = rest.getForEntity(location, Student::class.java)
        val createdStudent = checkResponse.body
        assertEquals(student,createdStudent)
    }


}