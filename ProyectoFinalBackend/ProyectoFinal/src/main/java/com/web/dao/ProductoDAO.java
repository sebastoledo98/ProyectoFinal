package com.web.dao;

import java.util.List;
import com.web.modelos.Producto;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class ProductoDAO {
	
    @PersistenceContext//asignamos el encargado para la persistencia
    private EntityManager em;//creamos una instancia del entity manager para la persistencia

    //metodo para guardar la instancia del producto en la base
    public void insert(Producto producto){
        em.persist(producto);
    }

    //metodo para actualizar la instancia del producto en la base
    public void update(Producto producto){
        em.merge(producto);
    }

    //metodo para borrar un producto de la base mediante el id
    public void remove(int codigo){
        Producto producto = em.find(Producto.class, codigo);
        em.remove(producto);
    }

    //metodo para obtener el producto mediante un id
    public Producto read(int codigo){
        Producto producto = em.find(Producto.class, codigo);
        return producto;
    }

    //metodo para listar los productos
    public List<Producto> getAll(){
        String sentencia = "SELECT p FROM Producto p";//creamos una sentencia jpql
        Query q = em.createQuery(sentencia, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        return q.getResultList();//devolvemos la lista de los productos
    }

    //metodo para obtener los productos por la categoria
    public List<Producto> productoCategoria(int idcategoria){
        String sql = "SELECT * FROM productos WHERE idcategoria = :idcat";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("idcat",idcategoria);//asignamos el valor al parametro del id de la categoria
        List<Producto> productos = q.getResultList();//obtenemos una lista de los resultados
        if(productos != null && productos.size() > 0)//verificamos que obtuvimos al menos un resultado
            return productos;//devolvemos la lista de los productos
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener el producto por el codigo
    public Producto getProductoPorCodigo(int codigo){
        System.out.println("producto id:" + codigo);
        String jpql = "SELECT c FROM Producto c WHERE c.id = :codigo";//creamos una sentencia jpql
        Query q = em.createQuery(jpql, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("codigo", codigo);//asignamos el valor al parametro del id del producto
        List<Producto> productos = q.getResultList();//obtenemos una lista de los resultados
        if(productos.size()>0)//verificamos que obtuvimos al menos un resultado
            return productos.get(0);//devolvemos el primer valor de la lista
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para obtener los productos por el codigo de la categoria
    public List<Producto> getProductosCatPorCodigo(int codigo){
        System.out.println("productoCategoriaDao:" + codigo);
        String jpql = "SELECT p FROM Producto p JOIN p.categoria c WHERE c.id = :codigo";//creamos una sentencia jpql
        Query q = em.createQuery(jpql, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        q.setParameter("codigo", codigo);//asignamos el valor al parametro del id de la categoria
        List<Producto> productos = q.getResultList();//obtenemos una lista de los resultados
        if(productos.size()> 0)//verificamos que obtuvimos al menos un resultado
            return productos;//devolvemos la lista de los productos
        return null;//si no se obtuvo nada devolvemos un null
    }

    //metodo para buscar los productos por un string
    public List<Producto> productosNombre(String nombre){
        System.out.println("Buscando : " + nombre);
        String sql = "SELECT * FROM productos WHERE nombre ILIKE '%" + nombre + "%'";//creamos la sentencia sql
        Query q = em.createNativeQuery(sql, Producto.class);//creamos la consulta, asignamos la sentencia y el tipo de respuesta
        List<Producto> productos = q.getResultList();//obtenemos una lista de los resultados
        if(productos != null && productos.size() > 0)//verificamos que obtuvimos al menos un resultado
            return productos;//devolvemos la lista de los productos
        return null;//si no se obtuvo nada devolvemos un null
    }

}
