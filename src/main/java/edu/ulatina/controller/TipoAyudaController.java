package edu.ulatina.controller;

import edu.ulatina.dao.TipoAyudaDAO;
import edu.ulatina.model.TipoAyuda;
import java.util.List;

public class TipoAyudaController {
    private TipoAyudaDAO tipoAyudaDAO;
    
    public TipoAyudaController() {
        this.tipoAyudaDAO = new TipoAyudaDAO();
    }
    
    public TipoAyuda registrarTipoAyuda(String nombre, String descripcion, 
                                        TipoAyuda.Categoria categoria) {
        try {
            TipoAyuda tipoAyuda = new TipoAyuda(nombre, descripcion, categoria);
            return tipoAyudaDAO.create(tipoAyuda);
        } catch (Exception e) {
            System.err.println("Error al registrar tipo de ayuda: " + e.getMessage());
            return null;
        }
    }
    
    public TipoAyuda buscarPorId(Integer id) {
        return tipoAyudaDAO.findById(id);
    }
    
    public List<TipoAyuda> listarTodos() {
        return tipoAyudaDAO.findAll();
    }
    
    public List<TipoAyuda> listarActivos() {
        return tipoAyudaDAO.findActivos();
    }
    
    public List<TipoAyuda> listarPorCategoria(TipoAyuda.Categoria categoria) {
        return tipoAyudaDAO.findByCategoria(categoria);
    }
    
    public TipoAyuda actualizarTipoAyuda(TipoAyuda tipoAyuda) {
        return tipoAyudaDAO.update(tipoAyuda);
    }
    
    public void desactivarTipoAyuda(Integer id) {
        TipoAyuda tipoAyuda = tipoAyudaDAO.findById(id);
        if (tipoAyuda != null) {
            tipoAyuda.setActivo(false);
            tipoAyudaDAO.update(tipoAyuda);
        }
    }
}
