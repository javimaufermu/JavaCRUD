/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.persistence.jpa;

import edu.unicauca.apliweb.persistence.entities.Alerta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Javier Fernandez
 */
public class AlertaJpaController implements Serializable {

    public AlertaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alerta alerta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dispositivo iddispositivo = alerta.getIddispositivo();
            if (iddispositivo != null) {
                iddispositivo = em.getReference(iddispositivo.getClass(), iddispositivo.getId());
                alerta.setIddispositivo(iddispositivo);
            }
            em.persist(alerta);
            if (iddispositivo != null) {
                iddispositivo.getAlertaList().add(alerta);
                iddispositivo = em.merge(iddispositivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alerta alerta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alerta persistentAlerta = em.find(Alerta.class, alerta.getId());
            Dispositivo iddispositivoOld = persistentAlerta.getIddispositivo();
            Dispositivo iddispositivoNew = alerta.getIddispositivo();
            if (iddispositivoNew != null) {
                iddispositivoNew = em.getReference(iddispositivoNew.getClass(), iddispositivoNew.getId());
                alerta.setIddispositivo(iddispositivoNew);
            }
            alerta = em.merge(alerta);
            if (iddispositivoOld != null && !iddispositivoOld.equals(iddispositivoNew)) {
                iddispositivoOld.getAlertaList().remove(alerta);
                iddispositivoOld = em.merge(iddispositivoOld);
            }
            if (iddispositivoNew != null && !iddispositivoNew.equals(iddispositivoOld)) {
                iddispositivoNew.getAlertaList().add(alerta);
                iddispositivoNew = em.merge(iddispositivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alerta.getId();
                if (findAlerta(id) == null) {
                    throw new NonexistentEntityException("The alerta with id " + id + " no longer exists.");
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
            Alerta alerta;
            try {
                alerta = em.getReference(Alerta.class, id);
                alerta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alerta with id " + id + " no longer exists.", enfe);
            }
            Dispositivo iddispositivo = alerta.getIddispositivo();
            if (iddispositivo != null) {
                iddispositivo.getAlertaList().remove(alerta);
                iddispositivo = em.merge(iddispositivo);
            }
            em.remove(alerta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alerta> findAlertaEntities() {
        return findAlertaEntities(true, -1, -1);
    }

    public List<Alerta> findAlertaEntities(int maxResults, int firstResult) {
        return findAlertaEntities(false, maxResults, firstResult);
    }

    private List<Alerta> findAlertaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alerta.class));
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

    public Alerta findAlerta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alerta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlertaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alerta> rt = cq.from(Alerta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
