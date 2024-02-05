package com.web.dao;

import com.web.modelos.Factura;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class FacturaDAO{

    @PersistenceContext
    private EntityManager em;

    public void insert(Factura factura){
        em.persist(factura);
    }

    public void update(Factura factura){
        em.merge(factura);
    }

    public void remove(int codigo){
        Factura factura = em.find(Factura.class, codigo);
        em.remove(factura);
    }

    public Factura read(int codigo){
        Factura factura = em.find(Factura.class, codigo);
        return factura;
    }

    public List<Factura> getAll(){
        String sentencia = "SELECT f FROM Factura f";
        Query q = em.createQuery(sentencia, Factura.class);
        return q.getResultList();
    }
}
