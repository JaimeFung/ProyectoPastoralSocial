package edu.ulatina.controller;

import java.util.Date;
import java.util.List;
import edu.ulatina.dao.SeguimientoDAO;
import edu.ulatina.dao.ExpedienteDAO;
import edu.ulatina.model.Seguimiento;
import edu.ulatina.model.Expediente;

public class SeguimientoController {
    private SeguimientoDAO seguimientoDAO;
    private ExpedienteDAO expedienteDAO;
    
    public SeguimientoController() {
        this.seguimientoDAO = new SeguimientoDAO();
        this.expedienteDAO = new ExpedienteDAO();
    }
    
    /**
     * Registrar un nuevo seguimiento
     */
    public Seguimiento registrarSeguimiento(Integer idExpediente, String tipo, 
                                           String titulo, String descripcion, 
                                           String responsable) {
        try {
            Expediente expediente = expedienteDAO.findById(idExpediente);
            if (expediente == null) {
                System.err.println("Expediente no encontrado");
                return null;
            }
            
            Seguimiento seguimiento = new Seguimiento();
            seguimiento.setExpediente(expediente);
            seguimiento.setTipo(tipo);
            seguimiento.setTitulo(titulo);
            seguimiento.setDescripcion(descripcion);
            seguimiento.setResponsable(responsable);
            seguimiento.setFechaRegistro(new Date());
            
            seguimiento = seguimientoDAO.create(seguimiento);
            
            if (seguimiento != null) {
                System.out.println("✓ Seguimiento registrado para expediente: " + idExpediente);
            }
            
            return seguimiento;
        } catch (Exception e) {
            System.err.println("Error al registrar seguimiento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Buscar seguimiento por ID
     */
    public Seguimiento buscarPorId(Integer id) {
        return seguimientoDAO.findById(id);
    }
    
    /**
     * Listar todos los seguimientos de un expediente
     */
    public List<Seguimiento> listarPorExpediente(Integer idExpediente) {
        return seguimientoDAO.findByExpediente(idExpediente);
    }
    
    /**
     * Actualizar un seguimiento
     */
    public Seguimiento actualizarSeguimiento(Seguimiento seguimiento) {
        return seguimientoDAO.update(seguimiento);
    }
    
    /**
     * Eliminar un seguimiento
     */
    public void eliminarSeguimiento(Integer id) {
        seguimientoDAO.delete(id);
        System.out.println("✓ Seguimiento eliminado: " + id);
    }
}