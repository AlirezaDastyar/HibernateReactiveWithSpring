package space.dastyar.hibernatereactive.persistence


import space.dastyar.hibernatereactive.model.Student
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.stereotype.Repository


@Repository
class StudentDAO(private var factory:Mutiny.SessionFactory) : GenericDAO<Student, Long>(){
    override fun getFactory(): Mutiny.SessionFactory {
        return factory;
    }

    override fun getType(): Class<Student> {
        return Student::class.java
    }

}