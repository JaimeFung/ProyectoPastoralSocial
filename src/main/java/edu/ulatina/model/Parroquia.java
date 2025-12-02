package edu.ulatina.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Entity implementation class for Entity: Parroquia
 *
 */
@Entity
@Table(name = "parroquias")

public class Parroquia implements Serializable {


	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_parroquia")
	    private Integer idParroquia;
	    
	    @Column(nullable = false, length = 100)
	    private String nombre;
	    
	    @Column(length = 200)
	    private String direccion;
	    
	    @Column(length = 20)
	    private String telefono;
	    
	    @Column(length = 100)
	    private String parroco;
	    
	    @Column(nullable = false)
	    private Boolean activa = true;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "fecha_creacion", updatable = false)
	    private Date fechaCreacion;
	    
	    @OneToMany(mappedBy = "parroquia", cascade = CascadeType.ALL)
	    private List<Familia> familias;
	    
	    @PrePersist
	    protected void onCreate() {
	        fechaCreacion = new Date();
	    }
	    
	    // Constructores
	    public Parroquia() {}
	    
	    public Parroquia(String nombre, String direccion, String telefono, String parroco) {
	        this.nombre = nombre;
	        this.direccion = direccion;
	        this.telefono = telefono;
	        this.parroco = parroco;
	    }
	    
	    // Getters y Setters
	    public Integer getIdParroquia() { return idParroquia; }
	    public void setIdParroquia(Integer idParroquia) { this.idParroquia = idParroquia; }
	    
	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }
	    
	    public String getDireccion() { return direccion; }
	    public void setDireccion(String direccion) { this.direccion = direccion; }
	    
	    public String getTelefono() { return telefono; }
	    public void setTelefono(String telefono) { this.telefono = telefono; }
	    
	    public String getParroco() { return parroco; }
	    public void setParroco(String parroco) { this.parroco = parroco; }
	    
	    public Boolean getActiva() { return activa; }
	    public void setActiva(Boolean activa) { this.activa = activa; }
	    
	    public Date getFechaCreacion() { return fechaCreacion; }
	    
	    public List<Familia> getFamilias() { return familias; }
	    public void setFamilias(List<Familia> familias) { this.familias = familias; }
	    
	    @Override
	    public String toString() {
	        return "Parroquia{" +
	                "idParroquia=" + idParroquia +
	                ", nombre='" + nombre + '\'' +
	                ", parroco='" + parroco + '\'' +
	                ", activa=" + activa +
	                '}';
	    }
   
}
