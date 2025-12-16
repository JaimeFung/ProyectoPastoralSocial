package edu.ulatina.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "addendum")
public class Addendum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_addendum")
    private Integer idAddendum;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private Expediente expediente;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;
    
    // === PAGOS MENSUALES ACTUALES - SERVICIOS PÚBLICOS ===
    @Column(name = "electricidad_monto")
    private Double electricidadMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "electricidad_fecha1")
    private Date electricidadFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "electricidad_fecha2")
    private Date electricidadFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "electricidad_fecha3")
    private Date electricidadFecha3;
    
    @Column(name = "agua_monto")
    private Double aguaMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "agua_fecha1")
    private Date aguaFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "agua_fecha2")
    private Date aguaFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "agua_fecha3")
    private Date aguaFecha3;
    
    @Column(name = "telefono_monto")
    private Double telefonoMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "telefono_fecha1")
    private Date telefonoFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "telefono_fecha2")
    private Date telefonoFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "telefono_fecha3")
    private Date telefonoFecha3;
    
    @Column(name = "internet_monto")
    private Double internetMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "internet_fecha1")
    private Date internetFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "internet_fecha2")
    private Date internetFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "internet_fecha3")
    private Date internetFecha3;
    
    @Column(name = "cable_monto")
    private Double cableMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "cable_fecha1")
    private Date cableFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "cable_fecha2")
    private Date cableFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "cable_fecha3")
    private Date cableFecha3;
    
    // === GASTO DE VIVIENDA ===
    @Column(name = "alquiler_monto")
    private Double alquilerMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alquiler_fecha1")
    private Date alquilerFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alquiler_fecha2")
    private Date alquilerFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alquiler_fecha3")
    private Date alquilerFecha3;
    
    @Column(name = "alimentacion_monto")
    private Double alimentacionMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alimentacion_fecha1")
    private Date alimentacionFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alimentacion_fecha2")
    private Date alimentacionFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "alimentacion_fecha3")
    private Date alimentacionFecha3;
    
    // === GASTO DE SALUD ===
    @Column(name = "medicamento_monto")
    private Double medicamentoMonto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "medicamento_fecha1")
    private Date medicamentoFecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "medicamento_fecha2")
    private Date medicamentoFecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "medicamento_fecha3")
    private Date medicamentoFecha3;
    
    // === OTROS GASTOS ===
    @Column(name = "otro_gasto1_nombre", length = 255)
    private String otroGasto1Nombre;
    
    @Column(name = "otro_gasto1_monto")
    private Double otroGasto1Monto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto1_fecha1")
    private Date otroGasto1Fecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto1_fecha2")
    private Date otroGasto1Fecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto1_fecha3")
    private Date otroGasto1Fecha3;
    
    @Column(name = "otro_gasto2_nombre", length = 255)
    private String otroGasto2Nombre;
    
    @Column(name = "otro_gasto2_monto")
    private Double otroGasto2Monto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto2_fecha1")
    private Date otroGasto2Fecha1;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto2_fecha2")
    private Date otroGasto2Fecha2;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "otro_gasto2_fecha3")
    private Date otroGasto2Fecha3;
    
    // === OBSERVACIONES ===
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    // === RECOMENDACIÓN DE AYUDA ===
    @Column(name = "recomienda_ayuda")
    private Boolean recomiendaAyuda;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "recomendacion_fecha")
    private Date recomendacionFecha;
    
    // Constructores
    public Addendum() {
        this.fechaRegistro = new Date();
        this.recomiendaAyuda = false;
    }
    
    public Addendum(Expediente expediente) {
        this.expediente = expediente;
        this.fechaRegistro = new Date();
        this.recomiendaAyuda = false;
    }
    
    // Getters y Setters
    public Integer getIdAddendum() {
        return idAddendum;
    }
    
    public void setIdAddendum(Integer idAddendum) {
        this.idAddendum = idAddendum;
    }
    
    public Expediente getExpediente() {
        return expediente;
    }
    
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters para Electricidad
    public Double getElectricidadMonto() {
        return electricidadMonto;
    }

    public void setElectricidadMonto(Double electricidadMonto) {
        this.electricidadMonto = electricidadMonto;
    }

    public Date getElectricidadFecha1() {
        return electricidadFecha1;
    }

    public void setElectricidadFecha1(Date electricidadFecha1) {
        this.electricidadFecha1 = electricidadFecha1;
    }

    public Date getElectricidadFecha2() {
        return electricidadFecha2;
    }

    public void setElectricidadFecha2(Date electricidadFecha2) {
        this.electricidadFecha2 = electricidadFecha2;
    }

    public Date getElectricidadFecha3() {
        return electricidadFecha3;
    }

    public void setElectricidadFecha3(Date electricidadFecha3) {
        this.electricidadFecha3 = electricidadFecha3;
    }

    // Getters y Setters para Agua
    public Double getAguaMonto() {
        return aguaMonto;
    }

    public void setAguaMonto(Double aguaMonto) {
        this.aguaMonto = aguaMonto;
    }

    public Date getAguaFecha1() {
        return aguaFecha1;
    }

    public void setAguaFecha1(Date aguaFecha1) {
        this.aguaFecha1 = aguaFecha1;
    }

    public Date getAguaFecha2() {
        return aguaFecha2;
    }

    public void setAguaFecha2(Date aguaFecha2) {
        this.aguaFecha2 = aguaFecha2;
    }

    public Date getAguaFecha3() {
        return aguaFecha3;
    }

    public void setAguaFecha3(Date aguaFecha3) {
        this.aguaFecha3 = aguaFecha3;
    }

    // Getters y Setters para Teléfono
    public Double getTelefonoMonto() {
        return telefonoMonto;
    }

    public void setTelefonoMonto(Double telefonoMonto) {
        this.telefonoMonto = telefonoMonto;
    }

    public Date getTelefonoFecha1() {
        return telefonoFecha1;
    }

    public void setTelefonoFecha1(Date telefonoFecha1) {
        this.telefonoFecha1 = telefonoFecha1;
    }

    public Date getTelefonoFecha2() {
        return telefonoFecha2;
    }

    public void setTelefonoFecha2(Date telefonoFecha2) {
        this.telefonoFecha2 = telefonoFecha2;
    }

    public Date getTelefonoFecha3() {
        return telefonoFecha3;
    }

    public void setTelefonoFecha3(Date telefonoFecha3) {
        this.telefonoFecha3 = telefonoFecha3;
    }

    // Getters y Setters para Internet
    public Double getInternetMonto() {
        return internetMonto;
    }

    public void setInternetMonto(Double internetMonto) {
        this.internetMonto = internetMonto;
    }

    public Date getInternetFecha1() {
        return internetFecha1;
    }

    public void setInternetFecha1(Date internetFecha1) {
        this.internetFecha1 = internetFecha1;
    }

    public Date getInternetFecha2() {
        return internetFecha2;
    }

    public void setInternetFecha2(Date internetFecha2) {
        this.internetFecha2 = internetFecha2;
    }

    public Date getInternetFecha3() {
        return internetFecha3;
    }

    public void setInternetFecha3(Date internetFecha3) {
        this.internetFecha3 = internetFecha3;
    }

    // Getters y Setters para Cable
    public Double getCableMonto() {
        return cableMonto;
    }

    public void setCableMonto(Double cableMonto) {
        this.cableMonto = cableMonto;
    }

    public Date getCableFecha1() {
        return cableFecha1;
    }

    public void setCableFecha1(Date cableFecha1) {
        this.cableFecha1 = cableFecha1;
    }

    public Date getCableFecha2() {
        return cableFecha2;
    }

    public void setCableFecha2(Date cableFecha2) {
        this.cableFecha2 = cableFecha2;
    }

    public Date getCableFecha3() {
        return cableFecha3;
    }

    public void setCableFecha3(Date cableFecha3) {
        this.cableFecha3 = cableFecha3;
    }

    // Getters y Setters para Alquiler
    public Double getAlquilerMonto() {
        return alquilerMonto;
    }

    public void setAlquilerMonto(Double alquilerMonto) {
        this.alquilerMonto = alquilerMonto;
    }

    public Date getAlquilerFecha1() {
        return alquilerFecha1;
    }

    public void setAlquilerFecha1(Date alquilerFecha1) {
        this.alquilerFecha1 = alquilerFecha1;
    }

    public Date getAlquilerFecha2() {
        return alquilerFecha2;
    }

    public void setAlquilerFecha2(Date alquilerFecha2) {
        this.alquilerFecha2 = alquilerFecha2;
    }

    public Date getAlquilerFecha3() {
        return alquilerFecha3;
    }

    public void setAlquilerFecha3(Date alquilerFecha3) {
        this.alquilerFecha3 = alquilerFecha3;
    }

    // Getters y Setters para Alimentación
    public Double getAlimentacionMonto() {
        return alimentacionMonto;
    }

    public void setAlimentacionMonto(Double alimentacionMonto) {
        this.alimentacionMonto = alimentacionMonto;
    }

    public Date getAlimentacionFecha1() {
        return alimentacionFecha1;
    }

    public void setAlimentacionFecha1(Date alimentacionFecha1) {
        this.alimentacionFecha1 = alimentacionFecha1;
    }

    public Date getAlimentacionFecha2() {
        return alimentacionFecha2;
    }

    public void setAlimentacionFecha2(Date alimentacionFecha2) {
        this.alimentacionFecha2 = alimentacionFecha2;
    }

    public Date getAlimentacionFecha3() {
        return alimentacionFecha3;
    }

    public void setAlimentacionFecha3(Date alimentacionFecha3) {
        this.alimentacionFecha3 = alimentacionFecha3;
    }

    // Getters y Setters para Medicamento
    public Double getMedicamentoMonto() {
        return medicamentoMonto;
    }

    public void setMedicamentoMonto(Double medicamentoMonto) {
        this.medicamentoMonto = medicamentoMonto;
    }

    public Date getMedicamentoFecha1() {
        return medicamentoFecha1;
    }

    public void setMedicamentoFecha1(Date medicamentoFecha1) {
        this.medicamentoFecha1 = medicamentoFecha1;
    }

    public Date getMedicamentoFecha2() {
        return medicamentoFecha2;
    }

    public void setMedicamentoFecha2(Date medicamentoFecha2) {
        this.medicamentoFecha2 = medicamentoFecha2;
    }

    public Date getMedicamentoFecha3() {
        return medicamentoFecha3;
    }

    public void setMedicamentoFecha3(Date medicamentoFecha3) {
        this.medicamentoFecha3 = medicamentoFecha3;
    }

    // Getters y Setters para Otros Gastos
    public String getOtroGasto1Nombre() {
        return otroGasto1Nombre;
    }

    public void setOtroGasto1Nombre(String otroGasto1Nombre) {
        this.otroGasto1Nombre = otroGasto1Nombre;
    }

    public Double getOtroGasto1Monto() {
        return otroGasto1Monto;
    }

    public void setOtroGasto1Monto(Double otroGasto1Monto) {
        this.otroGasto1Monto = otroGasto1Monto;
    }

    public Date getOtroGasto1Fecha1() {
        return otroGasto1Fecha1;
    }

    public void setOtroGasto1Fecha1(Date otroGasto1Fecha1) {
        this.otroGasto1Fecha1 = otroGasto1Fecha1;
    }

    public Date getOtroGasto1Fecha2() {
        return otroGasto1Fecha2;
    }

    public void setOtroGasto1Fecha2(Date otroGasto1Fecha2) {
        this.otroGasto1Fecha2 = otroGasto1Fecha2;
    }

    public Date getOtroGasto1Fecha3() {
        return otroGasto1Fecha3;
    }

    public void setOtroGasto1Fecha3(Date otroGasto1Fecha3) {
        this.otroGasto1Fecha3 = otroGasto1Fecha3;
    }

    public String getOtroGasto2Nombre() {
        return otroGasto2Nombre;
    }

    public void setOtroGasto2Nombre(String otroGasto2Nombre) {
        this.otroGasto2Nombre = otroGasto2Nombre;
    }

    public Double getOtroGasto2Monto() {
        return otroGasto2Monto;
    }

    public void setOtroGasto2Monto(Double otroGasto2Monto) {
        this.otroGasto2Monto = otroGasto2Monto;
    }

    public Date getOtroGasto2Fecha1() {
        return otroGasto2Fecha1;
    }

    public void setOtroGasto2Fecha1(Date otroGasto2Fecha1) {
        this.otroGasto2Fecha1 = otroGasto2Fecha1;
    }

    public Date getOtroGasto2Fecha2() {
        return otroGasto2Fecha2;
    }

    public void setOtroGasto2Fecha2(Date otroGasto2Fecha2) {
        this.otroGasto2Fecha2 = otroGasto2Fecha2;
    }

    public Date getOtroGasto2Fecha3() {
        return otroGasto2Fecha3;
    }

    public void setOtroGasto2Fecha3(Date otroGasto2Fecha3) {
        this.otroGasto2Fecha3 = otroGasto2Fecha3;
    }

    // Getters y Setters para Observaciones y Recomendación
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getRecomiendaAyuda() {
        return recomiendaAyuda;
    }

    public void setRecomiendaAyuda(Boolean recomiendaAyuda) {
        this.recomiendaAyuda = recomiendaAyuda;
    }

    public Date getRecomendacionFecha() {
        return recomendacionFecha;
    }

    public void setRecomendacionFecha(Date recomendacionFecha) {
        this.recomendacionFecha = recomendacionFecha;
    }
    
    @Override
    public String toString() {
        return "Addendum{" +
                "idAddendum=" + idAddendum +
                ", fechaRegistro=" + fechaRegistro +
                ", recomiendaAyuda=" + recomiendaAyuda +
                '}';
    }
}