/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.persistence.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import edu.unicauca.apliweb.persistence.entities.Parametros;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Javier Fernandez
 */
public class ParametrosJpaController implements Serializable {

    public ParametrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parametros parametros) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dispositivo iddispositivo = parametros.getIddispositivo();
            if (iddispositivo != null) {
                iddispositivo = em.getReference(iddispositivo.getClass(), iddispositivo.getId());
                parametros.setIddispositivo(iddispositivo);
            }
            em.persist(parametros);
            if (iddispositivo != null) {
                iddispositivo.getParametrosList().add(parametros);
                iddispositivo = em.merge(iddispositivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parametros parametros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parametros persistentParametros = em.find(Parametros.class, parametros.getId());
            Dispositivo iddispositivoOld = persistentParametros.getIddispositivo();
            Dispositivo iddispositivoNew = parametros.getIddispositivo();
            if (iddispositivoNew != null) {
                iddispositivoNew = em.getReference(iddispositivoNew.getClass(), iddispositivoNew.getId());
                parametros.setIddispositivo(iddispositivoNew);
            }
            parametros = em.merge(parametros);
            if (iddispositivoOld != null && !iddispositivoOld.equals(iddispositivoNew)) {
                iddispositivoOld.getParametrosList().remove(parametros);
                iddispositivoOld = em.merge(iddispositivoOld);
            }
            if (iddispositivoNew != null && !iddispositivoNew.equals(iddispositivoOld)) {
                iddispositivoNew.getParametrosList().add(parametros);
                iddispositivoNew = em.merge(iddispositivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametros.getId();
                if (findParametros(id) == null) {
                    throw new NonexistentEntityException("The parametros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parametros parametros;
            try {
                parametros = em.getReference(Parametros.class, id);
                parametros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametros with id " + id + " no longer exists.", enfe);
            }
            Dispositivo iddispositivo = parametros.getIddispositivo();
            if (iddispositivo != null) {
                iddispositivo.getParametrosList().remove(parametros);
                iddispositivo = em.merge(iddispositivo);
            }
            em.remove(parametros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parametros> findParametrosEntities() {
        return findParametrosEntities(true, -1, -1);
    }

    public List<Parametros> findParametrosEntities(int maxResults, int firstResult) {
        return findParametrosEntities(false, maxResults, firstResult);
    }

    private List<Parametros> findParametrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Parametros.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Parametros findParametros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parametros.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Parametros> rt = cq.from(Parametros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
