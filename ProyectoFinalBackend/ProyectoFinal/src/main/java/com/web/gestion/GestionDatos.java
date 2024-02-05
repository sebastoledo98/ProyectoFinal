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
    private GestionCarros gCarros;
    @Inject
    private GestionCategorias gCategorias;
    @Inject
    private GestionFacturas gFacturas;
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

        //Factura 1
        Factura factura = new Factura();
        factura.setId(1);
        factura.setUsuario(gUsuarios.leerUsuario(1));
        factura.setNumero("001-001-1234");
        factura.setFecha(LocalDateTime.now());

        //Detalle 1
        Carro carro = new Carro();
        carro.setId(1);
        carro.setFactura(factura);
        carro.setProducto(producto);
        carro.setCantidad(5);
        carro.setSubtotal();

        factura.setTotal(carro.getSubtotal());
        factura.setDescuento(0.00);
        gFacturas.guardarFactura(factura);
        gCarros.guardarCarro(carro);

        //Factura 2
        factura = new Factura();
        factura.setId(2);
        factura.setUsuario(gUsuarios.leerUsuario(2));
        factura.setNumero("002-002-5678");
        factura.setFecha(LocalDateTime.now());
        factura.setDescuento(1.00);
        factura.setTotal(0.00);
        gFacturas.guardarFactura(factura);

        //Detalle 2
        carro = new Carro();
        carro.setId(2);
        carro.setFactura(factura);
        carro.setProducto(gProductos.leerProducto(1));
        carro.setCantidad(5);
        carro.setSubtotal();
        gCarros.guardarCarro(carro);
        double tot = carro.getSubtotal();

        //Detalle 3
        carro = new Carro();
        carro.setId(3);
        carro.setFactura(factura);
        carro.setProducto(gProductos.leerProducto(2));
        carro.setCantidad(2);
        carro.setSubtotal();
        gCarros.guardarCarro(carro);
        tot = carro.getSubtotal();

        factura.setTotal(tot);
        gFacturas.guardarFactura(factura);

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

        //Listar Carros
        List<Carro> carros = gCarros.getCarros();
        for(Carro c : carros){
            System.out.println(c);
        }

        //Listar Facturas
        System.out.println("--------------Facturas--------------");
        List<Factura> facturas = gFacturas.getFacturas();
        for(Factura f : facturas){
            System.out.println(f);
        }
    }
}
