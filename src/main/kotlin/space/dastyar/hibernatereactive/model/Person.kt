package space.dastyar.hibernatereactive.model

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="persons")
 abstract class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id:Long? = null,
    open var name:String? = null,
    open var age: Int?=null
): BaseEntity<Long> {

}