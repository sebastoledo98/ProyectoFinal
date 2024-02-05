package com.web.dao;

import jakarta.ejb.Stateless;

import com.web.modelos.Carro;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CarroDAO{

    @PersistenceContext
    private EntityManager em;

    public void insert(Carro carro){
        em.persist(carro);
    }

    public void update(Carro carro){
        em.merge(carro);
    }

    public void remove(int id){
        Carro carro = em.find(Carro.class, id);
        em.remove(carro);
    }

    public Carro read(int id){
        Carro carro = em.find(Carro.class, id);
        return carro;
    }

    public List<Carro> getAll(){
        String sentencia = "SELECT c FROM Carro c";
        Query q = em.createQuery(sentencia, Carro.class);
        return q.getResultList();
    }
}
