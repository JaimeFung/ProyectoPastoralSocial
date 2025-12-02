package edu.ulatina.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AyudaEntregada
 *
 */
@Entity

public class AyudaEntregada implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ayuda")
    private Integer idAyuda;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private Expediente expediente;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_ayuda", nullable = false)
    private TipoAyuda tipoAyuda;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_entrega", nullable = false)
    private Date fechaEntrega;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(length = 50)
    private String cantidad;
    
    @Column(name = "valor_estimado", precision = 10, scale = 2)
    private BigDecimal valorEstimado;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", updatable = false)
    private Date fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = new Date();
        if (fechaEntrega == null) {
            fechaEntrega = new Date();
        }
    }
    
    // Constructores
    public AyudaEntregada() {}
    
    public AyudaEntregada(Expediente expediente, TipoAyuda tipoAyuda, Usuario usuario, 
                          String descripcion, String cantidad) {
        this.expediente = expediente;
        this.tipoAyuda = tipoAyuda;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fechaEntrega = new Date();
    }
    
    // Getters y Setters
    public Integer getIdAyuda() { return idAyuda; }
    public void setIdAyuda(Integer idAyuda) { this.idAyuda = idAyuda; }
    
    public Expediente getExpediente() { return expediente; }
    public void setExpediente(Expediente expediente) { this.expediente = expediente; }
    
    public TipoAyuda getTipoAyuda() { return tipoAyuda; }
    public void setTipoAyuda(TipoAyuda tipoAyuda) { this.tipoAyuda = tipoAyuda; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Date getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Date fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }
    
    public BigDecimal getValorEstimado() { return valorEstimado; }
    public void setValorEstimado(BigDecimal valorEstimado) { this.valorEstimado = valorEstimado; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public Date getFechaRegistro() { return fechaRegistro; }
    
    @Override
    public String toString() {
        return "AyudaEntregada{" +
                "idAyuda=" + idAyuda +
                ", tipoAyuda=" + (tipoAyuda != null ? tipoAyuda.getNombre() : "N/A") +
                ", fechaEntrega=" + fechaEntrega +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", valorEstimado=" + valorEstimado +
                ", entregadoPor=" + (usuario != null ? usuario.getNombreCompleto() : "N/A") +
                '}';
    }
   
}
