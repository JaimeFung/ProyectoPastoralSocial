package edu.ulatina.controller;

import edu.ulatina.dao.ParroquiaDAO;
import edu.ulatina.model.Parroquia;
import java.util.List;

public class ParroquiaController {
    private ParroquiaDAO parroquiaDAO;
    
    public ParroquiaController() {
        this.parroquiaDAO = new ParroquiaDAO();
    }
    
    public Parroquia registrarParroquia(String nombre, String direccion, 
                                        String telefono, String parroco) {
        try {
            Parroquia parroquia = new Parroquia(nombre, direccion, telefono, parroco);
            return parroquiaDAO.create(parroquia);
        } catch (Exception e) {
            System.err.println("Error al registrar parroquia: " + e.getMessage());
            return null;
        }
    }
    
    public Parroquia buscarPorId(Integer id) {
        return parroquiaDAO.findById(id);
    }
    
    public List<Parroquia> listarTodas() {
        return parroquiaDAO.findAll();
    }
    
    public List<Parroquia> listarActivas() {
        return parroquiaDAO.findActivas();
    }
    
    public Parroquia actualizarParroquia(Parroquia parroquia) {
        return parroquiaDAO.update(parroquia);
    }
    
    public void eliminarParroquia(Integer id) {
        parroquiaDAO.delete(id);
    }
}
