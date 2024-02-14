package com.web.servicios;

import com.web.gestion.*;
import com.web.modelos.*;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;

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

    private Random random = new Random();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("prod")
    public Response agregarProducto(@QueryParam("producto") int idprod, @QueryParam("carro") int idcarro, @QueryParam("cantidad") int cantidad){
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
    public Response buscarCarroUsuario(@QueryParam("usuario") int idusuario){
        try{
            Usuario usr = gUsuarios.getUsuarioPorId(idusuario);
            List<Carro> carros = gCarros.buscarCarroUsuario(usr.getId());
            return Response.status(Response.Status.FOUND)
                .entity(carros)
                .build();
        } catch (Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro asociado a ese usuario");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detalle")
    public Response buscarDetalleCarro(@QueryParam("carro") int idcarro){
        try{
            List<Detalle> detalles = gDetalles.detalleCarro(idcarro);
            return Response.status(Response.Status.FOUND)
                .entity(detalles)
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
    public Response confirmarCarro(@QueryParam("carro") int idcarro, @QueryParam("usuario") int idusuario){
        try{
            Carro carro = gCarros.leerCarro(idcarro);
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("detalle")
    public Response crearCarroProducto(@QueryParam("producto") int idproducto, @QueryParam("usuario") int idusuario, @QueryParam("cantidad") int cantidad){
        try{
            Carro carro = gCarros.obtenerUltimoCarroUsuario(idusuario);
            if(carro.getEstado()){
                Detalle det = gDetalles.buscarProductoCarro(idusuario, idproducto, carro.getId());
                if(det != null){
                    det.setCantidad(det.getCantidad() + cantidad);
                    det.setSubtotal();
                    gDetalles.actualizarDetalle(det);
                    carro.setTotal(carro.getTotal() + det.getSubtotal());
                    gCarros.actualizarCarro(carro);
                }else{
                    det = new Detalle();
                    det.setCarro(carro);
                    det.setProducto(gProductos.leerProducto(idproducto));
                    det.setCantidad(cantidad);
                    det.setSubtotal();
                    gDetalles.guardarDetalle(det);
                    carro.setTotal(carro.getTotal() + det.getSubtotal());
                    gCarros.actualizarCarro(carro);
                }
            }else{
                int id = gCarros.obtenerUltimoCarro().getId() + 1;
                carro = new Carro();
                carro.setEstado(true);
                carro.setUsuario(gUsuarios.leerUsuario(idusuario));
                carro.setNumero(id + "-" + id + "-" + id);
                carro.setFecha(LocalDateTime.now());
                carro.setDescuento((double)(random.nextInt(100)/100));
                Detalle det = new Detalle();
                det.setCarro(carro);
                det.setProducto(gProductos.leerProducto(idproducto));
                det.setCantidad(cantidad);
                det.setSubtotal();
                gDetalles.guardarDetalle(det);
                carro.setTotal(carro.getTotal() + det.getSubtotal());
                gCarros.actualizarCarro(carro);
            }
            ErrorMessage error = new ErrorMessage(1, "OK");
            return Response.status(Response.Status.OK)
                .entity(error)
                .build();
        }catch(Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @DELETE
    @Path("carro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCarro(@QueryParam("carro") int idcarro, @QueryParam("usuario") int idusuario){
        try{
            Carro car = gCarros.buscarIdCarroUsuario(idusuario, idcarro);
            ErrorMessage error = new ErrorMessage(99, "No existe un usuario con ese id de carro");
            Response.Status stat = Response.Status.NOT_FOUND;
            if(car != null){
                gDetalles.borrarDetalleCarro(idcarro);
                gCarros.borrarCarro(idcarro);
                error = new ErrorMessage(1, "OK");
                stat = Response.Status.ACCEPTED;
            }
            return Response.status(stat)
                .entity(error)
                .build();
        }catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, "Error al borrar el carro: No existe el carro con ese id");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @DELETE
    @Path("detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@QueryParam("usuario") int idusuario, @QueryParam("carro") int idcarro, @QueryParam("producto") int idproducto){
        try{
            Carro carro = gCarros.buscarIdCarroUsuario(idcarro, idusuario);
            if(carro != null){
                gDetalles.borrarProductoCarro(carro.getId(), idproducto);
                ErrorMessage error = new ErrorMessage(1, "OK");
                return Response.status(Response.Status.ACCEPTED)
                    .entity(error)
                    .build();
            }
            return null;
        }catch(Exception e){
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }
}
