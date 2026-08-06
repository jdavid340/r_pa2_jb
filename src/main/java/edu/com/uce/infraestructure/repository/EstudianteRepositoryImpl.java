package edu.com.uce.infraestructure.repository;

import edu.com.uce.domain.model.Estudiante;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class EstudianteRepositoryImpl implements PanacheRepositoryBase<Estudiante, Integer> {

    @Inject
    private EntityManager entityManager;

    public Estudiante buscarPorCedula(String cedula) {
        TypedQuery<Estudiante> query = this.entityManager.createQuery(
                "SELECT e FROM Estudiante e WHERE e.cedula = :cedula", Estudiante.class);
        query.setParameter("cedula", cedula);
        return query.getSingleResult();
    }
}