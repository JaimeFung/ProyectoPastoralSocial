package edu.ulatina.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.ulatina.dao.MiembroFamiliaDAO;
import edu.ulatina.dao.FamiliaDAO;
import edu.ulatina.model.MiembroFamilia;
import edu.ulatina.model.Familia;

public class MiembroFamiliaController {
    private MiembroFamiliaDAO miembroDAO;
    private FamiliaDAO familiaDAO;
    
    public MiembroFamiliaController() {
        this.miembroDAO = new MiembroFamiliaDAO();
        this.familiaDAO = new FamiliaDAO();
    }
    
    /**
     * Registra un nuevo miembro de familia
     */
    public MiembroFamilia registrarMiembro(Integer idFamilia, String nombreCompleto,
                                           String identificacion, String fechaNacimientoStr,
                                           String generoStr, String estadoCivil,
                                           String telefono, String email,
                                           String parentescoStr) {
        try {
            // Buscar la familia
            Familia familia = familiaDAO.findById(idFamilia);
            if (familia == null) {
                System.err.println("Familia no encontrada");
                return null;
            }
            
            // Convertir fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
            
            // Convertir género
            MiembroFamilia.Genero genero = convertirGenero(generoStr);
            
            // Convertir parentesco
            MiembroFamilia.Parentesco parentesco = MiembroFamilia.Parentesco.valueOf(parentescoStr.toUpperCase());
            
            // Crear miembro
            MiembroFamilia miembro = new MiembroFamilia(familia, nombreCompleto, 
                                                        fechaNacimiento, parentesco, genero);
            miembro.setIdentificacion(identificacion);
            miembro.setEstadoCivil(estadoCivil);
            miembro.setTelefono(telefono);
            miembro.setEmail(email);
            
            // Guardar miembro
            miembro = miembroDAO.create(miembro);
            
            if (miembro != null) {
                // Actualizar contador de miembros en la familia
                actualizarContadorMiembros(idFamilia);
                System.out.println("✓ Miembro registrado: " + miembro.getNombreCompleto());
            }
            
            return miembro;
            
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error al registrar miembro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Registra al jefe de familia (miembro principal)
     */
    public MiembroFamilia registrarJefeFamilia(Integer idFamilia, String nombreCompleto,
                                               String identificacion, String fechaNacimientoStr,
                                               String generoStr, String estadoCivil,
                                               String telefono, String email) {
        return registrarMiembro(idFamilia, nombreCompleto, identificacion, 
                               fechaNacimientoStr, generoStr, estadoCivil,
                               telefono, email, "JEFE");
    }
    
    /**
     * Convierte el string del género del formulario al enum
     */
    private MiembroFamilia.Genero convertirGenero(String generoStr) {
        if (generoStr == null) return null;
        
        switch (generoStr.toLowerCase()) {
            case "masculino":
                return MiembroFamilia.Genero.MASCULINO;
            case "femenino":
                return MiembroFamilia.Genero.FEMENINO;
            case "otro":
                return MiembroFamilia.Genero.OTRO;
            default:
                return null;
        }
    }
    
    /**
     * Actualiza el contador de miembros en la familia
     */
    private void actualizarContadorMiembros(Integer idFamilia) {
        Familia familia = familiaDAO.findById(idFamilia);
        if (familia != null) {
            long count = miembroDAO.countByFamilia(idFamilia);
            familia.setCantidadMiembros((int) count);
            familiaDAO.update(familia);
        }
    }
    
    public MiembroFamilia buscarPorId(Integer id) {
        return miembroDAO.findById(id);
    }
    
    public List<MiembroFamilia> listarPorFamilia(Integer idFamilia) {
        return miembroDAO.findByFamilia(idFamilia);
    }
    
    public MiembroFamilia buscarJefeFamilia(Integer idFamilia) {
        return miembroDAO.findJefeFamilia(idFamilia);
    }
    
    public MiembroFamilia actualizarMiembro(MiembroFamilia miembro) {
        return miembroDAO.update(miembro);
    }
    
    public void eliminarMiembro(Integer id) {
        MiembroFamilia miembro = miembroDAO.findById(id);
        if (miembro != null) {
            Integer idFamilia = miembro.getFamilia().getIdFamilia();
            miembroDAO.delete(id);
            actualizarContadorMiembros(idFamilia);
        }
    }
}