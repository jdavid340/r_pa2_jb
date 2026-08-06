package edu.com.uce.infraestructure.repository;

import edu.com.uce.domain.model.Materia;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MateriaRepositoryImpl implements PanacheRepositoryBase<Materia, Integer> {

    @Inject
    private EntityManager entityManager;

    public Materia buscarPorCodigo(String codigo) {
        TypedQuery<Materia> query = this.entityManager.createQuery(
                "SELECT m FROM Materia m WHERE m.codigo = :codigo", Materia.class);
        query.setParameter("codigo", codigo);
        return query.getSingleResult();
    }
}