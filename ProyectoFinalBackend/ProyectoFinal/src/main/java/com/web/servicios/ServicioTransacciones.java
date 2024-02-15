package com.web.servicios;

import com.web.gestion.*;
import com.web.modelos.*;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("compras")//ruta para acceder a la clase
public class ServicioTransacciones{

    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionUsuarios gUsuarios;
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionCarros gCarros;
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionCategorias gCategorias;
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionDetalles gDetalles;
    @Inject//injectamos la clase para no necesitar inicializarla
    private GestionProductos gProductos;

    //instancia de la clase random para establecer los descuentos
    private Random random = new Random();

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//ruta para acceder al servicio
    @Path("carro")//ruta para acceder al servicio
    //metodo para buscar el carro mediante el usuario
    public Response buscarCarroUsuario(@QueryParam("usuario") int idusuario){
        try{
            Usuario usr = gUsuarios.getUsuarioPorId(idusuario);//nos aseguramos de que existe el usuario
            List<Carro> carros = gCarros.buscarCarroUsuario(usr.getId());//obtenemos la lista de carros del usuario
            //respondemos con la lista de productos
            return Response.status(Response.Status.FOUND)
                .entity(carros)
                .build();
        } catch (Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//ruta para acceder al servicio
    @Path("carroUser")//ruta para acceder al servicio
    //metodo para buscar el ultimo carro por el usuario
    public Response buscarCarroUsuarioUltimo(@QueryParam("usuario") int idusuario){
        try{
            Carro carro = gCarros.obtenerUltimoCarroUsuario(idusuario);//obtenemos el ultimo carro creado por el usuario
            if(carro.getEstado()) {//verificamos el estado del carro obtenido
                //respondemos con un OK y el carro obtenido
            	return Response.status(Response.Status.OK)
                        .entity(carro)
                        .build();
            }else {
                //Generamos un mensaje de error
            	ErrorMessage error = new ErrorMessage(99, "No existe un carro abierto");
                //devolvemos el error con el mensaje
            	return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Produces(MediaType.APPLICATION_JSON)//ruta para acceder al servicio
    @Path("detalle")//ruta para acceder al servicio
    //metodo para buscar el detalle mediante el id del carro
    public Response buscarDetalleCarro(@QueryParam("carro") int idcarro){
        try{
            List<Detalle> detalles = gDetalles.detalleCarro(idcarro);//obtenemos una lista de los detalles que pertenece al carro
            //creamos la respuesta, le asignamos el estado y la lista de los detalles del carro
            return Response.status(Response.Status.OK)
                .entity(detalles)
                .build();
        } catch (Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @PUT//especificamos que el servicio es de tipo PUT
    @Produces(MediaType.APPLICATION_JSON)//ruta para acceder al servicio
    @Path("confirmar")//ruta para acceder al servicio
    //metodo para confirmar la compra de los productos
    public Response confirmarCarro(@QueryParam("carro") int idcarro, @QueryParam("usuario") int idusuario){
        try{
            Carro carro = gCarros.leerCarro(idcarro);//obtenemos el carro mediante el id
            carro.setEstado(false);//cambiamos el estado a false, haciendo que no se puedan anadir mas productos
            gCarros.actualizarCarro(carro);//actualizamos el carro en la base
            Usuario usuario = gUsuarios.getUsuarioPorId(idusuario);//obtenemos al usuario que es dueno del carro
            usuario.setSaldo(usuario.getSaldo() - carro.getTotal());//restamos el total de la compra de la cuenta del usuario
            gUsuarios.actualizarUsuario(usuario);//actualizamos el usuario con el nuevo saldo
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(Response.Status.OK)
                .entity(carro)
                .build();
        }catch(Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @PUT//especificamos que el servicio es de tipo PUT
    @Produces(MediaType.APPLICATION_JSON)//ruta para acceder al servicio
    @Path("saldo")//ruta para acceder al servicio
    //mmetodo para aumentar el saldo del usuario
    public Response aumentarSaldoUsuario(@QueryParam("id") int id, @QueryParam("saldo") double saldo){
        try{
            Usuario usuario = gUsuarios.getUsuarioPorId(id);//obtenemos el usuario mediante el id
            usuario.setSaldo(usuario.getSaldo() + saldo);//le aumentamos la cantidad con el saldo recibido
            gUsuarios.actualizarUsuario(usuario);//actualizamos el usuario
            //creamos la respuesta, le asignamos el estado y el usuario con el nuevo saldo
            return Response.status(Response.Status.OK)
                .entity(usuario)
                .build();
        }catch (Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, "No se encuentra un carro con ese id");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @PUT//especificamos que el servicio es de tipo PUT
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    @Path("detalle")//ruta para acceder al servicio
    ////metodo para agregar un producto al carro
    public Response crearCarroProducto(@QueryParam("codigo") int idproducto, @QueryParam("usuario") int idusuario, @QueryParam("cantidad") int cantidad){
        try{
            Carro carro = gCarros.obtenerUltimoCarroUsuario(idusuario);//obtenemos el ultimo carro agregado por el usuario
            if(carro != null && carro.getEstado()){//verificamos si existe un carro y su estado
                //si existe
                Detalle det = gDetalles.buscarProductoCarro(idusuario, idproducto, carro.getId());//buscamos el detalle mediante el carro
                if(det != null){//verificamos que nos devuelva un detalle
                    //si nos devuelve un detalle
                    carro.setTotal(carro.getTotal() - det.getSubtotal());//restamos el subtotal anterior del detalle
                    det.setCantidad(det.getCantidad() + cantidad);//aumentamos la cantidad de productos
                    det.setSubtotal();//calculamos el subtotal
                    gDetalles.actualizarDetalle(det);//acuatlizamos el detalle
                    carro.setTotal(carro.getTotal() + det.getSubtotal());//aumentamos el total del carro con el nuevo subtotal del detalle modificado
                    gCarros.actualizarCarro(carro);//actualizamos el carro con el nuevo total
                }else{
                    //si el detalle no existe 
                    det = new Detalle();//creamos un nuevo detalle
                    det.setCarro(carro);//agregamos el carro del detalle
                    det.setProducto(gProductos.leerProducto(idproducto));//agregamos el producto
                    det.setCantidad(cantidad);//asignamos la cantidad del producto
                    det.setSubtotal();//calculamos el subtotal
                    gDetalles.guardarDetalle(det);//guardamos el detalle creado
                    carro.setTotal(carro.getTotal() + det.getSubtotal());//actualizamos el total del carro de compras
                    gCarros.actualizarCarro(carro);//actualizamos el carro en la base
                }
            }else{
                //si el carro no existe
                int id = gCarros.obtenerUltimoCarro().getId() + 1;//obtenemos el id del ultimo carro guardado en la base 
                carro = new Carro();//creamo un nuevo carro de compras
                carro.setEstado(true);//le ponemos de estado true para poder agregar productos
                carro.setUsuario(gUsuarios.leerUsuario(idusuario));//asignamos el usuario dueno del carro
                carro.setNumero(id + "-" + id + "-" + id);//creamos un numero para la factura
                carro.setFecha(LocalDateTime.now());//creamos con la fecha actual
                carro.setDescuento((double)(random.nextInt(100))/100);//generamos un descuento de forma aleatoria
                gCarros.guardarCarro(carro);//guardamos el carro nuevo en la base
                Detalle det = new Detalle();//creamos un nuevo detalle
                det.setCarro(carro);//asignamos el detalle al carro
                det.setProducto(gProductos.leerProducto(idproducto));//agregamos el producto al detalle
                det.setCantidad(cantidad);//asignamos la cantidad del producto
                det.setSubtotal();//calculamos el subtotal del detalle
                gDetalles.guardarDetalle(det);//guardamos el detalle en la base
                carro.setTotal(det.getSubtotal());//asinamos el total del carro con el nuevo detalle
                gCarros.actualizarCarro(carro);//actualizamos el carro con el total modificado
            }
            ErrorMessage error = new ErrorMessage(1, "OK");//creamos el objeto de respuesta
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(Response.Status.OK)
                .entity(error)
                .build();
        }catch(Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, "Error interno del servidor");
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @DELETE//especificamos que el servicio es de tipo DELETE
    @Path("carro")//ruta para acceder al servicio
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para eliminar el carro completo con detalles
    public Response eliminarCarro(@QueryParam("carro") int idcarro, @QueryParam("usuario") int idusuario){
        try{
            Carro car = gCarros.buscarIdCarroUsuario(idusuario, idcarro);//obtenemos el carro del usuario
            //creamos un mensaje de error que lo cambiaremos si es que se realizo el proceso
            ErrorMessage error = new ErrorMessage(99, "No existe un usuario con ese id de carro");
            Response.Status stat = Response.Status.NOT_FOUND;//creamos un estado de la respuesta que cambiaremos si es que se realizo el proceso
            if(car != null){
                gDetalles.borrarDetalleCarro(idcarro);//borramos todos los detalles que pertenecen al carro de compras
                gCarros.borrarCarro(idcarro);//borramos el carro de compras de la base
                error = new ErrorMessage(1, "OK");//cambiamos el mensaje de respuesta a OK
                stat = Response.Status.OK;//cambiamos el estado de la respuesta a OK
            }
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(stat)
                .entity(error)
                .build();
        }catch (Exception e) {
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, "Error al borrar el carro: No existe el carro con ese id");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }
    }

    @DELETE//especificamos que el servicio es de tipo DELETE
    @Path("detalle")//ruta para acceder al servicio
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para eliminar el producto de un carro de compras
    public Response eliminarProducto(@QueryParam("usuario") int idusuario, @QueryParam("carro") int idcarro, @QueryParam("producto") int idproducto){
        try{
            gDetalles.borrarProductoCarro(idcarro, idproducto);//mandamos a eliminar el detalle del carro de compras
            ErrorMessage error = new ErrorMessage(1, "OK");//creamos el objeto de respuesta
            //creamos la respuesta, le asignamos el estado y el objeto JSON de respuesta
            return Response.status(Response.Status.OK)
                .entity(error)
                .build();
        }catch(Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
        }
    }

    @GET//especificamos que el servicio es de tipo GET
    @Path("prods")//ruta para acceder al servicio
    @Produces(MediaType.APPLICATION_JSON)//especificamos que produce un JSON como resultado
    //metodo para buscar productos por un string
    public Response productosNombre(@QueryParam("nombre") String nombre){
        try{
            List<Producto> productos = gProductos.productosNombre(nombre);//obtenemos los productos mediante el string a buscar
            //creamos la respuesta, le asignamos el estado y los productos que coinciden con la busqueda
            return Response.status(Response.Status.OK)
                .entity(productos)
                .build();
        }catch(Exception e){
            //capturamos el error y devolvemos el mensaje
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.OK)
                .entity(error)
                .build();
        }
    }
}
