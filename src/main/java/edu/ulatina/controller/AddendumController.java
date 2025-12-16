package edu.ulatina.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import edu.ulatina.dao.AddendumDAO;
import edu.ulatina.dao.ExpedienteDAO;
import edu.ulatina.model.Addendum;
import edu.ulatina.model.Expediente;

public class AddendumController {
    private AddendumDAO addendumDAO;
    private ExpedienteDAO expedienteDAO;
    private SimpleDateFormat sdf;
    
    public AddendumController() {
        this.addendumDAO = new AddendumDAO();
        this.expedienteDAO = new ExpedienteDAO();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    /**
     * Crear un addendum completo desde el formulario
     */
    public Addendum crearAddendumCompleto(
            Integer idExpediente,
            // Electricidad
            String electricidadMonto, String electricidadFecha1, 
            String electricidadFecha2, String electricidadFecha3,
            // Agua
            String aguaMonto, String aguaFecha1, 
            String aguaFecha2, String aguaFecha3,
            // Teléfono
            String telefonoMonto, String telefonoFecha1, 
            String telefonoFecha2, String telefonoFecha3,
            // Internet
            String internetMonto, String internetFecha1, 
            String internetFecha2, String internetFecha3,
            // Cable
            String cableMonto, String cableFecha1, 
            String cableFecha2, String cableFecha3,
            // Alquiler
            String alquilerMonto, String alquilerFecha1, 
            String alquilerFecha2, String alquilerFecha3,
            // Alimentación
            String alimentacionMonto, String alimentacionFecha1, 
            String alimentacionFecha2, String alimentacionFecha3,
            // Medicamento
            String medicamentoMonto, String medicamentoFecha1, 
            String medicamentoFecha2, String medicamentoFecha3,
            // Otros gastos
            String otroGasto1Nombre, String otroGasto1Monto,
            String otroGasto1Fecha1, String otroGasto1Fecha2, String otroGasto1Fecha3,
            String otroGasto2Nombre, String otroGasto2Monto,
            String otroGasto2Fecha1, String otroGasto2Fecha2, String otroGasto2Fecha3,
            // Observaciones
            String observaciones,
            // Recomendación
            String recomiendaAyuda, String recomendacionFecha
    ) {
        try {
            Expediente expediente = expedienteDAO.findById(idExpediente);
            if (expediente == null) {
                System.err.println("Expediente no encontrado");
                return null;
            }
            
            Addendum addendum = new Addendum(expediente);
            
            // Electricidad
            addendum.setElectricidadMonto(parseMonto(electricidadMonto));
            addendum.setElectricidadFecha1(parseFecha(electricidadFecha1));
            addendum.setElectricidadFecha2(parseFecha(electricidadFecha2));
            addendum.setElectricidadFecha3(parseFecha(electricidadFecha3));
            
            // Agua
            addendum.setAguaMonto(parseMonto(aguaMonto));
            addendum.setAguaFecha1(parseFecha(aguaFecha1));
            addendum.setAguaFecha2(parseFecha(aguaFecha2));
            addendum.setAguaFecha3(parseFecha(aguaFecha3));
            
            // Teléfono
            addendum.setTelefonoMonto(parseMonto(telefonoMonto));
            addendum.setTelefonoFecha1(parseFecha(telefonoFecha1));
            addendum.setTelefonoFecha2(parseFecha(telefonoFecha2));
            addendum.setTelefonoFecha3(parseFecha(telefonoFecha3));
            
            // Internet
            addendum.setInternetMonto(parseMonto(internetMonto));
            addendum.setInternetFecha1(parseFecha(internetFecha1));
            addendum.setInternetFecha2(parseFecha(internetFecha2));
            addendum.setInternetFecha3(parseFecha(internetFecha3));
            
            // Cable
            addendum.setCableMonto(parseMonto(cableMonto));
            addendum.setCableFecha1(parseFecha(cableFecha1));
            addendum.setCableFecha2(parseFecha(cableFecha2));
            addendum.setCableFecha3(parseFecha(cableFecha3));
            
            // Alquiler
            addendum.setAlquilerMonto(parseMonto(alquilerMonto));
            addendum.setAlquilerFecha1(parseFecha(alquilerFecha1));
            addendum.setAlquilerFecha2(parseFecha(alquilerFecha2));
            addendum.setAlquilerFecha3(parseFecha(alquilerFecha3));
            
            // Alimentación
            addendum.setAlimentacionMonto(parseMonto(alimentacionMonto));
            addendum.setAlimentacionFecha1(parseFecha(alimentacionFecha1));
            addendum.setAlimentacionFecha2(parseFecha(alimentacionFecha2));
            addendum.setAlimentacionFecha3(parseFecha(alimentacionFecha3));
            
            // Medicamento
            addendum.setMedicamentoMonto(parseMonto(medicamentoMonto));
            addendum.setMedicamentoFecha1(parseFecha(medicamentoFecha1));
            addendum.setMedicamentoFecha2(parseFecha(medicamentoFecha2));
            addendum.setMedicamentoFecha3(parseFecha(medicamentoFecha3));
            
            // Otros gastos
            addendum.setOtroGasto1Nombre(otroGasto1Nombre);
            addendum.setOtroGasto1Monto(parseMonto(otroGasto1Monto));
            addendum.setOtroGasto1Fecha1(parseFecha(otroGasto1Fecha1));
            addendum.setOtroGasto1Fecha2(parseFecha(otroGasto1Fecha2));
            addendum.setOtroGasto1Fecha3(parseFecha(otroGasto1Fecha3));
            
            addendum.setOtroGasto2Nombre(otroGasto2Nombre);
            addendum.setOtroGasto2Monto(parseMonto(otroGasto2Monto));
            addendum.setOtroGasto2Fecha1(parseFecha(otroGasto2Fecha1));
            addendum.setOtroGasto2Fecha2(parseFecha(otroGasto2Fecha2));
            addendum.setOtroGasto2Fecha3(parseFecha(otroGasto2Fecha3));
            
            // Observaciones
            addendum.setObservaciones(observaciones);
            
            // Recomendación
            addendum.setRecomiendaAyuda("si".equalsIgnoreCase(recomiendaAyuda));
            addendum.setRecomendacionFecha(parseFecha(recomendacionFecha));
            
            addendum = addendumDAO.create(addendum);
            
            if (addendum != null) {
                System.out.println("✓ Addendum creado para expediente: " + idExpediente);
            }
            
            return addendum;
        } catch (Exception e) {
            System.err.println("Error al crear addendum: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Buscar addendum por ID
     */
    public Addendum buscarPorId(Integer id) {
        return addendumDAO.findById(id);
    }
    
    /**
     * Listar todos los addendums de un expediente
     */
    public List<Addendum> listarPorExpediente(Integer idExpediente) {
        return addendumDAO.findByExpediente(idExpediente);
    }
    
    /**
     * Actualizar un addendum
     */
    public Addendum actualizarAddendum(Addendum addendum) {
        return addendumDAO.update(addendum);
    }
    
    /**
     * Eliminar un addendum
     */
    public void eliminarAddendum(Integer id) {
        addendumDAO.delete(id);
        System.out.println("✓ Addendum eliminado: " + id);
    }
    
    // Métodos auxiliares para parsear datos
    
    private Double parseMonto(String monto) {
        if (monto == null || monto.trim().isEmpty()) {
            return null;
        }
        try {
            // Remover símbolos de moneda y espacios
            String cleanMonto = monto.replaceAll("[₡\\$,\\s]", "");
            return Double.parseDouble(cleanMonto);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private Date parseFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return null;
        }
        try {
            return sdf.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }
}