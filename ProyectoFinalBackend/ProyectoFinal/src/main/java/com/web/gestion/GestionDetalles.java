package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Detalle;
import com.web.dao.DetalleDAO;

@Stateless
public class GestionDetalles {

    @Inject
    private DetalleDAO detalleDao;

    public void guardarDetalle(Detalle detalle){
    	System.out.println("detalle = " + detalle);
        Detalle fac = detalleDao.read(detalle.getId());
        System.out.println("fac = " + fac);
        if(fac == null)
            detalleDao.insert(detalle);
        else
            detalleDao.update(detalle);
    }
    
    public void actualizarDetalle(Detalle detalle) throws Exception{
    	Detalle fac = detalleDao.read(detalle.getId());
    	if(fac != null) 
            detalleDao.update(detalle);
        else
            throw new Exception("Detalle no existe");
    }

    public void borrarDetalle(int id){
        detalleDao.remove(id);
    }
    
    public Detalle leer(int id){
        return detalleDao.read(id);
    }

    public Detalle buscarProductoCarro(int idusuario, int idproducto, int idcarro){
        Detalle detalle = detalleDao.productoCarro(idusuario, idproducto, idcarro);
        return detalle;
    }

    public void borrarProductoCarro(int idcarro, int idproducto){
        detalleDao.borrarProductoCarro(idcarro, idproducto);
    }

    public void borrarDetalleCarro(int idcarro){
        detalleDao.borrarCarro(idcarro);
    }

    public List<Detalle> getDetalles(){
        return detalleDao.getAll();
    }

    public List<Detalle> detalleCarro(int idcarro){
        return detalleDao.detalleCarro(idcarro);
    }
}
