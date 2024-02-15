package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Carro;
import com.web.dao.CarroDAO;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class GestionCarros {

    @Inject//injectamos la clase para no necesitar inicializarla
    private CarroDAO carroDao;

    //metodo para guardar el objeto de la clase
    public void guardarCarro(Carro carro){
    	System.out.println("carro = " + carro);
        Carro car = carroDao.read(carro.getId());//buscamos si existe
        System.out.println("car = " + car);
        if(car == null)//verificamos si nos devolvio un null
            carroDao.insert(carro);//insertamos si no existe
        else
            carroDao.update(carro);//actualizamos si ya existe
    }
    
    //metodo para actualizar el objeto de la clase
    public void actualizarCarro(Carro carro) throws Exception{
    	Carro car = carroDao.read(carro.getId());//buscamos si existe
    	if(car != null) //verificamos si nos devolvio un null
            carroDao.update(carro);//actualizamos si ya existe
        else
            throw new Exception("Carro no existe");//lanzamos un error si es que no existe
    }

    //metodo para buscaer el carro por el id y por el usuario
    public Carro buscarIdCarroUsuario(int idusuario, int idcarro)throws Exception{
        List<Carro> carros = carroDao.buscarCarroUsuario(idusuario);//buscamos si existe
        for(Carro c : carros)//iteramos por la lista que nos devuelve
            if(c.getId() == idcarro)//buscamos el que coincida por id
                return c;//devolvemos el objeto encontrado
        throw new Exception("No existe un carro con id " + idcarro + " que pertenezca al usuario con id " + idusuario);
    }

    //metodo para buscar los carros mediante el usuario
    public List<Carro> buscarCarroUsuario(int idusuario){
        List<Carro> carros = carroDao.buscarCarroUsuario(idusuario);//buscamos si exsten
        return carros;//devolvemos la lista obtenida
    }

    //metodo para buscar el carro mediante el id
    public Carro leerCarro(int id) throws Exception{
        Carro carro = carroDao.read(id);//buscamos si existe
        if(carro != null)//verificamos si nos devolvio un null
            return carro;//devolvemos el objeto obtenido
        throw new Exception("Carro no encontrado");//lanzamos un error si es que no existe
    }

    //metodo para borrar el objeto de la clase medante el id
    public void borrarCarro(int id){
        carroDao.remove(id);
    }

    //metodos para listar todos los objetos de la clase
    public List<Carro> getCarros(){
        return carroDao.getAll();
    }

    //metodo para obtener el ultimo carro creado por el usuario
    public Carro obtenerUltimoCarroUsuario(int idusuario)throws Exception{
        Carro carro = carroDao.ultimoCarroUsuario(idusuario);
        if(carro != null)//verificamos si nos devolvio un null
            return carro;//devolvemos el objeto obtenido
        throw new Exception("No existen carros para el usuario con id " + idusuario);//lanzamos un error si es que no existe
    }

    //metodo para obtener el ultimo carro agregado a la base
    public Carro obtenerUltimoCarro(){
        Carro carro = carroDao.ultimoCarro();
        return carro;
    }

}
