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

    public List<Carro> buscarCarroUsuario(int id){
        String sql = "SELECT * FROM carros WHERE idusuario = :id";
        Query q = em.createNativeQuery(sql, Carro.class);
        q.setParameter("id", id);
        List<Carro> carros = q.getResultList();
        if(carros != null && carros.size() > 0)
            return carros;
        return null;
    }

    public Carro ultimoCarroUsuario(int idusuario){
        String sql = "SELECT * FROM carros WHERE idusuario = :idusr ORDER BY 1 DESC";
        Query q = em.createNativeQuery(sql, Carro.class);
        q.setParameter("idusr",idusuario);
        List<Carro> carros = q.getResultList();
        if(carros != null && carros.size() > 0)
            return carros.get(0);
        return null;
    }

    public Carro ultimoCarro(){
        String sql = "SELECT * FROM carros ORDER BY 1 DESC";
        Query q = em.createNativeQuery(sql, Carro.class);
        List<Carro> carros = q.getResultList();
        if(carros != null && carros.size() > 0)
            return carros.get(0);
        return null;
    }

    public List<Carro> getAll(){
        String sentencia = "SELECT c FROM Carro c";
        Query q = em.createQuery(sentencia, Carro.class);
        return q.getResultList();
    }
}
