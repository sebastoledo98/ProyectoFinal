package com.web.dao;

import jakarta.ejb.Stateless;

import java.util.List;
import com.web.modelos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class UsuarioDAO {

    @PersistenceContext//asignamos el encargado para la persistencia
    private EntityManager em;//creamos una instancia del entity manager para la persistencia

    //metodo para guardar la instancia del usuario en la base
    public void insert(Usuario usuario){
        em.persist(usuario);
    }

    //metodo para actualizar la instancia del usuario en la base
    public void update(Usuario usuario){
        em.merge(usuario);
    }

    //metodo para borrar un usuario de la base mediante el id
    public void remove(int codigo){
        Usuario usuario = em.find(Usuario.class, codigo);
        em.remove(usuario);
    }

    //metodo para obtener el usuario mediante un id
    public Usuario read(int codigo){
        Usuario usuario = em.find(Usuario.class, codigo);
        return usuario;
    }

    //metodo para realizar el login del usuario
    public Usuario login(String usuario, String pass){
        String sql = "SELECT * FROM usuarios WHERE usuario = :usr AND password = :pass";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Usuario.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("usr", usuario);//asignamos el valor al parametro del usuario
        q.setParameter("pass",pass);//asignamos el valor al parametro de la contrasena del usuario
        List<Usuario> usuarios = q.getResultList();//obtenemos una lista de los resultados
        if(usuarios.size() > 0)//verificamos que obtuvimos al menos un resultado
            return usuarios.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener el usuario mediante el id
    public Usuario getUsuarioPorId(int id) {
        String sentencia = "SELECT u FROM Usuario u WHERE u.id = :id";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Usuario.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("id",id);//asignamos el valor al parametro del id del usuario
        List<Usuario> usuarios = q.getResultList();//obtenemos una lista de los resultados
        if(usuarios.size() > 0)//verificamos que obtuvimos al menos un resultado
            return usuarios.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener el usuario mediante su login
    public Usuario getUsuario(String usuario){
        String sql = "SELECT * FROM usuarios WHERE usuario = :usr";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql,Usuario.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("usr",usuario);//asignamos el valor al parametro del usuario
        List<Usuario> usuarios = q.getResultList();//obtenemos una lista de los resultados
        if(usuarios.size() > 0)//verificamos que obtuvimos al menos un resultado
            return usuarios.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para listar los usuarios
    public List<Usuario> getAll(){
        String sentencia = "SELECT u FROM Usuario u";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Usuario.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        return q.getResultList();//devolvemos la lista de los usuarios
    }

}
