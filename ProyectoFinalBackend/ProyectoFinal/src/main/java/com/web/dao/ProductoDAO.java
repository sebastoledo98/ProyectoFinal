package com.web.dao;

import java.util.List;
import com.web.modelos.Producto;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProductoDAO {
	
    @PersistenceContext
    private EntityManager em;

    public void insert(Producto producto){
        em.persist(producto);
    }

    public void update(Producto producto){
        em.merge(producto);
    }

    public void remove(int codigo){
        Producto producto = em.find(Producto.class, codigo);
        em.remove(producto);
    }

    public Producto read(int codigo){
        Producto producto = em.find(Producto.class, codigo);
        return producto;
    }

    public List<Producto> getAll(){
        String sentencia = "SELECT p FROM Producto p";
        Query q = em.createQuery(sentencia, Producto.class);
        return q.getResultList();
    }

    public List<Producto> productoCategoria(int idcategoria){
        String sql = "SELECT * FROM productos WHERE idcategoria = :idcat";
        Query q = em.createNativeQuery(sql, Producto.class);
        q.setParameter("idcat",idcategoria);
        List<Producto> detalles = q.getResultList();
        if(detalles != null && detalles.size() > 0)
            return detalles;
        return null;
    }

    public Producto getProductoPorCodigo(int codigo){
        System.out.println("productoDao:" + codigo);
        String jpql = "SELECT c FROM Producto c WHERE c.id = :codigo";
        Query q = em.createQuery(jpql, Producto.class);
        q.setParameter("codigo", codigo);
        List<Producto> productos = q.getResultList();
        if(productos.size()>0)
            return productos.get(0);
        return null;
    }

    public List<Producto> getProductosCatPorCodigo(int codigo){
        System.out.println("productoCategoriaDao:" + codigo);
        String jpql = "SELECT p FROM Producto p JOIN p.categoria c WHERE c.id = :codigo";
        Query q = em.createQuery(jpql, Producto.class);
        q.setParameter("codigo", codigo);
        List<Producto> productos = q.getResultList();
        if(productos.size()> 0)
            return productos;
        return null;
    }

}
