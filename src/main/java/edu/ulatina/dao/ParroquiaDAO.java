package edu.ulatina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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