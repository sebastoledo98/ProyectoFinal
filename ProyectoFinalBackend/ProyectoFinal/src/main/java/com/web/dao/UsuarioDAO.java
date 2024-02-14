package com.web.dao;

import jakarta.ejb.Stateless;

import java.util.List;
import com.web.modelos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Usuario usuario){
        em.persist(usuario);
    }

    public void update(Usuario usuario){
        em.merge(usuario);
    }

    public void remove(int codigo){
        Usuario usuario = em.find(Usuario.class, codigo);
        em.remove(usuario);
    }

    public Usuario read(int codigo){
        Usuario usuario = em.find(Usuario.class, codigo);
        return usuario;
    }

    public Usuario login(String usuario, String pass){
        String sql = "SELECT * FROM usuarios WHERE usuario = :usr AND password = :pass";
        Query q = em.createNativeQuery(sql, Usuario.class);
        q.setParameter("usr", usuario);
        q.setParameter("pass",pass);
        List<Usuario> usuarios = q.getResultList();
        if(usuarios.size() > 0)
            return usuarios.get(0);
        return null;
    }

    public Usuario getUsuarioPorId(int id) {
        String sentencia = "SELECT u FROM Usuario u WHERE u.id = :id";
        Query q = em.createQuery(sentencia, Usuario.class);
        q.setParameter("id",id);
        List<Usuario> usuarios = q.getResultList();
        if(usuarios.size() > 0)
            return usuarios.get(0);
        return null;
    }

    public Usuario getUsuario(String usuario){
        String sql = "SELECT * FROM usuarios WHERE usuario = :usr";
        Query q = em.createNativeQuery(sql,Usuario.class);
        q.setParameter("usr",usuario);
        List<Usuario> usuarios = q.getResultList();
        if(usuarios.size() > 0)
            return usuarios.get(0);
        return null;
    }

    public List<Usuario> getAll(){
        String sentencia = "SELECT u FROM Usuario u";
        Query q = em.createQuery(sentencia, Usuario.class);
        return q.getResultList();
    }

}
