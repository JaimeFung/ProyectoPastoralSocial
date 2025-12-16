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
    private MiembroFamiliaController miembroController;
    
    public FamiliaController() {
        this.familiaDAO = new FamiliaDAO();
        this.parroquiaDAO = new ParroquiaDAO();
        this.expedienteDAO = new ExpedienteDAO();
        this.miembroController = new MiembroFamiliaController();
    }
    
    /**
     * Registra una familia completa con el jefe de familia
     * Este método ahora también crea el miembro principal (jefe de familia)
     */
    public Familia registrarFamiliaCompleta(
            // Datos de la familia
            Integer idParroquia, 
            String numeroExpediente,
            String direccion,
            String telefono,
            Familia.SituacionEconomica situacion,
            String observaciones,
            String motivoExpediente,
            // Datos del jefe de familia (información personal)
            String nombre,
            String primerApellido,
            String segundoApellido,
            String identificacion,
            String fechaNacimiento,
            String genero,
            String estadoCivil,
            String email) {
        
        try {
            // 1. Buscar la parroquia
            Parroquia parroquia = parroquiaDAO.findById(idParroquia);
            if (parroquia == null) {
                System.err.println("Parroquia no encontrada");
                return null;
            }
            
            // 2. Crear el nombre completo del jefe para la familia
            String nombreCompletoJefe = nombre + " " + primerApellido + " " + segundoApellido;
            
            // 3. Crear la familia
            Familia familia = new Familia(parroquia, numeroExpediente, nombreCompletoJefe, 
                                         identificacion, direccion, situacion);
            familia.setTelefono(telefono);
            familia.setObservaciones(observaciones);
            
            // 4. Guardar familia
            familia = familiaDAO.create(familia);
            
            if (familia == null) {
                System.err.println("Error al crear la familia");
                return null;
            }
            
            System.out.println("✓ Familia creada: " + familia.getNumeroExpediente());
            
            // 5. Crear el registro del jefe de familia en MiembroFamilia
            miembroController.registrarJefeFamilia(
                familia.getIdFamilia(),
                nombre,
                primerApellido,
                segundoApellido,
                identificacion,
                fechaNacimiento,
                genero,
                estadoCivil,
                telefono,
                email
            );
            
            // 6. Crear expediente automáticamente
            if (motivoExpediente != null && !motivoExpediente.trim().isEmpty()) {
                Expediente expediente = new Expediente(familia, motivoExpediente);
                expedienteDAO.create(expediente);
                System.out.println("✓ Expediente creado para familia: " + familia.getNumeroExpediente());
            }
            
            return familia;
            
        } catch (Exception e) {
            System.err.println("Error al registrar familia completa: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método original mantenido para compatibilidad
     */
    public Familia registrarFamilia(Integer idParroquia, String numeroExpediente, 
                                    String nombreJefe, String identificacion, String direccion,
                                    String telefono, Familia.SituacionEconomica situacion,
                                    String observaciones, String motivoExpediente) {
        try {
            Parroquia parroquia = parroquiaDAO.findById(idParroquia);
            if (parroquia == null) {
                System.err.println("Parroquia no encontrada");
                return null;
            }
            
            Familia familia = new Familia(parroquia, numeroExpediente, nombreJefe, 
                                         identificacion, direccion, situacion);
            familia.setTelefono(telefono);
            familia.setObservaciones(observaciones);
            
            familia = familiaDAO.create(familia);
            
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