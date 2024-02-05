package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Producto;
import com.web.dao.ProductoDAO;

@Stateless
public class GestionProductos {

    @Inject
    private ProductoDAO productoDao;

    public void guardarProducto(Producto producto){
    	System.out.println("producto = " + producto);
        Producto prod = productoDao.read(producto.getId());
        System.out.println("prod = " + prod);
        if(prod == null)
            productoDao.insert(producto);
        else
            productoDao.update(producto);
    }
    
    public void actualizarProducto(Producto producto) throws Exception{
    	Producto prod = productoDao.read(producto.getId());
    	if(prod != null) 
            productoDao.update(producto);
        else
            throw new Exception("Producto no existe");
    }

    public Producto leerProducto(int id){
        return productoDao.read(id);
    }

    public void borrarProducto(int id){
        productoDao.remove(id);
    }

    public List<Producto> getProductos(){
        return productoDao.getAll();
    }
}
