package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Categoria;
import com.web.dao.CategoriaDAO;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class GestionCategorias {

    @Inject//injectamos la clase para no necesitar inicializarla
    private CategoriaDAO categoriaDao;

    //metodo para guardar el objeto de la clase
    public void guardarCategoria(Categoria categoria){
    	System.out.println("categoria = " + categoria);
        Categoria cat = categoriaDao.read(categoria.getId());//buscamos si existe
        System.out.println("cat = " + cat);
        if(cat == null)//verificamos si nos devolvio un null
            categoriaDao.insert(categoria);//insertamos si no existe
        else
            categoriaDao.update(categoria);//actualizamos si ya existe
    }
    
    //metodo para actualizar el objeto de la clase
    public void actualizarCategoria(Categoria categoria) throws Exception{
    	Categoria cat = categoriaDao.read(categoria.getId());//buscamos si existe
    	if(cat != null)//verificamos si nos devolvio un null
            categoriaDao.update(categoria);//actualizamos si ya existe
        else
            throw new Exception("Categoria no existe");//lanzamos un error si es que no existe
    }

    //metodo para borrar el objeto de la clase medante el id
    public void borrarCategoria(int id){
        categoriaDao.remove(id);
    }


    //metodo para buscar el objeto de la clase medante el id
    public Categoria buscarCategoriaId(int id){
        return categoriaDao.read(id);
    }

    //metodos para listar todos los objetos de la clase
    public List<Categoria> getCategorias(){
        return categoriaDao.getAll();
    }

    //metodo para obtener la categoria por el codigo
    public Categoria getCategoriaPorCodigo(int codigo) throws Exception{
        System.out.println("codigoCategGestion=" + codigo);
        System.out.println("productosEnviado");
        return categoriaDao.getCategoriaPorCodigo(codigo);
    }

}
