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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("productos")//ruta para acceder a la clase
public class ServicioProducto {
    
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionProductos gProductos;

    @POST//especificamos que el servicio es de tipo POST
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para guardar el objeto
    public Response crear(Producto producto){
    	System.out.println(producto);//imprimimos el objeto que recibimos
        try{
            gProductos.guardarProducto(producto);//mandamos a guardar el objeto
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
    public Response actualizar(Producto producto){
        try{
            gProductos.guardarProducto(producto);//mandamos a actualizar el objeto
            return Response.ok(producto).build();//retornamos un OK y con el objeto del carro encontrado
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
    public String borrar(@QueryParam("id") int codigo){
        try{
            gProductos.borrarProducto(codigo);//mandamos a eliminar el objeto
            return "OK";//respondemos con un OK
        }catch (Exception e) {
            return "Error";//respondemos con un Error
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Path("list")//ruta para acceder al servicio
    //metodo para listar todos los objetos guardados
    public Response getProducto(){
    	System.out.println("Listando Productos");
    	List<Producto> productos = gProductos.getProductos();//obtenemos todos los objetos guardados
    	if(productos.size() > 0)//verificamos que existan objetos en la base
            return Response.ok(productos).build();//retornamos un OK y con el objeto del carro encontrado
    	ErrorMessage error = new ErrorMessage(6, "No se registran productos");//creamos el objeto de respuesta
        //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para obtener el producto mediante el id
    public Response leer(@QueryParam("codigo")int id){
        try{
            System.out.println("Codigo del producto: " + id);
            Producto pro = gProductos.getClientePorProducto(id);//obtenemos el producto
            return Response.ok(pro).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");//creamos el objeto de respuesta
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Path("categ")//ruta para acceder al servicio
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para obtener el producto por categoria
    public Response leerCat(@QueryParam("codigo")int id){
        try{
            System.out.println("Codigo del producto: " + id);
            List<Producto> pro = gProductos.getProductosporCategoria(id);//obtenemos los productos por categoria
            return Response.ok(pro).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(4, "Producto no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
}
