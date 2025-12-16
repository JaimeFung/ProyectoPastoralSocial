package edu.ulatina.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import edu.ulatina.model.AyudaEntregada;

public class AyudaEntregadaDAO {
    private EntityManagerFactory emf;
    
    public AyudaEntregadaDAO() {
        this.emf = Persistence.createEntityManagerFactory("PastoralSocialPU");
    }
    
    public AyudaEntregada create(AyudaEntregada ayuda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ayuda);
            em.getTransaction().commit();
            return ayuda;
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
    
    public AyudaEntregada findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(AyudaEntregada.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<AyudaEntregada> findByExpediente(Integer idExpediente) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AyudaEntregada> query = em.createQuery(
                "SELECT a FROM AyudaEntregada a WHERE a.expediente.idExpediente = :idExpediente " +
                "ORDER BY a.fechaEntrega DESC", 
                AyudaEntregada.class);
            query.setParameter("idExpediente", idExpediente);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<AyudaEntregada> findByTipoAyuda(Integer idTipoAyuda) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AyudaEntregada> query = em.createQuery(
                "SELECT a FROM AyudaEntregada a WHERE a.tipoAyuda.idTipoAyuda = :idTipoAyuda " +
                "ORDER BY a.fechaEntrega DESC", 
                AyudaEntregada.class);
            query.setParameter("idTipoAyuda", idTipoAyuda);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<AyudaEntregada> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AyudaEntregada> query = em.createQuery(
                "SELECT a FROM AyudaEntregada a ORDER BY a.fechaEntrega DESC", 
                AyudaEntregada.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public AyudaEntregada update(AyudaEntregada ayuda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AyudaEntregada updated = em.merge(ayuda);
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
    
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AyudaEntregada ayuda = em.find(AyudaEntregada.class, id);
            if (ayuda != null) {
                em.remove(ayuda);
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
    public long countAyudasMesActual() {
        EntityManager em = emf.createEntityManager();
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date iniciaMes = cal.getTime();
            
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM AyudaEntregada a WHERE a.fechaEntrega >= :iniciaMes", 
                Long.class);
            query.setParameter("iniciaMes", iniciaMes);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<AyudaEntregada> findRecientes(int limite) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AyudaEntregada> query = em.createQuery(
                "SELECT a FROM AyudaEntregada a ORDER BY a.fechaEntrega DESC", 
                AyudaEntregada.class);
            query.setMaxResults(limite);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public long countByExpediente(Integer idExpediente) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM AyudaEntregada a WHERE a.expediente.idExpediente = :idExpediente",
                Long.class);
            query.setParameter("idExpediente", idExpediente);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
}