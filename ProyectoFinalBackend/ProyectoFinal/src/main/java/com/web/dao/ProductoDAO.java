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
}
