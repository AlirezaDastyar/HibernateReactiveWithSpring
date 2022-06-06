package space.dastyar.hibernatereactive.persistence

import space.dastyar.hibernatereactive.model.BaseEntity
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface DAO<T, I> where T : BaseEntity<I> {
    fun save(entity:  T): Uni<T>;
    fun update(entity:  T): Uni<T>;
    fun delete(id:  I): Uni<T>;
    fun get(id:  I): Uni<T?>;
    fun getAll(): Multi<T?>;
    fun getType():Class<T>;
}