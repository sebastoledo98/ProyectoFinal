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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("usuarios")
public class ServicioUsuario {
    
    @Inject
    private GestionUsuarios gUsuarios;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Usuario usuario){
    	System.out.println(usuario);
        try{
            gUsuarios.guardarUsuario(usuario);
            ErrorMessage error = new ErrorMessage(1, "OK");
            //return Response.ok(usuario).build();
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
    public Response actualizar(Usuario usuario){
        try{
            gUsuarios.guardarUsuario(usuario);
            return Response.ok(usuario).build();
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
            gUsuarios.borrarUsuario(codigo);
            return "OK";
        }catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("usr")String usuario, @QueryParam("pass") String password){
        try{
            System.out.println("Login");
            System.out.println("usuario: " + usuario + ", password = " + password);
            Usuario cli = gUsuarios.login(usuario, password);
            return Response.ok(cli).build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Usuario no existe");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getUsuario(){
    	System.out.println("Listando Usuarios");
    	List<Usuario> usuarios = gUsuarios.getUsuarios();
    	if(usuarios.size() > 0)
            return Response.ok(usuarios).build();
    	
    	ErrorMessage error = new ErrorMessage(6, "No se registran usuarios");
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(error)
            .build();
    }

}
