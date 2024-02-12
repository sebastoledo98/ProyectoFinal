package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Categoria;
import com.web.dao.CategoriaDAO;

@Stateless
public class GestionCategorias {

    @Inject
    private CategoriaDAO categoriaDao;

    public void guardarCategoria(Categoria categoria){
    	System.out.println("categoria = " + categoria);
        Categoria cat = categoriaDao.read(categoria.getId());
        System.out.println("cat = " + cat);
        if(cat == null)
            categoriaDao.insert(categoria);
        else
            categoriaDao.update(categoria);
    }
    
    public void actualizarCategoria(Categoria categoria) throws Exception{
    	Categoria cat = categoriaDao.read(categoria.getId());
    	if(cat != null) 
            categoriaDao.update(categoria);
        else
            throw new Exception("Categoria no existe");
    }

    public void borrarCategoria(int id){
        categoriaDao.remove(id);
    }

    public Categoria buscarCategoriaId(int id){
        return categoriaDao.read(id);
    }

    public List<Categoria> getCategorias(){
        return categoriaDao.getAll();
    }
}
