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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("carros")//ruta para acceder a la clase
public class ServicioCarro {
    
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionCarros gCarros;

    @POST//especificamos que el servicio es de tipo POST
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para guardaer el objeto
    public Response crear(Carro carro){
    	System.out.println(carro);//imprimimos el objeto que recibimos
        try{
            gCarros.guardarCarro(carro);//mandamos a guardar el objeto
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
    public Response actualizar(Carro carro){
        try{
            gCarros.guardarCarro(carro);//mandamos a actualizar el objeto
            return Response.ok(carro).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            //capturamos el error y respondemos con el mensaje de error
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @DELETE//especificamos que el servicio es de tipo DELETE
    @Produces(MediaType.APPLICATION_JSON)
    //metodo para elminar el objeto
    public String borrar(@QueryParam("id") int codigo){
        try{
            gCarros.borrarCarro(codigo);//mandamos a eliminar el objeto
            return "OK";//respondemos con un OK
        }catch (Exception e) {
            return "Error";//respondemos con un Error
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")//ruta para acceder al servicio
    //metodo para listar todos los objetos guardados
    public Response getCarro(){
    	System.out.println("Listando Carros");
    	List<Carro> carros = gCarros.getCarros();//obtenemos todos los objetos guardados
    	if(carros.size() > 0)
            return Response.ok(carros).build();//retornamos un OK y con el objeto del carro encontrado
    	ErrorMessage error = new ErrorMessage(6, "No se registran carros");//creamos el objeto de respuesta
        //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}

