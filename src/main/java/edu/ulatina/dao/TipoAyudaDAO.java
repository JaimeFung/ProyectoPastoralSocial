package edu.ulatina.dao;

import edu.ulatina.model.TipoAyuda;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TipoAyudaDAO extends GenericDAO<TipoAyuda, Integer> {
    
    public TipoAyudaDAO() {
        super(TipoAyuda.class);
    }
    
    /**
     * Lista todos los tipos de ayuda activos
     */
    public List<TipoAyuda> findActivos() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT t FROM TipoAyuda t WHERE t.activo = true ORDER BY t.categoria, t.nombre";
            TypedQuery<TipoAyuda> query = em.createQuery(jpql, TipoAyuda.class);
            List<TipoAyuda> results = query.getResultList();
            System.out.println("✓ Se encontraron " + results.size() + " tipos de ayuda activos");
            return results;
        } catch (Exception e) {
            System.err.println("✗ Error al buscar tipos de ayuda activos: " + e.getMessage());
            throw new RuntimeException("Error al buscar tipos de ayuda activos", e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca tipos de ayuda por categoría
     */
    public List<TipoAyuda> findByCategoria(TipoAyuda.Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT t FROM TipoAyuda t WHERE t.categoria = :categoria AND t.activo = true ORDER BY t.nombre";
            TypedQuery<TipoAyuda> query = em.createQuery(jpql, TipoAyuda.class);
            query.setParameter("categoria", categoria);
            List<TipoAyuda> results = query.getResultList();
            System.out.println("✓ Se encontraron " + results.size() + " tipos de ayuda en categoría " + categoria);
            return results;
        } catch (Exception e) {
            System.err.println("✗ Error al buscar tipos de ayuda por categoría: " + e.getMessage());
            throw new RuntimeException("Error al buscar tipos de ayuda por categoría", e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca un tipo de ayuda por nombre exacto
     */
    public TipoAyuda findByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT t FROM TipoAyuda t WHERE t.nombre = :nombre";
            TypedQuery<TipoAyuda> query = em.createQuery(jpql, TipoAyuda.class);
            query.setParameter("nombre", nombre);
            List<TipoAyuda> results = query.getResultList();
            if (!results.isEmpty()) {
                System.out.println("✓ Tipo de ayuda encontrado: " + results.get(0).getNombre());
                return results.get(0);
            } else {
                System.out.println("⚠ No se encontró tipo de ayuda con nombre: " + nombre);
                return null;
            }
        } catch (Exception e) {
            System.err.println("✗ Error al buscar tipo de ayuda por nombre: " + e.getMessage());
            throw new RuntimeException("Error al buscar tipo de ayuda por nombre", e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca tipos de ayuda por nombre (búsqueda parcial)
     */
    public List<TipoAyuda> findByNombreLike(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT t FROM TipoAyuda t WHERE t.nombre LIKE :nombre AND t.activo = true ORDER BY t.nombre";
            TypedQuery<TipoAyuda> query = em.createQuery(jpql, TipoAyuda.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar tipos de ayuda por nombre: " + e.getMessage());
            throw new RuntimeException("Error al buscar tipos de ayuda por nombre", e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Cuenta cuántos tipos de ayuda hay por categoría
     */
    public long countByCategoria(TipoAyuda.Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(t) FROM TipoAyuda t WHERE t.categoria = :categoria AND t.activo = true";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("categoria", categoria);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("✗ Error al contar tipos de ayuda: " + e.getMessage());
            return 0;
        } finally {
            em.close();
        }
    }
}
