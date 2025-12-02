package edu.ulatina.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Expediente
 *
 */
@Entity

public class Expediente implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_expediente")
	    private Integer idExpediente;
	    
	    @OneToOne
	    @JoinColumn(name = "id_familia", nullable = false, unique = true)
	    private Familia familia;
	    
	    @Temporal(TemporalType.DATE)
	    @Column(name = "fecha_apertura", nullable = false)
	    private Date fechaApertura;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Estado estado = Estado.ACTIVO;
	    
	    @Column(name = "motivo_apertura", nullable = false, columnDefinition = "TEXT")
	    private String motivoApertura;
	    
	    @Column(name = "notas_generales", columnDefinition = "TEXT")
	    private String notasGenerales;
	    
	    @Temporal(TemporalType.DATE)
	    @Column(name = "fecha_ultima_ayuda")
	    private Date fechaUltimaAyuda;
	    
	    @Column(name = "total_ayudas_recibidas")
	    private Integer totalAyudasRecibidas = 0;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "fecha_creacion", updatable = false)
	    private Date fechaCreacion;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "fecha_modificacion")
	    private Date fechaModificacion;
	    
	    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<AyudaEntregada> ayudas;
	    
	    public enum Estado {
	        ACTIVO, INACTIVO, CERRADO
	    }
	    
	    @PrePersist
	    protected void onCreate() {
	        fechaCreacion = new Date();
	        fechaModificacion = new Date();
	        if (fechaApertura == null) {
	            fechaApertura = new Date();
	        }
	    }
	    
	    @PreUpdate
	    protected void onUpdate() {
	        fechaModificacion = new Date();
	    }
	    
	    // Constructores
	    public Expediente() {}
	    
	    public Expediente(Familia familia, String motivoApertura) {
	        this.familia = familia;
	        this.motivoApertura = motivoApertura;
	        this.fechaApertura = new Date();
	    }
	    
	    // Getters y Setters
	    public Integer getIdExpediente() { return idExpediente; }
	    public void setIdExpediente(Integer idExpediente) { this.idExpediente = idExpediente; }
	    
	    public Familia getFamilia() { return familia; }
	    public void setFamilia(Familia familia) { this.familia = familia; }
	    
	    public Date getFechaApertura() { return fechaApertura; }
	    public void setFechaApertura(Date fechaApertura) { this.fechaApertura = fechaApertura; }
	    
	    public Estado getEstado() { return estado; }
	    public void setEstado(Estado estado) { this.estado = estado; }
	    
	    public String getMotivoApertura() { return motivoApertura; }
	    public void setMotivoApertura(String motivoApertura) { this.motivoApertura = motivoApertura; }
	    
	    public String getNotasGenerales() { return notasGenerales; }
	    public void setNotasGenerales(String notasGenerales) { this.notasGenerales = notasGenerales; }
	    
	    public Date getFechaUltimaAyuda() { return fechaUltimaAyuda; }
	    public void setFechaUltimaAyuda(Date fechaUltimaAyuda) { this.fechaUltimaAyuda = fechaUltimaAyuda; }
	    
	    public Integer getTotalAyudasRecibidas() { return totalAyudasRecibidas; }
	    public void setTotalAyudasRecibidas(Integer totalAyudasRecibidas) { this.totalAyudasRecibidas = totalAyudasRecibidas; }
	    
	    public Date getFechaCreacion() { return fechaCreacion; }
	    public Date getFechaModificacion() { return fechaModificacion; }
	    
	    public List<AyudaEntregada> getAyudas() { return ayudas; }
	    public void setAyudas(List<AyudaEntregada> ayudas) { this.ayudas = ayudas; }
	    
	    @Override
	    public String toString() {
	        return "Expediente{" +
	                "idExpediente=" + idExpediente +
	                ", familia=" + (familia != null ? familia.getNumeroExpediente() : "N/A") +
	                ", fechaApertura=" + fechaApertura +
	                ", estado=" + estado +
	                ", totalAyudasRecibidas=" + totalAyudasRecibidas +
	                ", fechaUltimaAyuda=" + fechaUltimaAyuda +
	                '}';
	    }
   
}
