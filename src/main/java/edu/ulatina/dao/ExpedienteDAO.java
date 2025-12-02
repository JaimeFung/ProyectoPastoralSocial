package edu.ulatina.dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.ulatina.model.Expediente;

import java.util.List;

public class ExpedienteDAO extends GenericDAO<Expediente, Integer> {
    
    public ExpedienteDAO() {
        super(Expediente.class);
    }
    
    // Buscar expediente por familia
    public Expediente findByFamilia(Integer idFamilia) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM Expediente e WHERE e.familia.idFamilia = :idFamilia";
            TypedQuery<Expediente> query = em.createQuery(jpql, Expediente.class);
            query.setParameter("idFamilia", idFamilia);
            List<Expediente> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            System.err.println("✗ Error al buscar expediente: " + e.getMessage());
            throw new RuntimeException("Error al buscar expediente", e);
        } finally {
            em.close();
        }
    }
    
    // Listar expedientes por estado
    public List<Expediente> findByEstado(Expediente.Estado estado) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM Expediente e WHERE e.estado = :estado " +
                         "ORDER BY e.fechaModificacion DESC";
            TypedQuery<Expediente> query = em.createQuery(jpql, Expediente.class);
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar expedientes por estado: " + e.getMessage());
            throw new RuntimeException("Error al buscar expedientes por estado", e);
        } finally {
            em.close();
        }
    }
}