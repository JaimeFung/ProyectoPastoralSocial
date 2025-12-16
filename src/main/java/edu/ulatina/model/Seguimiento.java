package edu.ulatina.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "seguimiento")
public class Seguimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private Expediente expediente;
    
    @Column(name = "tipo", length = 100)
    private String tipo; // Visita domiciliaria, Llamada telefónica, Entrevista, etc.
    
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "responsable", length = 255)
    private String responsable;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;
    
    // Constructores
    public Seguimiento() {
        this.fechaRegistro = new Date();
    }
    
    public Seguimiento(Expediente expediente, String titulo, String descripcion) {
        this.expediente = expediente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaRegistro = new Date();
    }
    
    // Getters y Setters
    public Integer getIdSeguimiento() {
        return idSeguimiento;
    }
    
    public void setIdSeguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }
    
    public Expediente getExpediente() {
        return expediente;
    }
    
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getResponsable() {
        return responsable;
    }
    
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public String toString() {
        return "Seguimiento{" +
                "idSeguimiento=" + idSeguimiento +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}