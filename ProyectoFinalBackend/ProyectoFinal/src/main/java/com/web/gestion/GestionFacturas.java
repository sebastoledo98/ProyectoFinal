package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Factura;
import com.web.dao.FacturaDAO;

@Stateless
public class GestionFacturas {

    @Inject
    private FacturaDAO facturaDao;

    public void guardarFactura(Factura factura){
    	System.out.println("factura = " + factura);
        Factura fac = facturaDao.read(factura.getId());
        System.out.println("fac = " + fac);
        if(fac == null)
            facturaDao.insert(factura);
        else
            facturaDao.update(factura);
    }
    
    public void actualizarFactura(Factura factura) throws Exception{
    	Factura fac = facturaDao.read(factura.getId());
    	if(fac != null) 
            facturaDao.update(factura);
        else
            throw new Exception("Factura no existe");
    }

    public void borrarFactura(int id){
        facturaDao.remove(id);
    }

    public List<Factura> getFacturas(){
        return facturaDao.getAll();
    }
}
