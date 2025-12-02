package edu.ulatina.controller;

import java.util.List;

import edu.ulatina.dao.ExpedienteDAO;
import edu.ulatina.dao.FamiliaDAO;
import edu.ulatina.model.Expediente;
import edu.ulatina.model.Familia;

public class ExpedienteController {
    private ExpedienteDAO expedienteDAO;
    private FamiliaDAO familiaDAO;
    
    public ExpedienteController() {
        this.expedienteDAO = new ExpedienteDAO();
        this.familiaDAO = new FamiliaDAO();
    }
    
    public Expediente crearExpediente(Integer idFamilia, String motivoApertura) {
        try {
            Familia familia = familiaDAO.findById(idFamilia);
            if (familia == null) {
                System.err.println("Familia no encontrada");
                return null;
            }
            
            // Verificar si ya existe expediente
            Expediente existente = expedienteDAO.findByFamilia(idFamilia);
            if (existente != null) {
                System.out.println("⚠ Ya existe un expediente para esta familia");
                return existente;
            }
            
            Expediente expediente = new Expediente(familia, motivoApertura);
            return expedienteDAO.create(expediente);
        } catch (Exception e) {
            System.err.println("Error al crear expediente: " + e.getMessage());
            return null;
        }
    }
    
    public Expediente buscarPorId(Integer id) {
        return expedienteDAO.findById(id);
    }
    
    public Expediente buscarPorFamilia(Integer idFamilia) {
        return expedienteDAO.findByFamilia(idFamilia);
    }
    
    public List<Expediente> listarTodos() {
        return expedienteDAO.findAll();
    }
    
    public List<Expediente> listarPorEstado(Expediente.Estado estado) {
        return expedienteDAO.findByEstado(estado);
    }
    
    public Expediente actualizarExpediente(Expediente expediente) {
        return expedienteDAO.update(expediente);
    }
    
    public void eliminarExpediente(Integer id) {
        expedienteDAO.delete(id);
    }
    
    public void cerrarExpediente(Integer id, String notas) {
        Expediente expediente = expedienteDAO.findById(id);
        if (expediente != null) {
            expediente.setEstado(Expediente.Estado.CERRADO);
            expediente.setNotasGenerales(notas);
            expedienteDAO.update(expediente);
            System.out.println("✓ Expediente cerrado: " + id);
        }
    }
}