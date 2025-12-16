package edu.ulatina.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

	public Usuario registrarUsuario(String nombreUsuario, String contrasena, String nombreCompleto, Rol rol,
			String email, Integer idParroquia, String telefono) {
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
	
	public void solicitarRecuperacionContrasena(String email) {

	    Usuario usuario = usuarioDAO.findByEmail(email);

	    if (usuario == null || !usuario.getActivo()) {
	        return;
	    }

	    String token = UUID.randomUUID().toString();

	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, 15);

	    usuario.setResetToken(token);
	    usuario.setResetTokenExpira(cal.getTime());

	    usuarioDAO.update(usuario);

	    UsuarioDAO.enviarRecuperacion(usuario.getEmail(), token);
	}
	
	public List<Usuario> listarActivos() {
        return usuarioDAO.findActivos();
    }
	
	public boolean restablecerContrasenaPorToken(String token, String nuevaContrasena) {

	    Usuario usuario = usuarioDAO.findByResetToken(token);

	    if (usuario == null) {
	        return false;
	    }

	    if (usuario.getResetTokenExpira().before(new Date())) {
	        return false;
	    }

	    usuario.setContrasena(nuevaContrasena);
	    usuario.setResetToken(null);
	    usuario.setResetTokenExpira(null);

	    usuarioDAO.update(usuario);
	    return true;
	}
	
	public boolean validarFortalezaContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 8) {
            return false;
        }
        
        // Verificar que tenga al menos una letra y un número
        boolean tieneLetra = false;
        boolean tieneNumero = false;
        
        for (char c : contrasena.toCharArray()) {
            if (Character.isLetter(c)) tieneLetra = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }
        
        return tieneLetra && tieneNumero;
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