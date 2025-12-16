package edu.ulatina.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import edu.ulatina.model.Seguimiento;
import edu.ulatina.util.JPAUtil;

public class SeguimientoDAO {
    
    /**
     * Crear un nuevo seguimiento
     */
    public Seguimiento create(Seguimiento seguimiento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(seguimiento);
            em.getTransaction().commit();
            return seguimiento;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    
    /**
     * Buscar seguimiento por ID
     */
    public Seguimiento findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Seguimiento.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Buscar todos los seguimientos de un expediente
     */
    public List<Seguimiento> findByExpediente(Integer idExpediente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Seguimiento> query = em.createQuery(
                "SELECT s FROM Seguimiento s WHERE s.expediente.idExpediente = :idExpediente " +
                "ORDER BY s.fechaRegistro DESC", 
                Seguimiento.class
            );
            query.setParameter("idExpediente", idExpediente);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Buscar seguimientos por tipo
     */
    public List<Seguimiento> findByTipo(String tipo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Seguimiento> query = em.createQuery(
                "SELECT s FROM Seguimiento s WHERE s.tipo = :tipo " +
                "ORDER BY s.fechaRegistro DESC", 
                Seguimiento.class
            );
            query.setParameter("tipo", tipo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Listar todos los seguimientos
     */
    public List<Seguimiento> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Seguimiento> query = em.createQuery(
                "SELECT s FROM Seguimiento s ORDER BY s.fechaRegistro DESC", 
                Seguimiento.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Actualizar seguimiento
     */
    public Seguimiento update(Seguimiento seguimiento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Seguimiento updated = em.merge(seguimiento);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    
    /**
     * Eliminar seguimiento
     */
    public void delete(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Seguimiento seguimiento = em.find(Seguimiento.class, id);
            if (seguimiento != null) {
                em.remove(seguimiento);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    /**
     * Contar seguimientos de un expediente
     */
    public long countByExpediente(Integer idExpediente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(s) FROM Seguimiento s WHERE s.expediente.idExpediente = :idExpediente", 
                Long.class
            );
            query.setParameter("idExpediente", idExpediente);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}