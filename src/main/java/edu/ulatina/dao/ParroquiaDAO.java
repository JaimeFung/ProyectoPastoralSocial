package edu.ulatina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import edu.ulatina.model.Parroquia;

public class ParroquiaDAO extends GenericDAO<Parroquia, Integer> {
    
    public ParroquiaDAO() {
        super(Parroquia.class);
    }
    
    // Listar parroquias activas
    public List<Parroquia> findActivas() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Parroquia p WHERE p.activa = true ORDER BY p.nombre";
            TypedQuery<Parroquia> query = em.createQuery(jpql, Parroquia.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar parroquias activas: " + e.getMessage());
            throw new RuntimeException("Error al buscar parroquias activas", e);
        } finally {
            em.close();
        }
    }
    
    public long countActivas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Parroquia p WHERE p.activa = true", 
                Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Map<String, Object>> obtenerDistribucionBeneficiarios() {
        EntityManager em = getEntityManager();
        try {
            // Consulta que agrupa familias por parroquia
            TypedQuery<Object[]> query = em.createQuery(
                "SELECT p.nombre, COUNT(f) " +
                "FROM Familia f JOIN f.parroquia p " +
                "WHERE f.activa = true " +
                "GROUP BY p.idParroquia, p.nombre " +
                "ORDER BY COUNT(f) DESC",
                Object[].class);
            
            List<Object[]> resultados = query.getResultList();
            List<Map<String, Object>> distribucion = new ArrayList<>();
            
            // Calcular el total para porcentajes
            long total = 0;
            for (Object[] resultado : resultados) {
                total += (Long) resultado[1];
            }
            
            for (Object[] resultado : resultados) {
                Map<String, Object> item = new HashMap<>();
                item.put("nombre", resultado[0]);
                item.put("cantidad", resultado[1]);
                
                // Calcular porcentaje
                long cantidad = (Long) resultado[1];
                double porcentaje = total > 0 ? (cantidad * 100.0 / total) : 0;
                item.put("porcentaje", Math.round(porcentaje));
                
                distribucion.add(item);
            }
            
            return distribucion;
        } finally {
            em.close();
        }
    }
    
    // Buscar parroquia por nombre
    public Parroquia findByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Parroquia p WHERE p.nombre LIKE :nombre";
            TypedQuery<Parroquia> query = em.createQuery(jpql, Parroquia.class);
            query.setParameter("nombre", "%" + nombre + "%");
            List<Parroquia> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            System.err.println("✗ Error al buscar parroquia: " + e.getMessage());
            throw new RuntimeException("Error al buscar parroquia", e);
        } finally {
            em.close();
        }
    }
}