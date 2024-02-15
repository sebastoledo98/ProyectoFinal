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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("detalles")//ruta para acceder a la clase
public class ServicioDetalle {
    
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionDetalles gDetalles;

    @POST//especificamos que el servicio es de tipo POST
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para guardar el objeto
    public Response crear(Detalle detalle){
    	System.out.println(detalle);
        try{
            gDetalles.guardarDetalle(detalle);//imprimimos el objeto que recibimos
            ErrorMessage error = new ErrorMessage(1, "OK");//creamos el objeto de respuesta
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(Response.Status.OK)
                .entity(error)
                .build();
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @PUT//especificamos que el servicio es de tipo PUT
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para actualizar el objeto
    public Response actualizar(Detalle detalle){
        try{
            gDetalles.guardarDetalle(detalle);//mandamos a actualizar el objeto
            return Response.ok(detalle).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @DELETE//especificamos que el servicio es de tipo DELETE
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para eliminar el objeto
    public String borrar(@QueryParam("id") int codigo){
        try{
            gDetalles.borrarDetalle(codigo);//mandamos a eliminar el objeto
            return "OK";//respondemos con un OK
        }catch (Exception e) {
            return "Error";//respondemos con un Error
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Path("list")//ruta para acceder al servicio
    public Response getDetalle(){
    	System.out.println("Listando Detalles");
    	List<Detalle> detalles = gDetalles.getDetalles();//obtenemos todos los objetos guardados
    	if(detalles.size() > 0)//verificamos que existan objetos en la base
            return Response.ok(detalles).build();//retornamos un OK y con el objeto del carro encontrado
    	ErrorMessage error = new ErrorMessage(6, "No se registran detalles");//creamos el objeto de respuesta
        //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
