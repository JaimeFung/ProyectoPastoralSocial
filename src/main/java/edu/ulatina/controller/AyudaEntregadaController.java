package edu.ulatina.controller;

import edu.ulatina.dao.AyudaEntregadaDAO;
import edu.ulatina.dao.ExpedienteDAO;
import edu.ulatina.dao.TipoAyudaDAO;
import edu.ulatina.dao.UsuarioDAO;
import edu.ulatina.model.AyudaEntregada;
import edu.ulatina.model.Expediente;
import edu.ulatina.model.TipoAyuda;
import edu.ulatina.model.Usuario;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AyudaEntregadaController {
    private AyudaEntregadaDAO ayudaEntregadaDAO;
    private ExpedienteDAO expedienteDAO;
    private TipoAyudaDAO tipoAyudaDAO;
    private UsuarioDAO usuarioDAO;
    
    public AyudaEntregadaController() {
        this.ayudaEntregadaDAO = new AyudaEntregadaDAO();
        this.expedienteDAO = new ExpedienteDAO();
        this.tipoAyudaDAO = new TipoAyudaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Registra una nueva ayuda entregada
     */
    /**
     * Registra una nueva ayuda entregada
     */
    public AyudaEntregada registrarAyuda(Integer idExpediente, Integer idTipoAyuda, 
                                         Integer idUsuario, String fechaEntrega,
                                         String descripcion, String cantidad, 
                                         String valorEstimado, String observaciones) {
        try {
            // Validar datos requeridos
            if (idExpediente == null || idTipoAyuda == null || idUsuario == null || 
                fechaEntrega == null || descripcion == null || descripcion.trim().isEmpty()) {
                System.err.println("Error: Faltan datos requeridos");
                return null;
            }
            
            // Obtener entidades relacionadas
            Expediente expediente = expedienteDAO.findById(idExpediente);
            if (expediente == null) {
                System.err.println("Error: Expediente no encontrado");
                return null;
            }
            
            TipoAyuda tipoAyuda = tipoAyudaDAO.findById(idTipoAyuda);
            if (tipoAyuda == null) {
                System.err.println("Error: Tipo de ayuda no encontrado");
                return null;
            }
            
            Usuario usuario = usuarioDAO.findById(idUsuario);
            if (usuario == null) {
                System.err.println("Error: Usuario no encontrado");
                return null;
            }
            
            // Convertir fecha de String a Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaEntrega);
            
            // Crear nueva ayuda entregada
            AyudaEntregada ayuda = new AyudaEntregada();
            ayuda.setExpediente(expediente);
            ayuda.setTipoAyuda(tipoAyuda);
            ayuda.setUsuario(usuario);
            ayuda.setFechaEntrega(fecha);
            ayuda.setDescripcion(descripcion.trim());
            
            // Datos opcionales
            if (cantidad != null && !cantidad.trim().isEmpty()) {
                ayuda.setCantidad(cantidad.trim());
            }
            
            if (valorEstimado != null && !valorEstimado.trim().isEmpty()) {
                try {
                    ayuda.setValorEstimado(new BigDecimal(valorEstimado));
                } catch (NumberFormatException e) {
                    System. err.println("Advertencia: Valor estimado inválido, se ignorará");
                }
            }
            
            if (observaciones != null && !observaciones.trim().isEmpty()) {
                ayuda.setObservaciones(observaciones.trim());
            }
            
            // Guardar en la base de datos
            AyudaEntregada ayudaCreada = ayudaEntregadaDAO.create(ayuda);
            
            // ACTUALIZAR EL CONTADOR DEL EXPEDIENTE
            if (ayudaCreada != null) {
                Integer totalActual = expediente.getTotalAyudasRecibidas();
                if (totalActual == null) {
                    totalActual = 0;
                }
                expediente.setTotalAyudasRecibidas(totalActual + 1);
                expedienteDAO.update(expediente);
            }
            
            return ayudaCreada;
            
        } catch (Exception e) {
            System.err.println("Error al registrar ayuda: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca una ayuda por su ID
     */
    public AyudaEntregada buscarPorId(Integer id) {
        return ayudaEntregadaDAO.findById(id);
    }
    
    /**
     * Lista todas las ayudas de un expediente
     */
    public List<AyudaEntregada> listarPorExpediente(Integer idExpediente) {
        return ayudaEntregadaDAO.findByExpediente(idExpediente);
    }
    
    /**
     * Lista todas las ayudas de un tipo específico
     */
    public List<AyudaEntregada> listarPorTipoAyuda(Integer idTipoAyuda) {
        return ayudaEntregadaDAO.findByTipoAyuda(idTipoAyuda);
    }
    
    /**
     * Lista todas las ayudas registradas
     */
    public List<AyudaEntregada> listarTodas() {
        return ayudaEntregadaDAO.findAll();
    }
    
    /**
     * Actualiza una ayuda existente
     */
    public AyudaEntregada actualizarAyuda(AyudaEntregada ayuda) {
        return ayudaEntregadaDAO.update(ayuda);
    }
    
    /**
     * Elimina una ayuda por su ID
     */
    public void eliminarAyuda(Integer id) {
        ayudaEntregadaDAO.delete(id);
    }
    
    /**
     * Cuenta el número de ayudas de un expediente
     */
    public long contarPorExpediente(Integer idExpediente) {
        return ayudaEntregadaDAO.countByExpediente(idExpediente);
    }
}