package com.mycompany.login.persistencia;

import com.mycompany.login.logica.Rol;
import com.mycompany.login.logica.Usuario;
import com.mycompany.login.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    UsuarioJpaController usuarioController = new UsuarioJpaController();
    RolJpaController rolController = new RolJpaController();

    public List<Usuario> traerUsuarios() {
        return usuarioController.findUsuarioEntities();
    }

    public List<Rol> traerRolesBD() {
        return rolController.findRolEntities();
    }

    public void crearUsuarioBD(Usuario nuevo) {
        usuarioController.create(nuevo);
    }

    public void borrarUsuarioBD(int id_usuario) {
        try {
            usuarioController.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuarioBD(int id_usuario) {
        return usuarioController.findUsuario(id_usuario);
    }

    public void modificarBD(Usuario user) {
        try {
            usuarioController.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    
    
}
