package com.web.dao;

import com.web.modelos.Categoria;
import com.web.modelos.Producto;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class CategoriaDAO {

    @PersistenceContext//asignamos el encargado para la persistencia
    private EntityManager em;//creamos una instancia del entity manager para la persistencia

    //metodo para guardar la instancia de la categoria en la base
    public void insert(Categoria categoria){
        em.persist(categoria);
    }

    //metodo para actualizar la instancia de la categoria en la base
    public void update(Categoria categoria){
        em.merge(categoria);
    }

    //metodo para borrar una categoria de la base mediante el id
    public void remove(int codigo){
        Categoria categoria = em.find(Categoria.class, codigo);
        em.remove(categoria);
    }

    //metodo para obtener el carro mediante un id
    public Categoria read(int codigo){
        Categoria categoria = em.find(Categoria.class, codigo);
        return categoria;
    }

    //metodo para listar las categorias
    public List<Categoria> getAll(){
        String sentencia = "SELECT c FROM Categoria c";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Categoria.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        return q.getResultList();//devolvemos la lista de las categorias
    }

    //metodo para buscar la categoria por id
    public Categoria getCategoriaPorCodigo(int codigo){
        System.out.println("productoDao:" + codigo);
        String jpql = "SELECT c FROM Categoria c WHERE c.id = :codigo";//creamos una sentencia jpql
        Query q = em.createQuery(jpql, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("codigo", codigo);//asignamos el valor al parametro del codigo a buscar
        List<Categoria> categorias = q.getResultList();//obtenermos la lista de las categorias
        if(categorias.size()>0)//verificamos que obtuvimos al menos un resultado
                return categorias.get(0);//verificamos que obtuvimos al menos un resultado
        return null;//si no se obtuvo nada devolvemos un null
    }
}
