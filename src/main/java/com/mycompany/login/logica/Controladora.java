package com.mycompany.login.logica;
import com.mycompany.login.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {
    
    ControladoraPersistencia controlPersis = null;
    
    public Controladora(){
        controlPersis = new ControladoraPersistencia();
    }
    
    public Usuario validarUsuario(String usuario, String contrasenia) {
        
        Usuario user = null;
        List <Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for(Usuario actual : listaUsuarios){
            if(actual.getNombreUsuario().equals(usuario)){
                if(actual.getContraseña().equals(contrasenia)){
                    user = actual;
                    return user;
                }
                else{
                    user = null;
                    return user;
                }        
            }
            else{
                user = null;
            }
        }
        return user;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRolesBD();
    }

    public void crearUsuario(String usuario, String contrasenia, String rol) {
        Usuario nuevo = new Usuario();
        nuevo.setNombreUsuario(usuario);
        nuevo.setContraseña(contrasenia);
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rol);
        if(rolEncontrado != null){
            nuevo.setUnRol(rolEncontrado);
        }
        
        int id = this.buscarUltimaIDUsuario();
        nuevo.setId(id + 1);
        
        controlPersis.crearUsuarioBD(nuevo);
        
    }

    private Rol traerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRolesBD();
        
        for(Rol actual : listaRoles){
            if(actual.getNombreRol().equals(rolRecibido))
                return actual;
        }
        return null;        
    }


    private int buscarUltimaIDUsuario() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        Usuario ultimo = listaUsuarios.get(listaUsuarios.size() - 1);
        
        return ultimo.getId();       
    }


    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuarioBD(id_usuario);
    }

    public Usuario traerUsuario(int id_usuario) {
        return controlPersis.traerUsuarioBD(id_usuario);
    }

    public void editarUsuario(Usuario user, String usuario, String contrasenia, String rol) {
        user.setNombreUsuario(usuario);
        user.setContraseña(contrasenia);
        
        Rol nuevoRol = new Rol();
        nuevoRol = this.traerRol(rol);
        if(nuevoRol != null)
            user.setUnRol(nuevoRol);
        
       controlPersis.modificarBD(user);
    }

    
}
