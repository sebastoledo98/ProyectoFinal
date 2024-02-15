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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("categorias")//ruta para acceder a la clase
public class ServicioCategoria {
    
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionCategorias gCategorias;

    @POST//especificamos que el servicio es de tipo POST
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para guardar el objeto
    public Response crear(Categoria categoria){
    	System.out.println(categoria);//imprimimos el objeto que recibimos
        try{
            gCategorias.guardarCategoria(categoria);//mandamos a guardar el objeto
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
    public Response actualizar(Categoria categoria){
        try{
            gCategorias.guardarCategoria(categoria);//mandamos a actualizar el objeto
            return Response.ok(categoria).build();//retornamos un OK y con el objeto del carro encontrado
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
            gCategorias.borrarCategoria(codigo);//mandamos a eliminar el objeto
            return "OK";//respondemos con un OK
        }catch (Exception e) {
            return "Error";//respondemos con un Error
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Path("list")//ruta para acceder al servicio
    //metodo para listar todos los objetos guardados
    public Response getCategoria(){
    	System.out.println("Listando Categorias");
    	List<Categoria> categorias = gCategorias.getCategorias();//obtenemos todos los objetos guardados
    	if(categorias.size() > 0)//verificamos que existan objetos
            return Response.ok(categorias).build();//retornamos un OK y con la lista de categorias
    	ErrorMessage error = new ErrorMessage(6, "No se registran categorias");//creamos el objeto de respuesta
        //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para buscar la categoria mediante el id
    public Response leer(@QueryParam("codigo") int codigo){
        try{
            System.out.println("Codigo categoria: " + codigo);
            Categoria categoria = gCategorias.getCategoriaPorCodigo(codigo);//obtenemos la categoria
            return Response.ok(categoria).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(4, "Categoria no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
}
