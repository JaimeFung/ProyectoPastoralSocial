package edu.ulatina.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Miembro
 *
 */
@Entity
@Table(name = "miembrofamilia")
public class MiembroFamilia implements Serializable {

	
private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    private Integer idMiembro;
    
    @ManyToOne
    @JoinColumn(name = "id_familia", nullable = false)
    private Familia familia;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "primer_apellido", nullable = false, length = 50)
    private String primerApellido;
    
    @Column(name = "segundo_apellido", nullable = false, length = 50)
    private String segundoApellido;
    
    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;
    
    @Column(name = "identificacion", length = 20)
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
    
    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(length = 100)
    private String email;
    
    @Column(name = "necesidades_especiales", columnDefinition = "TEXT")
    private String necesidadesEspeciales;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", updatable = false)
    private Date fechaRegistro;
    
    public enum Parentesco {
        JEFE, CONYUGE, HIJO, HIJA, PADRE, MADRE, HERMANO, HERMANA, ABUELO, SOBRINO, NIETO, TIO, OTRO
    }
    
    public enum Genero {
        MASCULINO, FEMENINO, OTRO
    }
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = new Date();
        calcularEdad();
        generarNombreCompleto();
    }
    
    @PreUpdate
    protected void onUpdate() {
        calcularEdad();
        generarNombreCompleto();
    }
    
    private void calcularEdad() {
        if (fechaNacimiento != null) {
            Date hoy = new Date();
            long diferencia = hoy.getTime() - fechaNacimiento.getTime();
            this.edad = (int) (diferencia / (1000L * 60 * 60 * 24 * 365));
        }
    }
    
    private void generarNombreCompleto() {
        if (nombre != null && primerApellido != null && segundoApellido != null) {
            this.nombreCompleto = nombre + " " + primerApellido + " " + segundoApellido;
        }
    }
    
    // Constructores
    public MiembroFamilia() {}
    
    public MiembroFamilia(Familia familia, String nombre, String primerApellido, 
                          String segundoApellido, Date fechaNacimiento, 
                          Parentesco parentesco, Genero genero) {
        this.familia = familia;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.parentesco = parentesco;
        this.genero = genero;
    }
    
    // Getters y Setters
    public Integer getIdMiembro() { return idMiembro; }
    public void setIdMiembro(Integer idMiembro) { this.idMiembro = idMiembro; }
    
    public Familia getFamilia() { return familia; }
    public void setFamilia(Familia familia) { this.familia = familia; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
        this.nombre = nombre;
        generarNombreCompleto();
    }
    
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { 
        this.primerApellido = primerApellido;
        generarNombreCompleto();
    }
    
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { 
        this.segundoApellido = segundoApellido;
        generarNombreCompleto();
    }
    
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
    
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNecesidadesEspeciales() { return necesidadesEspeciales; }
    public void setNecesidadesEspeciales(String necesidadesEspeciales) { 
        this.necesidadesEspeciales = necesidadesEspeciales; 
    }
    
    public Date getFechaRegistro() { return fechaRegistro; }
    
    @Override
    public String toString() {
        return "MiembroFamilia{" +
                "idMiembro=" + idMiembro +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", edad=" + edad +
                ", parentesco=" + parentesco +
                ", genero=" + genero +
                ", estadoCivil='" + estadoCivil + '\'' +
                '}';
    }
   
}