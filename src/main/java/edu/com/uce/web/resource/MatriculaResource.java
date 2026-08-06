package edu.com.uce.web.resource;

import edu.com.uce.application.service.MatriculaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/matricula")
public class MatriculaResource {

    @Inject
    private MatriculaService matriculaService;

    @Path("/matricular/{cedula}/{codigo}")
    @POST
    public void matricular(@PathParam("cedula") String cedula,
            @PathParam("codigo") String codigo) {
        this.matriculaService.matricular(cedula, codigo);
    }
}