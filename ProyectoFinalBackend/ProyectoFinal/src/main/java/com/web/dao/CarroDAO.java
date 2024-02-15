package com.web.dao;

import jakarta.ejb.Stateless;

import com.web.modelos.Carro;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class CarroDAO{

    @PersistenceContext//asignamos el encargado para la persistencia
    private EntityManager em;//creamos una instancia del entity manager para la persistencia

    //metodo para guardar la instancia del carro en la base
    public void insert(Carro carro){
        em.persist(carro);
    }

    //metodo para actualizar la instancia del carro en la base
    public void update(Carro carro){
        em.merge(carro);
    }

    //metodo para borrar un carro de la base mediante el id
    public void remove(int id){
        Carro carro = em.find(Carro.class, id);
        em.remove(carro);
    }

    //metodo para obtener el carro mediante un id
    public Carro read(int id){
        Carro carro = em.find(Carro.class, id);
        return carro;
    }

    //metodo para buscar un carro mediante el id del usuario al que pertenece
    public List<Carro> buscarCarroUsuario(int id){
        String sql = "SELECT * FROM carros WHERE idusuario = :id";//creamos la sentencia sql para la consulta a la base
        Query q = em.createNativeQuery(sql, Carro.class);//asignamos la sentencia y el tipo de respuesta que se espera 
        q.setParameter("id", id);//asignamos el valor al parametro de la sentencia
        List<Carro> carros = q.getResultList();//obtenemos los resultados
        if(carros != null && carros.size() > 0)//verificamos que obtuvimos al menos un resultado
            return carros;//devolvemos la lista de los carros
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener el ultimo carro guardado por el usuario
    public Carro ultimoCarroUsuario(int idusuario){
        //se obtiene mediante el id del usuario y se ordena de forma descendente
        String sql = "SELECT * FROM carros WHERE idusuario = :idusr ORDER BY 1 DESC";//creamos la sentencia
        Query q = em.createNativeQuery(sql, Carro.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("idusr",idusuario);//asignamos el valor al parametro del id del usuario
        List<Carro> carros = q.getResultList();//obtenemos una lista de los resultados
        if(carros != null && carros.size() > 0)//verificamos que obtuvimos al menos un resultado
            return carros.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener el ultimo carro agregado a la base
    public Carro ultimoCarro(){
        //se obtienen todos los carros y se ordenan por el id de forma descendente
        String sql = "SELECT * FROM carros ORDER BY 1 DESC";//creamos la sentencia
        Query q = em.createNativeQuery(sql, Carro.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        List<Carro> carros = q.getResultList();//obtenemos una lista de los resultados
        if(carros != null && carros.size() > 0)//verificamos que obtuvimos al menos un resultado
            return carros.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para listar los carros
    public List<Carro> getAll(){
        String sentencia = "SELECT c FROM Carro c";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Carro.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta

        return q.getResultList();//devolvemos la lista de los carros
    }
}
