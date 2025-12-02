// =============================================
// GenericDAO.java - DAO Genérico con operaciones CRUD
// =============================================
package edu.ulatina.dao;

import edu.ulatina.util.JPAUtil;
import javax.persistence.*;
import java.util.List;

public abstract class GenericDAO<T, ID> {
    private Class<T> entityClass;
    
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager() {
        return JPAUtil.getEntityManager();
    }
    
    // CREATE
    public T create(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
            System.out.println("✓ Entidad creada: " + entity);
            return entity;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("✗ Error al crear entidad: " + e.getMessage());
            throw new RuntimeException("Error al crear entidad", e);
        } finally {
            em.close();
        }
    }
    
    // READ - Buscar por ID
    public T findById(ID id) {
        EntityManager em = getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                System.out.println("✓ Entidad encontrada: " + entity);
            } else {
                System.out.println("⚠ No se encontró entidad con ID: " + id);
            }
            return entity;
        } catch (Exception e) {
            System.err.println("✗ Error al buscar entidad: " + e.getMessage());
            throw new RuntimeException("Error al buscar entidad", e);
        } finally {
            em.close();
        }
    }
    
    // READ - Listar todos
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(queryString, entityClass);
            List<T> results = query.getResultList();
            System.out.println("✓ Se encontraron " + results.size() + " registros");
            return results;
        } catch (Exception e) {
            System.err.println("✗ Error al listar entidades: " + e.getMessage());
            throw new RuntimeException("Error al listar entidades", e);
        } finally {
            em.close();
        }
    }
    
    // UPDATE
    public T update(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();
            System.out.println("✓ Entidad actualizada: " + merged);
            return merged;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("✗ Error al actualizar entidad: " + e.getMessage());
            throw new RuntimeException("Error al actualizar entidad", e);
        } finally {
            em.close();
        }
    }
    
    // DELETE
    public void delete(ID id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                System.out.println("✓ Entidad eliminada con ID: " + id);
            } else {
                System.out.println("⚠ No se encontró entidad con ID: " + id);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("✗ Error al eliminar entidad: " + e.getMessage());
            throw new RuntimeException("Error al eliminar entidad", e);
        } finally {
            em.close();
        }
    }
    
    // Método auxiliar para ejecutar queries personalizadas
    protected List<T> executeQuery(String jpql, Object... params) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al ejecutar query: " + e.getMessage());
            throw new RuntimeException("Error al ejecutar query", e);
        } finally {
            em.close();
        }
    }
    
    // Contar registros
    public long count() {
        EntityManager em = getEntityManager();
        try {
            String queryString = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<Long> query = em.createQuery(queryString, Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("✗ Error al contar entidades: " + e.getMessage());
            return 0;
        } finally {
            em.close();
        }
    }
}