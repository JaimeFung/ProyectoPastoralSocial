package edu.ulatina.controller;

import edu.ulatina.dao.*;
import edu.ulatina.model.*;
import java.util.*;

public class DashboardController {
    
    private FamiliaDAO familiaDAO;
    private ExpedienteDAO expedienteDAO;
    private AyudaEntregadaDAO ayudaDAO;
    private ParroquiaDAO parroquiaDAO;
    
    public DashboardController() {
        this.familiaDAO = new FamiliaDAO();
        this.expedienteDAO = new ExpedienteDAO();
        this.ayudaDAO = new AyudaEntregadaDAO();
        this.parroquiaDAO = new ParroquiaDAO();
    }
    
    /**
     * Obtiene estadísticas generales del sistema
     */
    public Map<String, Long> obtenerEstadisticas() {
        Map<String, Long> stats = new HashMap<>();
        
        // Total de beneficiarios (familias activas)
        long totalBeneficiarios = familiaDAO.countActivas();
        stats.put("totalBeneficiarios", totalBeneficiarios);
        
        // Expedientes activos
        long expedientesActivos = expedienteDAO.countActivos();
        stats.put("expedientesActivos", expedientesActivos);
        
        // Ayudas del mes actual
        long ayudasMesActual = ayudaDAO.countAyudasMesActual();
        stats.put("ayudasMesActual", ayudasMesActual);
        
        // Parroquias activas
        long parroquiasActivas = parroquiaDAO.countActivas();
        stats.put("parroquiasActivas", parroquiasActivas);
        
        return stats;
    }
    
    
    
    /**
     * Obtiene actividades recientes del sistema
     */
    public List<Map<String, Object>> obtenerActividadesRecientes(int limite) {
        List<Map<String, Object>> actividades = new ArrayList<>();
        
        // Obtener últimas ayudas entregadas
        List<AyudaEntregada> ayudas = ayudaDAO.findRecientes(limite);
        
        for (AyudaEntregada ayuda : ayudas) {
            Map<String, Object> actividad = new HashMap<>();
            actividad.put("tipo", "ayuda");
            actividad.put("titulo", "Ayuda entregada: " + ayuda.getTipoAyuda().getNombre());
            actividad.put("beneficiario", ayuda.getExpediente().getFamilia().getNombreJefeFamilia());
            actividad.put("fecha", ayuda.getFechaEntrega());
            actividades.add(actividad);
        }
        
        return actividades;
    }
    
    /**
     * Obtiene distribución de beneficiarios por parroquia
     */
    public List<Map<String, Object>> obtenerDistribucionParroquias() {
        return parroquiaDAO.obtenerDistribucionBeneficiarios();
    }
}