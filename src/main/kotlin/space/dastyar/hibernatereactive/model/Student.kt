package space.dastyar.hibernatereactive.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "person_id")
data class Student(
    @Column(name = "grade")
    var grade: Int? = null
) : Person() {

}
