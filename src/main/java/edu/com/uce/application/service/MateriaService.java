package edu.com.uce.application.service;

import edu.com.uce.domain.model.*;
import edu.com.uce.infraestructure.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Transactional
public class MateriaService {

    @Inject
    private MateriaRepositoryImpl materiaRepository;

    @Inject
    private MatriculaRepositoryImpl matriculaRepository;

    public ResultadoProcesamiento registrar(List<Materia> materias) {
        long inicio = System.currentTimeMillis();
        String tipo;

        if (materias.size() <= 10) {
            tipo = "SECUENCIAL";
            for (Materia m : materias) {
                this.materiaRepository.persist(m);
            }
        } else {
            tipo = "PARALELO";
            List<CompletableFuture<Void>> tareas = new ArrayList<>();
            for (Materia m : materias) {
                CompletableFuture<Void> tarea = CompletableFuture.runAsync(() -> {
                    this.materiaRepository.persist(m);
                });
                tareas.add(tarea);
            }
            CompletableFuture.allOf(tareas.toArray(new CompletableFuture[0])).join();
        }

        long fin = System.currentTimeMillis();
        ResultadoProcesamiento resultado = new ResultadoProcesamiento();
        resultado.setRegistrosProcesados(materias.size());
        resultado.setTipoProcesamiento(tipo);
        resultado.setTiempoEjecucion(fin - inicio);
        return resultado;
    }

    public List<Estudiante> buscarEstudiantesPorCodigo(String codigo) {
        return this.matriculaRepository.buscarEstudiantesPorCodigo(codigo);
    }
}