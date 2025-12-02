package edu.ulatina.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Miembro
 *
 */
@Entity

public class MiembroFamilia implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_miembro")
	    private Integer idMiembro;
	    
	    @ManyToOne
	    @JoinColumn(name = "id_familia", nullable = false)
	    private Familia familia;
	    
	    @Column(name = "nombre_completo", nullable = false, length = 100)
	    private String nombreCompleto;
	    
	    @Column(length = 20)
	    private String identificacion;
	    
	    @Temporal(TemporalType.DATE)
	    @Column(name = "fecha_nacimiento", nullable = false)
	    private Date fechaNacimiento;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Parentesco parentesco;
	    
	    @Column
	    private Integer edad;
	    
	    @Enumerated(EnumType.STRING)
	    private Genero genero;
	    
	    @Column(name = "necesidades_especiales", columnDefinition = "TEXT")
	    private String necesidadesEspeciales;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "fecha_registro", updatable = false)
	    private Date fechaRegistro;
	    
	    public enum Parentesco {
	        JEFE, CONYUGE, HIJO, PADRE, MADRE, OTRO
	    }
	    
	    public enum Genero {
	        M, F, OTRO
	    }
	    
	    @PrePersist
	    protected void onCreate() {
	        fechaRegistro = new Date();
	        calcularEdad();
	    }
	    
	    @PreUpdate
	    protected void onUpdate() {
	        calcularEdad();
	    }
	    
	    private void calcularEdad() {
	        if (fechaNacimiento != null) {
	            Date hoy = new Date();
	            long diferencia = hoy.getTime() - fechaNacimiento.getTime();
	            this.edad = (int) (diferencia / (1000L * 60 * 60 * 24 * 365));
	        }
	    }
	    
	    // Constructores
	    public MiembroFamilia() {}
	    
	    public MiembroFamilia(Familia familia, String nombreCompleto, Date fechaNacimiento, 
	                          Parentesco parentesco, Genero genero) {
	        this.familia = familia;
	        this.nombreCompleto = nombreCompleto;
	        this.fechaNacimiento = fechaNacimiento;
	        this.parentesco = parentesco;
	        this.genero = genero;
	    }
	    
	    // Getters y Setters
	    public Integer getIdMiembro() { return idMiembro; }
	    public void setIdMiembro(Integer idMiembro) { this.idMiembro = idMiembro; }
	    
	    public Familia getFamilia() { return familia; }
	    public void setFamilia(Familia familia) { this.familia = familia; }
	    
	    public String getNombreCompleto() { return nombreCompleto; }
	    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
	    
	    public String getIdentificacion() { return identificacion; }
	    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
	    
	    public Date getFechaNacimiento() { return fechaNacimiento; }
	    public void setFechaNacimiento(Date fechaNacimiento) { 
	        this.fechaNacimiento = fechaNacimiento;
	        calcularEdad();
	    }
	    
	    public Parentesco getParentesco() { return parentesco; }
	    public void setParentesco(Parentesco parentesco) { this.parentesco = parentesco; }
	    
	    public Integer getEdad() { return edad; }
	    
	    public Genero getGenero() { return genero; }
	    public void setGenero(Genero genero) { this.genero = genero; }
	    
	    public String getNecesidadesEspeciales() { return necesidadesEspeciales; }
	    public void setNecesidadesEspeciales(String necesidadesEspeciales) { this.necesidadesEspeciales = necesidadesEspeciales; }
	    
	    public Date getFechaRegistro() { return fechaRegistro; }
	    
	    @Override
	    public String toString() {
	        return "MiembroFamilia{" +
	                "idMiembro=" + idMiembro +
	                ", nombreCompleto='" + nombreCompleto + '\'' +
	                ", edad=" + edad +
	                ", parentesco=" + parentesco +
	                ", genero=" + genero +
	                '}';
	    }
   
}
