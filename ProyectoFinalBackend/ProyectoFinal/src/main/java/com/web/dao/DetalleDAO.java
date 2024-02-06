package com.web.dao;

import com.web.modelos.Detalle;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DetalleDAO{

    @PersistenceContext
    private EntityManager em;

    public void insert(Detalle detalle){
        em.persist(detalle);
    }

    public void update(Detalle detalle){
        em.merge(detalle);
    }

    public void remove(int codigo){
        Detalle detalle = em.find(Detalle.class, codigo);
        em.remove(detalle);
    }

    public Detalle read(int codigo){
        Detalle detalle = em.find(Detalle.class, codigo);
        return detalle;
    }

    public List<Detalle> getAll(){
        String sentencia = "SELECT f FROM Detalle f";
        Query q = em.createQuery(sentencia, Detalle.class);
        return q.getResultList();
    }
}
