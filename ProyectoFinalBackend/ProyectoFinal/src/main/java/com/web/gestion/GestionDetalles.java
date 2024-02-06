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

    public List<Detalle> getDetalles(){
        return detalleDao.getAll();
    }
}
