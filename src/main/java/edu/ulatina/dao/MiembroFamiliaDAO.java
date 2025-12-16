package edu.ulatina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.ulatina.model.MiembroFamilia;

public class MiembroFamiliaDAO {
    private EntityManagerFactory emf;
    
    public MiembroFamiliaDAO() {
        this.emf = Persistence.createEntityManagerFactory("PastoralSocialPU");
    }
    
    public MiembroFamilia create(MiembroFamilia miembro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(miembro);
            em.getTransaction().commit();
            return miembro;
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
    
    public MiembroFamilia findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(MiembroFamilia.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<MiembroFamilia> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MiembroFamilia> query = em.createQuery(
                "SELECT m FROM MiembroFamilia m", MiembroFamilia.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<MiembroFamilia> findByFamilia(Integer idFamilia) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MiembroFamilia> query = em.createQuery(
                "SELECT m FROM MiembroFamilia m WHERE m.familia.idFamilia = :idFamilia",
                MiembroFamilia.class);
            query.setParameter("idFamilia", idFamilia);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public MiembroFamilia findJefeFamilia(Integer idFamilia) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MiembroFamilia> query = em.createQuery(
                "SELECT m FROM MiembroFamilia m WHERE m.familia.idFamilia = :idFamilia " +
                "AND m.parentesco = 'JEFE'",
                MiembroFamilia.class);
            query.setParameter("idFamilia", idFamilia);
            List<MiembroFamilia> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    
    public MiembroFamilia update(MiembroFamilia miembro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            MiembroFamilia updated = em.merge(miembro);
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
            MiembroFamilia miembro = em.find(MiembroFamilia.class, id);
            if (miembro != null) {
                em.remove(miembro);
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
    
    public long countByFamilia(Integer idFamilia) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(m) FROM MiembroFamilia m WHERE m.familia.idFamilia = :idFamilia",
                Long.class);
            query.setParameter("idFamilia", idFamilia);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}