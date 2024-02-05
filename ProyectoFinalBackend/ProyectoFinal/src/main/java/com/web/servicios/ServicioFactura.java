package com.web.servicios;

import com.web.gestion.GestionFacturas;
import com.web.modelos.Factura;

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

@Path("facturas")
public class ServicioFactura {
    
    @Inject
    private GestionFacturas gFacturas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Factura factura){
    	System.out.println(factura);
        try{
            gFacturas.guardarFactura(factura);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(factura).build();
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
    public Response actualizar(Factura factura){
        try{
            gFacturas.guardarFactura(factura);
            return Response.ok(factura).build();
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
            gFacturas.borrarFactura(codigo);
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
            Factura cli = gFacturas.getFacturaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Factura no existe");
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
            Factura cli = gFacturas.getFacturaPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Factura no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getFactura(){
    	System.out.println("Listando");
    	List<Factura> facturas = gFacturas.getFacturas();
    	if(facturas.size() > 0)
            return Response.ok(facturas).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran facturas");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
