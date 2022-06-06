package space.dastyar.hibernatereactive.persistence


import space.dastyar.hibernatereactive.model.BaseEntity
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.hibernate.reactive.mutiny.Mutiny
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root



abstract class GenericDAO<T,I> (): DAO<T, I> where T: BaseEntity<I> {

    override fun save(entity:  T): Uni<T> {
        return getFactory().withSession() { session -> session.persist(entity).chain(session::flush).replaceWith(entity) }
    }

    override fun update(entity:  T): Uni<T> {
        return getFactory().withSession() { session -> session.merge(entity).chain(session::flush).replaceWith(entity) }
    }

    override fun delete(id:  I): Uni<T>{
        return getFactory().withSession() { session -> session.find(getType(),id)
                                                               .call{e->session.remove(e)}
                                                               .call{ e->session.flush()}}
    }

    override fun get(id:  I):Uni<T?>{
        return getFactory().withSession() { session -> session.find(getType(),id) }
    }

    override fun getAll(): Multi<T?>{
        val cb: CriteriaBuilder = getFactory().criteriaBuilder
        val query: CriteriaQuery<T> = cb.createQuery(getType())
        val root: Root<T> = query.from(getType())
        return this.getFactory().withSession { session -> session.createQuery(query).resultList }
            .onItem().transformToMulti { r-> Multi.createFrom().items { r.stream() } }
    }

    protected abstract fun getFactory():Mutiny.SessionFactory;
}