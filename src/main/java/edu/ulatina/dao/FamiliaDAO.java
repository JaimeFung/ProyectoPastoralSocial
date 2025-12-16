package edu.ulatina.dao;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.ulatina.model.Familia;

import java.util.List;

public class FamiliaDAO extends GenericDAO<Familia, Integer> {
    
    public FamiliaDAO() {
        super(Familia.class);
    }
    
    
    public Familia findByNumeroExpediente(String numeroExpediente) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT f FROM Familia f WHERE f.numeroExpediente = :numero";
            TypedQuery<Familia> query = em.createQuery(jpql, Familia.class);
            query.setParameter("numero", numeroExpediente);
            List<Familia> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            System.err.println("✗ Error al buscar familia: " + e.getMessage());
            throw new RuntimeException("Error al buscar familia", e);
        } finally {
            em.close();
        }
    }
    public long countActivas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Familia f WHERE f.activa = true", 
                Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Familia> findByParroquia(Integer idParroquia) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT f FROM Familia f WHERE f.parroquia.idParroquia = :idParroquia " +
                         "AND f.activa = true ORDER BY f.nombreJefeFamilia";
            TypedQuery<Familia> query = em.createQuery(jpql, Familia.class);
            query.setParameter("idParroquia", idParroquia);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar familias por parroquia: " + e.getMessage());
            throw new RuntimeException("Error al buscar familias por parroquia", e);
        } finally {
            em.close();
        }
    }
    
    // Buscar familias por nombre
    public List<Familia> findByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT f FROM Familia f WHERE f.nombreJefeFamilia LIKE :nombre " +
                         "AND f.activa = true ORDER BY f.nombreJefeFamilia";
            TypedQuery<Familia> query = em.createQuery(jpql, Familia.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar familias por nombre: " + e.getMessage());
            throw new RuntimeException("Error al buscar familias por nombre", e);
        } finally {
            em.close();
        }
    }
    
    // Listar familias activas
    public List<Familia> findActivas() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT f FROM Familia f WHERE f.activa = true " +
                         "ORDER BY f.fechaRegistro DESC";
            TypedQuery<Familia> query = em.createQuery(jpql, Familia.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar familias activas: " + e.getMessage());
            throw new RuntimeException("Error al buscar familias activas", e);
        } finally {
            em.close();
        }
    }
}
