package com.web.servicios;

import com.web.gestion.GestionCarros;
import com.web.modelos.Carro;

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

@Path("carros")
public class ServicioCarro {
    
    @Inject
    private GestionCarros gCarros;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Carro carro){
    	System.out.println(carro);
        try{
            gCarros.guardarCarro(carro);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(carro).build();
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
    public Response actualizar(Carro carro){
        try{
            gCarros.guardarCarro(carro);
            return Response.ok(carro).build();
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
            gCarros.borrarCarro(codigo);
            return "OK";
        }catch (Exception e) {
            return "Error";
        }
    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("dni")String cedula, @QueryParam("nombre") String nombre){
        try{
            System.out.println("cedula: " + cedula + ", nombre = " + nombre);
            Carro cli = gCarros.getCarroPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Carro no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    
    @GET
    @Path("{dni}/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer2(@PathParam("dni")String cedula,@PathParam("nombre") String nombre) {
    	try{
            System.out.println("cedula: " + cedula + ", nombre = " + nombre);
            Carro cli = gCarros.getCarroPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Carro no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getCarro(){
    	System.out.println("Listando");
    	List<Carro> carros = gCarros.getCarros();
    	if(carros.size() > 0)
            return Response.ok(carros).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran carros");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}

