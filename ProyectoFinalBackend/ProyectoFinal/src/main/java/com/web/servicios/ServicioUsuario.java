package com.web.servicios;

import com.web.gestion.GestionUsuarios;
import com.web.modelos.Usuario;

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

@Path("usuarios")//ruta para acceder a la clase
public class ServicioUsuario {
    
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionUsuarios gUsuarios;

    @POST//especificamos que el servicio es de tipo POST
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Consumes(MediaType.APPLICATION_JSON)//especificamos que consume un objeto JSON
    //metodo para guardar el objeto
    public Response crear(Usuario usuario){
    	System.out.println(usuario);//imprimimos el objeto que recibimos
        try{
            gUsuarios.guardarUsuario(usuario);//mandamos a guardar el objeto
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
    public Response actualizar(Usuario usuario){
        try{
            gUsuarios.guardarUsuario(usuario);//mandamos a actualizar el objeto
            return Response.ok(usuario).build();//retornamos un OK y con el objeto del carro encontrado
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
            gUsuarios.borrarUsuario(codigo);//mandamos a eliminar el objeto
            return "OK";//respondemos con un OK
        }catch (Exception e) {
            return "Error";//respondemos con un Error
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Path("login")//ruta para acceder al servicio
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    public Response leer(@QueryParam("usr")String usuario, @QueryParam("pass") String password){
        try{
            System.out.println("Login");
            System.out.println("usuario: " + usuario + ", password = " + password);
            Usuario cli = gUsuarios.login(usuario, password);
            return Response.ok(cli).build();//retornamos un OK y con el objeto del carro encontrado
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(4, "Usuario no existe");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    /*   
    @GET
    @Path("{dni}/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer2(@PathParam("dni")String cedula,@PathParam("nombre") String nombre) {
    	try{
            System.out.println("cedula: " + cedula + ", nombre = " + nombre);
            Usuario cli = gUsuarios.getUsuarioPorCedula(cedula);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Usuario no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }
    */

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Path("list")//ruta para acceder al servicio
    //metodo para listar todos los objetos guardados
    public Response getUsuario(){
    	System.out.println("Listando Usuarios");
    	List<Usuario> usuarios = gUsuarios.getUsuarios();//obtenemos todos los objetos guardados
    	if(usuarios.size() > 0)//verificamos que existan objetos en la base
            return Response.ok(usuarios).build();//retornamos un OK y con el objeto del carro encontrado
    	ErrorMessage error = new ErrorMessage(6, "No se registran usuarios");//creamos el objeto de respuesta
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
