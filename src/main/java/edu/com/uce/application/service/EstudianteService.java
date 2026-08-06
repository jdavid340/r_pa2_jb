package edu.com.uce.application.service;

import edu.com.uce.domain.model.Estudiante;
import edu.com.uce.domain.model.Materia;
import edu.com.uce.domain.model.ResultadoProcesamiento;
import edu.com.uce.infraestructure.repository.EstudianteRepositoryImpl;
import edu.com.uce.infraestructure.repository.MatriculaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Transactional
public class EstudianteService {

    @Inject
    private EstudianteRepositoryImpl estudianteRepository;

    @Inject
    private MatriculaRepositoryImpl matriculaRepository;

    public ResultadoProcesamiento registrar(List<Estudiante> estudiantes) {
        long inicio = System.currentTimeMillis();
        String tipo;

        if (estudiantes.size() <= 10) {
            tipo = "SECUENCIAL";
            for (Estudiante e : estudiantes) {
                this.estudianteRepository.persist(e);
            }
        } else {
            tipo = "PARALELO";
            List<CompletableFuture<Void>> tareas = new ArrayList<>();
            for (Estudiante e : estudiantes) {
                CompletableFuture<Void> tarea = CompletableFuture.runAsync(() -> {
                    this.estudianteRepository.persist(e);
                });
                tareas.add(tarea);
            }
            CompletableFuture.allOf(tareas.toArray(new CompletableFuture[0])).join();
        }

        long fin = System.currentTimeMillis();
        ResultadoProcesamiento resultado = new ResultadoProcesamiento();
        resultado.setRegistrosProcesados(estudiantes.size());
        resultado.setTipoProcesamiento(tipo);
        resultado.setTiempoEjecucion(fin - inicio);
        return resultado;
    }

    public List<Materia> buscarMateriasPorCedula(String cedula) {
        return this.matriculaRepository.buscarMateriasPorCedula(cedula);
    }
}