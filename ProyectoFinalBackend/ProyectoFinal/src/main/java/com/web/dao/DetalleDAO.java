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

    public Detalle productoCarro(int idusuario, int idproducto, int idcarro){
        String sql = "SELECT d.* FROM detalles d, carros c WHERE c.idcarro = d.idcarro AND d.idproducto = :idprod AND d.idcarro = :idcar AND c.idusuario = :idusr";
        Query q = em.createNativeQuery(sql, Detalle.class);
        q.setParameter("idprod",idproducto);
        q.setParameter("idcar", idcarro);
        q.setParameter("idusr", idusuario);
        List<Detalle> detalles = q.getResultList();
        if(detalles != null && detalles.size() > 0)
            return detalles.get(0);
        return null;
    }

    public void borrarCarro(int idcarro){
        String sql = "DELETE FROM detalles WHERE idcarro = :idcar";
        Query q = em.createNativeQuery(sql, Detalle.class);
        q.setParameter("idcar",idcarro);
        q.executeUpdate();
    }

    public void borrarProductoCarro(int idcarro, int idproducto){
        String sql = "DELETE FROM detalles WHERE idcarro = :idcar AND idproducto = :idprod";
        Query q = em.createNativeQuery(sql, Detalle.class);
        q.setParameter("idcar",idcarro);
        q.setParameter("idprod",idproducto);
        q.executeUpdate();
    }

    public List<Detalle> getAll(){
        String sentencia = "SELECT d FROM Detalle d";
        Query q = em.createQuery(sentencia, Detalle.class);
        return q.getResultList();
    }

    public List<Detalle> detalleCarro(int idcarro){
        String sql = "SELECT * FROM detalles WHERE idcarro = :idcar";
        Query q = em.createNativeQuery(sql, Detalle.class);
        q.setParameter("idcar", idcarro);
        List<Detalle> detalles = q.getResultList();
        if(detalles != null && detalles.size() > 0)
            return detalles;
        return null;
    }
}
