package edu.com.uce.web.resource;

import edu.com.uce.application.service.EstudianteService;
import edu.com.uce.domain.model.Estudiante;
import edu.com.uce.domain.model.Materia;
import edu.com.uce.domain.model.ResultadoProcesamiento;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;

@Path("/estudiante")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Path("/registrar")
    @POST
    public ResultadoProcesamiento registrar(List<Estudiante> estudiantes) {
        return this.estudianteService.registrar(estudiantes);
    }

    @Path("/buscarMaterias/{cedula}")
    @GET
    public List<Materia> buscarMaterias(@PathParam("cedula") String cedula) {
        return this.estudianteService.buscarMateriasPorCedula(cedula);
    }
}