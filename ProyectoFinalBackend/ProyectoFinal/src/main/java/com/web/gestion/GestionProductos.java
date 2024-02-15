package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Producto;
import com.web.dao.ProductoDAO;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class GestionProductos {

    @Inject//injectamos la clase para no necesitar inicializarla
    private ProductoDAO productoDao;

    //metodo para guardar el objeto de la clase
    public void guardarProducto(Producto producto){
    	System.out.println("producto = " + producto);
        Producto prod = productoDao.read(producto.getId());//buscamos si existe
        System.out.println("prod = " + prod);
        if(prod == null)//verificamos si nos devolvio un null
            productoDao.insert(producto);//insertamos si no existe
        else
            productoDao.update(producto);//actualizamos si ya existe
    }
    
    //metodo para actualizar el objeto de la clase
    public void actualizarProducto(Producto producto) throws Exception{
    	Producto prod = productoDao.read(producto.getId());//buscamos si existe
    	if(prod != null)//verificamos si nos devolvio un null
            productoDao.update(producto);//actualizamos si ya existe
        else
            throw new Exception("Producto no existe");//lanzamos un error si es que no existe
    }

    //metodo para buscar un objeto de la clase mediante el id
    public Producto leerProducto(int id){
        return productoDao.read(id);
    }

    //metodo para borrar el objeto de la clase medante el id
    public void borrarProducto(int id){
        productoDao.remove(id);
    }

    //metodos para listar todos los objetos de la clase
    public List<Producto> getProductos(){
        return productoDao.getAll();
    }
    
    //metodo para obtener el producto por el carro del cliente 
    public Producto getClientePorProducto(int codigo) throws Exception{
        System.out.println("codigoProdGestion=" + codigo);
        System.out.println("clienteEnviado");
        return productoDao.getProductoPorCodigo(codigo);
    }

    //metodo para obtener una lista de productos por la categoria
    public List<Producto> getProductosporCategoria(int codigo) throws Exception{
        System.out.println("codigoProdCategorias=" + codigo);
        System.out.println("categoriaEnviado");
        return productoDao.getProductosCatPorCodigo(codigo);
    }

    //metodo para buscar productos por un string 
    public List<Producto> productosNombre(String nombre) throws Exception{
        List<Producto> productos = productoDao.productosNombre(nombre);
        if(productos != null && productos.size() > 0)
            return productos;
        throw new Exception("No se hallaron productos");
    }
}
