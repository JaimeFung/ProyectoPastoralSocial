package edu.ulatina.controller;

import java.util.List;

import edu.ulatina.dao.ExpedienteDAO;
import edu.ulatina.dao.FamiliaDAO;
import edu.ulatina.dao.ParroquiaDAO;
import edu.ulatina.model.Expediente;
import edu.ulatina.model.Familia;
import edu.ulatina.model.Parroquia;

public class FamiliaController {
    private FamiliaDAO familiaDAO;
    private ParroquiaDAO parroquiaDAO;
    private ExpedienteDAO expedienteDAO;
    
    public FamiliaController() {
        this.familiaDAO = new FamiliaDAO();
        this.parroquiaDAO = new ParroquiaDAO();
        this.expedienteDAO = new ExpedienteDAO();
    }
    
    public Familia registrarFamilia(Integer idParroquia, String numeroExpediente, 
                                    String nombreJefe, String identificacion, String direccion,
                                    String telefono, Familia.SituacionEconomica situacion,
                                    String observaciones, String motivoExpediente) {
        try {
            // Buscar la parroquia
            Parroquia parroquia = parroquiaDAO.findById(idParroquia);
            if (parroquia == null) {
                System.err.println("Parroquia no encontrada");
                return null;
            }
            
            // Crear familia
            Familia familia = new Familia(parroquia, numeroExpediente, nombreJefe, 
                                         identificacion, direccion, situacion);
            familia.setTelefono(telefono);
            familia.setObservaciones(observaciones);
            
            // Guardar familia
            familia = familiaDAO.create(familia);
            
            // Crear expediente automáticamente
            if (familia != null && motivoExpediente != null) {
                Expediente expediente = new Expediente(familia, motivoExpediente);
                expedienteDAO.create(expediente);
                System.out.println("✓ Expediente creado para familia: " + familia.getNumeroExpediente());
            }
            
            return familia;
        } catch (Exception e) {
            System.err.println("Error al registrar familia: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Familia buscarPorId(Integer id) {
        return familiaDAO.findById(id);
    }
    
    public Familia buscarPorExpediente(String numeroExpediente) {
        return familiaDAO.findByNumeroExpediente(numeroExpediente);
    }
    
    public List<Familia> listarTodas() {
        return familiaDAO.findAll();
    }
    
    public List<Familia> listarActivas() {
        return familiaDAO.findActivas();
    }
    
    public List<Familia> listarPorParroquia(Integer idParroquia) {
        return familiaDAO.findByParroquia(idParroquia);
    }
    
    public List<Familia> buscarPorNombre(String nombre) {
        return familiaDAO.findByNombre(nombre);
    }
    
    public Familia actualizarFamilia(Familia familia) {
        return familiaDAO.update(familia);
    }
    
    public void eliminarFamilia(Integer id) {
        familiaDAO.delete(id);
    }
    
    public long contarFamilias() {
        return familiaDAO.count();
    }
}