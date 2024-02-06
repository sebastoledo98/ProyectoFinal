package com.web.gestion;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

import com.web.dao.*;
import com.web.modelos.*;

@Singleton
@Startup
public class GestionDatos {

    @Inject
    private GestionUsuarios gUsuarios;
    @Inject
    private GestionDetalles gDetalles;
    @Inject
    private GestionCategorias gCategorias;
    @Inject
    private GestionCarros gCarros;
    @Inject
    private GestionProductos gProductos;

    @PostConstruct
    public void init(){
        System.out.println("iniciando");

        //usuario 1
        Usuario usuario = new Usuario();
        System.out.println(usuario);
        usuario.setId(1);
        usuario.setUsuario("pepito");
        usuario.setPassword("pepito123");
        usuario.setNombres("Pepe");
        usuario.setApellidos("Perez");
        usuario.setEmail("pepe@ejemplo.com");
        usuario.setUbicacion("Cuenca");
        usuario.setDireccion("Av. Don Bosco");
        usuario.setTelefono("0986532147");
        usuario.setSaldo(120.00);
        System.out.println(usuario);
        gUsuarios.guardarUsuario(usuario);

        //usuario 2
        usuario = new Usuario();
        usuario.setId(2);
        usuario.setUsuario("diego");
        usuario.setPassword("diego123");
        usuario.setNombres("Diego");
        usuario.setApellidos("Gomez");
        usuario.setEmail("diego@ejemplo.com");
        usuario.setUbicacion("Quito");
        usuario.setDireccion("Av. Don Bosco");
        usuario.setTelefono("0986532147");
        usuario.setSaldo(325.00);
        gUsuarios.guardarUsuario(usuario);

        //Categoria 1
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Carnes");
        categoria.setDescripcion("Carnes de animales para el consumo humano");
        gCategorias.guardarCategoria(categoria);

        //Producto 1
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Carne de cerdo");
        producto.setDescripcion("Carne de cerdo 1KG");
        producto.setPrecio(10.0);
        producto.setStock(20);
        producto.setCategoria(categoria);
        gProductos.guardarProducto(producto);

        //Categoria 2
        categoria = new Categoria();
        categoria.setId(2);
        categoria.setNombre("Ropa");
        categoria.setDescripcion("Ropa deportiva unisex");
        gCategorias.guardarCategoria(categoria);

        //Producto 2
        producto = new Producto();
        producto.setId(2);
        producto.setNombre("Calentador Gris");
        producto.setDescripcion("Calentador Deportivo Color Gris Unisex");
        producto.setPrecio(25.0);
        producto.setStock(90);
        producto.setCategoria(categoria);
        gProductos.guardarProducto(producto);

        //Carro 1
        Carro carro = new Carro();
        carro.setId(1);
        carro.setUsuario(gUsuarios.leerUsuario(1));
        carro.setNumero("001-001-1234");
        carro.setFecha(LocalDateTime.now());

        //Detalle 1
        Detalle detalle = new Detalle();
        detalle.setId(1);
        detalle.setCarro(carro);
        detalle.setProducto(producto);
        detalle.setCantidad(5);
        detalle.setSubtotal();

        carro.setTotal(detalle.getSubtotal());
        carro.setDescuento(0.00);
        gCarros.guardarCarro(carro);
        gDetalles.guardarDetalle(detalle);

        //Carro 2
        carro = new Carro();
        carro.setId(2);
        carro.setUsuario(gUsuarios.leerUsuario(2));
        carro.setNumero("002-002-5678");
        carro.setFecha(LocalDateTime.now());
        carro.setDescuento(1.00);
        carro.setTotal(0.00);
        gCarros.guardarCarro(carro);

        //Detalle 2
        detalle = new Detalle();
        detalle.setId(2);
        detalle.setCarro(carro);
        detalle.setProducto(gProductos.leerProducto(1));
        detalle.setCantidad(5);
        detalle.setSubtotal();
        gDetalles.guardarDetalle(detalle);
        double tot = detalle.getSubtotal();

        //Detalle 3
        detalle = new Detalle();
        detalle.setId(3);
        detalle.setCarro(carro);
        detalle.setProducto(gProductos.leerProducto(2));
        detalle.setCantidad(2);
        detalle.setSubtotal();
        gDetalles.guardarDetalle(detalle);
        tot = detalle.getSubtotal();

        carro.setTotal(tot);
        gCarros.guardarCarro(carro);

        //Listar Clientes
        List<Usuario> lista = gUsuarios.getUsuarios();
        for(Usuario u : lista){
            System.out.println(u);
        }

        //Listar categorias
        List<Categoria> categorias = gCategorias.getCategorias();
        for(Categoria c : categorias){
            System.out.println(c);
        }

        //Listar productos
        List<Producto> productos = gProductos.getProductos();
        for(Producto p : productos){
            System.out.println(p);
        }

        //Listar Detalles
        List<Detalle> detalles = gDetalles.getDetalles();
        for(Detalle c : detalles){
            System.out.println(c);
        }

        //Listar Carros
        System.out.println("--------------Carros--------------");
        List<Carro> carros = gCarros.getCarros();
        for(Carro f : carros){
            System.out.println(f);
        }
    }
}
