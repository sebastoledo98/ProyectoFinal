package com.web.servicios;

import com.web.gestion.GestionDetalles;
import com.web.modelos.Detalle;

import java.util.List;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("detalles")
public class ServicioDetalle {
    
    @Inject
    private GestionDetalles gDetalles;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Detalle detalle){
    	System.out.println(detalle);
        try{
            gDetalles.guardarDetalle(detalle);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(detalle).build();
            return Response.status(Response.Status.CREATED)
                .entity(error)
                .build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Detalle detalle){
        try{
            gDetalles.guardarDetalle(detalle);
            return Response.ok(detalle).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo){
        try{
            gDetalles.borrarDetalle(codigo);
            return "OK";
        }catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getDetalle(){
    	System.out.println("Listando Detalles");
    	List<Detalle> detalles = gDetalles.getDetalles();
    	if(detalles.size() > 0)
            return Response.ok(detalles).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran detalles");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
