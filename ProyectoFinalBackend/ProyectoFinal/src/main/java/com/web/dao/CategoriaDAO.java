package com.web.dao;

import com.web.modelos.Categoria;
import com.web.modelos.Producto;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Categoria categoria){
        em.persist(categoria);
    }

    public void update(Categoria categoria){
        em.merge(categoria);
    }

    public void remove(int codigo){
        Categoria categoria = em.find(Categoria.class, codigo);
        em.remove(categoria);
    }

    public Categoria read(int codigo){
        Categoria categoria = em.find(Categoria.class, codigo);
        return categoria;
    }

    public List<Categoria> getAll(){
        String sentencia = "SELECT c FROM Categoria c";
        Query q = em.createQuery(sentencia, Categoria.class);
        return q.getResultList();
    }

    public Categoria getCategoriaPorCodigo(int codigo){
        System.out.println("productoDao:" + codigo);
        String jpql = "SELECT c FROM Categoria c WHERE c.id = :codigo";
        Query q = em.createQuery(jpql, Producto.class);
        q.setParameter("codigo", codigo);
        List<Categoria> categorias = q.getResultList();
        if(categorias.size()>0)
                return categorias.get(0);
        return null;
    }
}
