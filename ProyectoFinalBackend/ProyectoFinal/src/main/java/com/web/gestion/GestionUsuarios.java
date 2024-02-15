package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Usuario;
import com.web.dao.UsuarioDAO;

@Stateless//especificamos que la clase sera una sesion de tipo stateless del EJB
public class GestionUsuarios {

    @Inject//injectamos la clase para no necesitar inicializarla
    private UsuarioDAO usuarioDao;

    //metodo para guardar el objeto de la clase
    public void guardarUsuario(Usuario usuario){
    	System.out.println("usuario = " + usuario);
        Usuario usr = usuarioDao.read(usuario.getId());//buscamos si existe
        System.out.println("usr = " + usr);
        if(usr == null)//verificamos si nos devolvio un null
            usuarioDao.insert(usuario);//insertamos si no existe
        else
            usuarioDao.update(usuario);//actualizamos si ya existe
    }
    
    //metodo para actualizar el objeto de la clase
    public void actualizarUsuario(Usuario usuario) throws Exception{
    	Usuario usr = usuarioDao.read(usuario.getId());//buscamos si existe
    	if(usr != null) //verificamos si nos devolvio un null
            usuarioDao.update(usuario);//actualizamos si ya existe
        else
            throw new Exception("Usuario no existe");//lanzamos un error si es que no existe
    }

    //metodo para buscar un objeto de la clase mediante el id
    public Usuario leerUsuario(int id){
        Usuario usr = usuarioDao.read(id);
        return usr;
    }

    //metodo para buscar el usuario mediante el id
    public Usuario getUsuarioPorId(int id) throws Exception{
        Usuario usr = usuarioDao.getUsuarioPorId(id);//buscamos si existe
        if(usr == null)//verificamos si nos devolvio un null
            throw new Exception("No existe el usuario con id " + id);//lanzamos un error si es que no existe
        return usr;//devolvemos el objeto obtenido
    }

    //metodo para buscar el usuario mediante su nombre de login
    public Usuario getUsuario(String usuario) throws Exception{
        Usuario usr = usuarioDao.getUsuario(usuario);//buscamos si existe
        if(usr == null)//verificamos si nos devolvio un null
            throw new Exception("No existe el usuario " + usuario);//lanzamos un error si es que no existe
        return usr;//devolvemos el objeto obtenido
    }

    //metodo para borrar el objeto de la clase mediante el id
    public void borrarUsuario(int id){
        usuarioDao.remove(id);
    }

    //metodos para listar todos los objetos de la clase
    public List<Usuario> getUsuarios(){
        return usuarioDao.getAll();
    }

    //metodo para autenticar el inicio de sesion del usuario
    public Usuario login(String usuario, String pass) throws Exception{
        System.out.println("Usuario: " + usuario);
        System.out.println("Contrasenia: " + pass);
        Usuario usr = usuarioDao.login(usuario, pass);//compobamos el usuario y la contrasena
        if(usr != null)//verificamos si nos devolvio un null
            return usr;//retornamos el usuario que inicio sesion
        else{
            throw new Exception("Error en el login, verificar el nombre de usuario y la contrasenia");//lanzamos una expecion de que no se pudo realizar el login
        }
    }
}
