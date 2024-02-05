package com.web.servicios;

import com.web.gestion.GestionCategorias;
import com.web.modelos.Categoria;

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

@Path("categorias")
public class ServicioCategoria {
    
    @Inject
    private GestionCategorias gCategorias;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Categoria categoria){
    	System.out.println(categoria);
        try{
            gCategorias.guardarCategoria(categoria);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(categoria).build();
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
    public Response actualizar(Categoria categoria){
        try{
            gCategorias.guardarCategoria(categoria);
            return Response.ok(categoria).build();
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
            gCategorias.borrarCategoria(codigo);
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
            Categoria cli = gCategorias.getCategoriaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Categoria no existe");
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
            Categoria cli = gCategorias.getCategoriaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Categoria no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getCategoria(){
    	System.out.println("Listando");
    	List<Categoria> categorias = gCategorias.getCategorias();
    	if(categorias.size() > 0)
            return Response.ok(categorias).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran categorias");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
