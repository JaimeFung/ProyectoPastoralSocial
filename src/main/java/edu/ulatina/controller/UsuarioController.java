package edu.ulatina.controller;

import java.util.List;

import edu.ulatina.dao.UsuarioDAO;

import edu.ulatina.model.Parroquia;
import edu.ulatina.model.Usuario;
import edu.ulatina.model.Usuario.Rol;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private ParroquiaController parroquiaController;
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.parroquiaController = new ParroquiaController();
    }
    
    public Usuario registrarUsuario(
            String nombreUsuario,
            String contrasena,
            String nombreCompleto,
            Rol rol,
            String email,
            Integer idParroquia,
            String telefono
    ) {
        try {
            Parroquia parroquia = parroquiaController.buscarPorId(idParroquia);

            if (parroquia == null || !parroquia.getActiva()) {
                throw new IllegalArgumentException("Parroquia inválida");
            }

            Usuario usuario = new Usuario(nombreUsuario, contrasena, nombreCompleto, rol, email, parroquia, telefono);
           

            return usuarioDAO.create(usuario);

        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return null;
        }
    }

    
    public Usuario login(String nombreUsuario, String contrasena) {
        return usuarioDAO.login(nombreUsuario, contrasena);
    }
    public Usuario cambiarEstadoUsuario(Integer idUsuario) {
        Usuario usuario = usuarioDAO.findById(idUsuario);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        usuario.setActivo(!usuario.getActivo()); 

        return usuarioDAO.update(usuario);
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