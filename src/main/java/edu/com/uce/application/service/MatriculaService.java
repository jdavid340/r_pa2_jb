package edu.com.uce.application.service;

import edu.com.uce.domain.model.Estudiante;
import edu.com.uce.domain.model.Materia;
import edu.com.uce.domain.model.Matricula;
import edu.com.uce.infraestructure.repository.EstudianteRepositoryImpl;
import edu.com.uce.infraestructure.repository.MateriaRepositoryImpl;
import edu.com.uce.infraestructure.repository.MatriculaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MatriculaService {

    @Inject
    private MatriculaRepositoryImpl matriculaRepository;

    @Inject
    private EstudianteRepositoryImpl estudianteRepository;

    @Inject
    private MateriaRepositoryImpl materiaRepository;

    public void matricular(String cedula, String codigo) {
        Estudiante estudiante = this.estudianteRepository.buscarPorCedula(cedula);
        Materia materia = this.materiaRepository.buscarPorCodigo(codigo);
        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setMateria(materia);
        this.matriculaRepository.persist(matricula);
    }
}