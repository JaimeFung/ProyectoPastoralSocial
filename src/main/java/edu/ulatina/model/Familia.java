package edu.ulatina.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Entity implementation class for Entity: Familia
 *
 */
@Entity

public class Familia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia")
    private Integer idFamilia;
    
    @ManyToOne
    @JoinColumn(name = "id_parroquia", nullable = false)
    private Parroquia parroquia;
    
    @Column(name = "numero_expediente", unique = true, nullable = false, length = 50)
    private String numeroExpediente;
    
    @Column(name = "nombre_jefe_familia", nullable = false, length = 100)
    private String nombreJefeFamilia;
    
    @Column(name = "identificacion_jefe", unique = true, nullable = false, length = 20)
    private String identificacionJefe;
    
    @Column(nullable = false, length = 200)
    private String direccion;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(name = "cantidad_miembros")
    private Integer cantidadMiembros = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "situacion_economica", nullable = false)
    private SituacionEconomica situacionEconomica;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", updatable = false)
    private Date fechaRegistro;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    
    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiembroFamilia> miembros;
    
    @OneToOne(mappedBy = "familia", cascade = CascadeType.ALL)
    private Expediente expediente;
    
    public enum SituacionEconomica {
        MUY_BAJA, BAJA, MEDIA, ESTABLE
    }
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = new Date();
    }
    
    // Constructores
    public Familia() {}
    
    public Familia(Parroquia parroquia, String numeroExpediente, String nombreJefeFamilia, 
                   String identificacionJefe, String direccion, SituacionEconomica situacionEconomica) {
        this.parroquia = parroquia;
        this.numeroExpediente = numeroExpediente;
        this.nombreJefeFamilia = nombreJefeFamilia;
        this.identificacionJefe = identificacionJefe;
        this.direccion = direccion;
        this.situacionEconomica = situacionEconomica;
    }
    
    // Getters y Setters
    public Integer getIdFamilia() { return idFamilia; }
    public void setIdFamilia(Integer idFamilia) { this.idFamilia = idFamilia; }
    
    public Parroquia getParroquia() { return parroquia; }
    public void setParroquia(Parroquia parroquia) { this.parroquia = parroquia; }
    
    public String getNumeroExpediente() { return numeroExpediente; }
    public void setNumeroExpediente(String numeroExpediente) { this.numeroExpediente = numeroExpediente; }
    
    public String getNombreJefeFamilia() { return nombreJefeFamilia; }
    public void setNombreJefeFamilia(String nombreJefeFamilia) { this.nombreJefeFamilia = nombreJefeFamilia; }
    
    public String getIdentificacionJefe() { return identificacionJefe; }
    public void setIdentificacionJefe(String identificacionJefe) { this.identificacionJefe = identificacionJefe; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public Integer getCantidadMiembros() { return cantidadMiembros; }
    public void setCantidadMiembros(Integer cantidadMiembros) { this.cantidadMiembros = cantidadMiembros; }
    
    public SituacionEconomica getSituacionEconomica() { return situacionEconomica; }
    public void setSituacionEconomica(SituacionEconomica situacionEconomica) { this.situacionEconomica = situacionEconomica; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    
    public Date getFechaRegistro() { return fechaRegistro; }
    public Date getFechaModificacion() { return fechaModificacion; }
    
    public List<MiembroFamilia> getMiembros() { return miembros; }
    public void setMiembros(List<MiembroFamilia> miembros) { this.miembros = miembros; }
    
    public Expediente getExpediente() { return expediente; }
    public void setExpediente(Expediente expediente) { this.expediente = expediente; }
    
    @Override
    public String toString() {
        return "Familia{" +
                "idFamilia=" + idFamilia +
                ", numeroExpediente='" + numeroExpediente + '\'' +
                ", nombreJefeFamilia='" + nombreJefeFamilia + '\'' +
                ", identificacionJefe='" + identificacionJefe + '\'' +
                ", parroquia=" + (parroquia != null ? parroquia.getNombre() : "N/A") +
                ", cantidadMiembros=" + cantidadMiembros +
                ", situacionEconomica=" + situacionEconomica +
                ", activa=" + activa +
                '}';
    }
   
}
