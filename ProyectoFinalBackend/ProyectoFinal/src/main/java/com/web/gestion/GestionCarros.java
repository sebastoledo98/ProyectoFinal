package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Carro;
import com.web.dao.CarroDAO;

@Stateless
public class GestionCarros {

    @Inject
    private CarroDAO carroDao;

    public void guardarCarro(Carro carro){
    	System.out.println("carro = " + carro);
        Carro car = carroDao.read(carro.getId());
        System.out.println("car = " + car);
        if(car == null)
            carroDao.insert(carro);
        else
            carroDao.update(carro);
    }
    
    public void actualizarCarro(Carro carro) throws Exception{
    	Carro car = carroDao.read(carro.getId());
    	if(car != null) 
            carroDao.update(carro);
        else
            throw new Exception("Carro no existe");
    }

    public Carro buscarCarroUsuario(int id) throws Exception{
        Carro carro = carroDao.buscarCarroUsuario(id);
        if(carro == null)
            throw new Exception("No existe un carro asociado con ese usuario");
        return carro;
    }

    public Carro leerCarro(int id) throws Exception{
        Carro carro = carroDao.read(id);
        if(carro != null)
            return carroDao.read(id);
        throw new Exception("Carro no encontrado");
    }

    public void borrarCarro(int id){
        carroDao.remove(id);
    }

    public List<Carro> getCarros(){
        return carroDao.getAll();
    }
}
