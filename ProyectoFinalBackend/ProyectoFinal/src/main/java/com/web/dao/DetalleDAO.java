package com.web.dao;

import com.web.modelos.Detalle;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class DetalleDAO{

    @PersistenceContext//asignamos el encargado para la persistencia
    private EntityManager em;//creamos una instancia del entity manager para la persistencia

    //metodo para guardar la instancia del detalle en la base
    public void insert(Detalle detalle){
        em.persist(detalle);
    }

    //metodo para actualizar la instancia del detalle en la base
    public void update(Detalle detalle){
        em.merge(detalle);
    }

    //metodo para borrar un detalle de la base mediante el id
    public void remove(int codigo){
        Detalle detalle = em.find(Detalle.class, codigo);
        em.remove(detalle);
    }

    //metodo para obtener el detalle mediante un id
    public Detalle read(int codigo){
        Detalle detalle = em.find(Detalle.class, codigo);
        return detalle;
    }

    //metodo para obtener el producto por el id del carro y el id del usuario
    public Detalle productoCarro(int idusuario, int idproducto, int idcarro){
        String sql = "SELECT d.* FROM detalles d, carros c WHERE c.idcarro = d.idcarro AND d.idproducto = :idprod AND d.idcarro = :idcar AND c.idusuario = :idusr";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Detalle.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("idprod",idproducto);//asignamos el valor al parametro del id del producto
        q.setParameter("idcar", idcarro);//asignamos el valor al parametro del id del carro
        q.setParameter("idusr", idusuario);//asignamos el valor al parametro del id del usuario
        List<Detalle> detalles = q.getResultList();//obtenemos una lista de los resultados
        if(detalles != null && detalles.size() > 0)//verificamos que obtuvimos al menos un resultado
            return detalles.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para eliminar los detalles mediante el id del carro
    public void borrarCarro(int idcarro){
        String sql = "DELETE FROM detalles WHERE idcarro = :idcar";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Detalle.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("idcar",idcarro);//asignamos el valor al parametro del id del carro
        q.executeUpdate();
    }

    public void borrarProductoCarro(int idcarro, int idproducto){
        String sql = "DELETE FROM detalles WHERE idcarro = :idcar AND idproducto = :idprod";
        Query q = em.createNativeQuery(sql, Detalle.class);
        q.setParameter("idcar",idcarro);
        q.setParameter("idprod",idproducto);
        q.executeUpdate();//ejecutamos la consulta
    }

    //metodo para listar los detalles
    public List<Detalle> getAll(){
        String sentencia = "SELECT d FROM Detalle d";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Detalle.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        return q.getResultList();//devolvemos la lista de los detalles
    }

    //metodo para obtener los detalles por el id del carro
    public List<Detalle> detalleCarro(int idcarro){
        String sql = "SELECT * FROM detalles WHERE idcarro = :idcar";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Detalle.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("idcar", idcarro);//asignamos el valor al parametro del id del carro
        List<Detalle> detalles = q.getResultList();//obtenemos una lista de los resultados
        if(detalles != null && detalles.size() > 0)//verificamos que obtuvimos al menos un resultado
            return detalles;//devolvemos la lista de los detalles
        return null;//si no se obtuvo nada devolvemos un null
    }
}
