package com.web.gestion;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.web.modelos.Usuario;
import com.web.dao.UsuarioDAO;

@Stateless
public class GestionUsuarios {

    @Inject
    private UsuarioDAO usuarioDao;

    public void guardarUsuario(Usuario usuario){
    	System.out.println("usuario = " + usuario);
        Usuario usr = usuarioDao.read(usuario.getId());
        System.out.println("usr = " + usr);
        if(usr == null)
            usuarioDao.insert(usuario);
        else
            usuarioDao.update(usuario);
    }
    
    public void actualizarUsuario(Usuario usuario) throws Exception{
    	Usuario usr = usuarioDao.read(usuario.getId());
    	if(usr != null) 
            usuarioDao.update(usuario);
        else
            throw new Exception("Usuario no existe");
    }

    public Usuario leerUsuario(int id){
        Usuario usr = usuarioDao.read(id);
        return usr;
    }

    public Usuario getUsuarioPorId(int id) throws Exception{
        Usuario usr = usuarioDao.getUsuarioPorId(id);
        if(usr == null)
            throw new Exception("No existe el usuario con id " + id);
        return usr;
    }

    public Usuario getUsuario(String usuario) throws Exception{
        Usuario usr = usuarioDao.getUsuario(usuario);
        if(usr == null)
            throw new Exception("No existe el usuario " + usuario);
        return usr;
    }

    public void borrarUsuario(int id){
        usuarioDao.remove(id);
    }

    public List<Usuario> getUsuarios(){
        return usuarioDao.getAll();
    }

    public Usuario login(String usuario, String pass) throws Exception{
        System.out.println("Usuario: " + usuario);
        System.out.println("Contrasenia: " + pass);
        Usuario usr = usuarioDao.login(usuario, pass);
        if(usr != null)
            return usr;
        else{
            throw new Exception("Error en el login, verificar el nombre de usuario y la contrasenia");
        }
    }
}
