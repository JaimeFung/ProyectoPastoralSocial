package edu.ulatina.controller;

import java.util.List;

import edu.ulatina.dao.UsuarioDAO;
import edu.ulatina.model.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario registrarUsuario(String nombreUsuario, String contrasena, 
                                    String nombreCompleto, Usuario.Rol rol, String email) {
        try {
            Usuario usuario = new Usuario(nombreUsuario, contrasena, nombreCompleto, rol);
            usuario.setEmail(email);
            return usuarioDAO.create(usuario);
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return null;
        }
    }
    
    public Usuario login(String nombreUsuario, String contrasena) {
        return usuarioDAO.login(nombreUsuario, contrasena);
    }
    
    public Usuario buscarPorId(Integer id) {
        return usuarioDAO.findById(id);
    }
    
    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }
    
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }
    
    public void eliminarUsuario(Integer id) {
        usuarioDAO.delete(id);
    }
    
    public List<Usuario> listarPorRol(Usuario.Rol rol) {
        return usuarioDAO.findByRol(rol);
    }
}