package com.web.servicios;

import com.web.gestion.GestionProductos;
import com.web.modelos.Producto;

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

@Path("productos")
public class ServicioProducto {
    
    @Inject
    private GestionProductos gProductos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Producto producto){
    	System.out.println(producto);
        try{
            gProductos.guardarProducto(producto);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(producto).build();
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
    public Response actualizar(Producto producto){
        try{
            gProductos.guardarProducto(producto);
            return Response.ok(producto).build();
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
            gProductos.borrarProducto(codigo);
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
            Producto cli = gProductos.getProductoPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");
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
            Producto cli = gProductos.getProductoPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getProducto(){
    	System.out.println("Listando");
    	List<Producto> productos = gProductos.getProductos();
    	if(productos.size() > 0)
            return Response.ok(productos).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
