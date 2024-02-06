package com.web.servicios;

import com.web.gestion.*;
import com.web.modelos.*;

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

@Path("compras")
public class ServicioTransacciones{

    @Inject
    private GestionUsuarios gUsuarios;
    @Inject
    private GestionCarros gCarros;
    @Inject
    private GestionCategorias gCategorias;
    @Inject
    private GestionDetalles gDetalles;
    @Inject
    private GestionProductos gProductos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("prod")
    public Response agregarProducto(@QueryParam("idprod") int idprod, @QueryParam("idcarro") int idcarro, @QueryParam("cantidad") int cantidad){
        try{
            Producto prod = gProductos.leerProducto(idprod);
            Carro carro = gCarros.leerCarro(idcarro);
            if(prod != null && carro != null){
                Detalle det = new Detalle();
                det.setCarro(carro);
                det.setProducto(prod);
                det.setCantidad(cantidad);
                det.setSubtotal();
                gDetalles.guardarDetalle(det);
                carro.setTotal(carro.getTotal() + det.getSubtotal());
                gCarros.actualizarCarro(carro);
                ErrorMessage error = new ErrorMessage(1, "OK");
                return Response.status(Response.Status.CREATED)
                    .entity(error)
                    .build();
            }else{
                ErrorMessage error = new ErrorMessage(99, "Carro o Producto no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
            }
        }catch (Exception e){
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("carro")
    public Response buscarCarroUsuario(@QueryParam("usuario") String usuario){
        try{
            Usuario usr = gUsuarios.getUsuario(usuario);
            Carro carro = gCarros.buscarCarroUsuario(usr.getId());
            return Response.status(Response.Status.FOUND)
                .entity(carro)
                .build();
        } catch (Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro asociado a ese usuario");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("confirmar")
    public Response confirmarCarro(@QueryParam("id") int id, @QueryParam("idusr") int idusuario){
        try{
            Carro carro = gCarros.leerCarro(id);
            carro.setEstado(true);
            gCarros.actualizarCarro(carro);
            Usuario usuario = gUsuarios.getUsuarioPorId(idusuario);
            usuario.setSaldo(usuario.getSaldo() - carro.getTotal());
            gUsuarios.actualizarUsuario(usuario);
            return Response.status(Response.Status.OK)
                .entity(usuario)
                .build();
        }catch(Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saldo")
    public Response aumentarSaldoUsuario(@QueryParam("id") int id, @QueryParam("saldo") double saldo){
        try{
            Usuario usuario = gUsuarios.getUsuarioPorId(id);
            usuario.setSaldo(usuario.getSaldo() + saldo);
            gUsuarios.actualizarUsuario(usuario);
            return Response.status(Response.Status.OK)
                .entity(usuario)
                .build();
        }catch (Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("carro")
    public Response crearCarroProducto(){
        return Response.ok().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("detalle")
    public Response borrarDetalle(@QueryParam("idproducto") int idproducto, @QueryParam("idcarro") int idcarro){
        return null;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("carro")
    public Response borrarCarro(@QueryParam("idcarro") int idcarro){
        return null;
    }
}
