package edu.ulatina.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ayuda
 *
 */
@Entity

public class TipoAyuda implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_ayuda")
    private Integer idTipoAyuda;
    
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @Column(length = 200)
    private String descripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "tipoAyuda")
    private List<AyudaEntregada> ayudasEntregadas;
    
    public enum Categoria {
        ALIMENTOS, MEDICAMENTOS, ECONOMICO, ACTIVIDAD, OTRO
    }
    
    // Constructores
    public TipoAyuda() {}
    
    public TipoAyuda(String nombre, String descripcion, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    
    // Getters y Setters
    public Integer getIdTipoAyuda() { return idTipoAyuda; }
    public void setIdTipoAyuda(Integer idTipoAyuda) { this.idTipoAyuda = idTipoAyuda; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    public List<AyudaEntregada> getAyudasEntregadas() { return ayudasEntregadas; }
    public void setAyudasEntregadas(List<AyudaEntregada> ayudasEntregadas) { this.ayudasEntregadas = ayudasEntregadas; }
    
    @Override
    public String toString() {
        return "TipoAyuda{" +
                "idTipoAyuda=" + idTipoAyuda +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", activo=" + activo +
                '}';
    }

   
}
