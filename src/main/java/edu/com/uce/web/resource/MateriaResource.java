package edu.com.uce.web.resource;

import edu.com.uce.application.service.MateriaService;
import edu.com.uce.domain.model.Estudiante;
import edu.com.uce.domain.model.Materia;
import edu.com.uce.domain.model.ResultadoProcesamiento;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;

@Path("/materia")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @Path("/registrar")
    @POST
    public ResultadoProcesamiento registrar(List<Materia> materias) {
        return this.materiaService.registrar(materias);
    }

    @Path("/buscarEstudiantes/{codigo}")
    @GET
    public List<Estudiante> buscarEstudiantes(@PathParam("codigo") String codigo) {
        return this.materiaService.buscarEstudiantesPorCodigo(codigo);
    }
}