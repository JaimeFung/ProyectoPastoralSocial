package edu.ulatina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.ulatina.model.TipoAyuda;

public class TipoAyudaDAO {
    private EntityManagerFactory emf;
    
    public TipoAyudaDAO() {
        this.emf = Persistence.createEntityManagerFactory("PastoralSocialPU");
    }
    
    public TipoAyuda create(TipoAyuda tipoAyuda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tipoAyuda);
            em.getTransaction().commit();
            return tipoAyuda;
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
    
    public TipoAyuda findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(TipoAyuda.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<TipoAyuda> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TipoAyuda> query = em.createQuery(
                "SELECT t FROM TipoAyuda t", TipoAyuda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<TipoAyuda> findActivos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TipoAyuda> query = em.createQuery(
                "SELECT t FROM TipoAyuda t WHERE t.activo = true", 
                TipoAyuda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<TipoAyuda> findByCategoria(TipoAyuda.Categoria categoria) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TipoAyuda> query = em.createQuery(
                "SELECT t FROM TipoAyuda t WHERE t.categoria = :categoria AND t.activo = true",
                TipoAyuda.class);
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public TipoAyuda update(TipoAyuda tipoAyuda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TipoAyuda updated = em.merge(tipoAyuda);
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
            TipoAyuda tipoAyuda = em.find(TipoAyuda.class, id);
            if (tipoAyuda != null) {
                em.remove(tipoAyuda);
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
}