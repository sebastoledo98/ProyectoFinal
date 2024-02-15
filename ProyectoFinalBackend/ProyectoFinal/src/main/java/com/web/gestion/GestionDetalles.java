package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Detalle;
import com.web.dao.DetalleDAO;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class GestionDetalles {

    @Inject//injectamos la clase para no necesitar inicializarla
    private DetalleDAO detalleDao;

    //metodo para guardar el objeto de la clase
    public void guardarDetalle(Detalle detalle){
    	System.out.println("detalle = " + detalle);
        Detalle fac = detalleDao.read(detalle.getId());//buscamos si existe
        System.out.println("fac = " + fac);
        if(fac == null)//verificamos si nos devolvio un null
            detalleDao.insert(detalle);//insertamos si no existe
        else
            detalleDao.update(detalle);//actualizamos si ya existe
    }
    
    //metodo para actualizar el objeto de la clase
    public void actualizarDetalle(Detalle detalle) throws Exception{
    	Detalle fac = detalleDao.read(detalle.getId());//buscamos si existe
    	if(fac != null) //verificamos si nos devolvio un null
            detalleDao.update(detalle);//actualizamos si ya existe
        else
            throw new Exception("Detalle no existe");//lanzamos un error si es que no existe
    }

    //metodo para borrar el objeto de la clase medante el id
    public void borrarDetalle(int id){
        detalleDao.remove(id);
    }
    
    //metodo para buscar un objeto de la clase mediante el id
    public Detalle leer(int id){
        return detalleDao.read(id);
    }

    //metodo para buscar si el producto esta en el carro del usuario
    public Detalle buscarProductoCarro(int idusuario, int idproducto, int idcarro){
        Detalle detalle = detalleDao.productoCarro(idusuario, idproducto, idcarro);
        return detalle;
    }

    //metodo para borrar un producto/detalle del carro
    public void borrarProductoCarro(int idcarro, int idproducto){
        detalleDao.borrarProductoCarro(idcarro, idproducto);
    }

    //metodo para borrar el detalle del carro
    public void borrarDetalleCarro(int idcarro){
        detalleDao.borrarCarro(idcarro);
    }

    //metodos para listar todos los objetos de la clase
    public List<Detalle> getDetalles(){
        return detalleDao.getAll();
    }

    //metodo para obtener los detalles de un carro
    public List<Detalle> detalleCarro(int idcarro) throws Exception{
        List<Detalle> detalles = detalleDao.detalleCarro(idcarro);//obtenemos los detalles
        if(detalles != null && detalles.size() > 0)//verificamos si nos devolvio detalles
            return detalles;//devolvemos la lista de detalles obtenidos
        throw new Exception("No existen detalles para el carro con id " + idcarro);//lanzamos un error si es que no existen detalles en el carro
    }
}
