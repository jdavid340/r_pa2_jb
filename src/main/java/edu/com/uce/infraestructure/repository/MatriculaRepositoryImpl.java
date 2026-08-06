package edu.com.uce.infraestructure.repository;

import edu.com.uce.domain.model.Materia;
import edu.com.uce.domain.model.Matricula;
import edu.com.uce.domain.model.Estudiante;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class MatriculaRepositoryImpl implements PanacheRepositoryBase<Matricula, Integer> {

    @Inject
    private EntityManager entityManager;

    public List<Materia> buscarMateriasPorCedula(String cedula) {
        TypedQuery<Materia> query = this.entityManager.createQuery(
                "SELECT m.materia FROM Matricula m WHERE m.estudiante.cedula = :cedula", Materia.class);
        query.setParameter("cedula", cedula);
        return query.getResultList();
    }

    public List<Estudiante> buscarEstudiantesPorCodigo(String codigo) {
        TypedQuery<Estudiante> query = this.entityManager.createQuery(
                "SELECT m.estudiante FROM Matricula m WHERE m.materia.codigo = :codigo", Estudiante.class);
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
}