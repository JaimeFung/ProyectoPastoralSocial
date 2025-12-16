package edu.ulatina.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import edu.ulatina.model.Addendum;
import edu.ulatina.util.JPAUtil;

public class AddendumDAO {
    
    /**
     * Crear un nuevo addendum
     */
    public Addendum create(Addendum addendum) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(addendum);
            em.getTransaction().commit();
            return addendum;
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
     * Buscar addendum por ID
     */
    public Addendum findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Addendum.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Buscar todos los addendums de un expediente
     */
    public List<Addendum> findByExpediente(Integer idExpediente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Addendum> query = em.createQuery(
                "SELECT a FROM Addendum a WHERE a.expediente.idExpediente = :idExpediente " +
                "ORDER BY a.fechaRegistro DESC", 
                Addendum.class
            );
            query.setParameter("idExpediente", idExpediente);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Listar todos los addendums
     */
    public List<Addendum> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Addendum> query = em.createQuery(
                "SELECT a FROM Addendum a ORDER BY a.fechaRegistro DESC", 
                Addendum.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Actualizar addendum
     */
    public Addendum update(Addendum addendum) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Addendum updated = em.merge(addendum);
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
     * Eliminar addendum
     */
    public void delete(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Addendum addendum = em.find(Addendum.class, id);
            if (addendum != null) {
                em.remove(addendum);
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
     * Contar addendums de un expediente
     */
    public long countByExpediente(Integer idExpediente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Addendum a WHERE a.expediente.idExpediente = :idExpediente", 
                Long.class
            );
            query.setParameter("idExpediente", idExpediente);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}