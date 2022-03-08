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
import edu.unicauca.apliweb.persistence.entities.RangoParametros;
import edu.unicauca.apliweb.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Javier Fernandez
 */
public class RangoParametrosJpaController implements Serializable {

    public RangoParametrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RangoParametros rangoParametros) {
        if (rangoParametros.getDispositivoList() == null) {
            rangoParametros.setDispositivoList(new ArrayList<Dispositivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dispositivo> attachedDispositivoList = new ArrayList<Dispositivo>();
            for (Dispositivo dispositivoListDispositivoToAttach : rangoParametros.getDispositivoList()) {
                dispositivoListDispositivoToAttach = em.getReference(dispositivoListDispositivoToAttach.getClass(), dispositivoListDispositivoToAttach.getId());
                attachedDispositivoList.add(dispositivoListDispositivoToAttach);
            }
            rangoParametros.setDispositivoList(attachedDispositivoList);
            em.persist(rangoParametros);
            for (Dispositivo dispositivoListDispositivo : rangoParametros.getDispositivoList()) {
                RangoParametros oldIdrangoOfDispositivoListDispositivo = dispositivoListDispositivo.getIdrango();
                dispositivoListDispositivo.setIdrango(rangoParametros);
                dispositivoListDispositivo = em.merge(dispositivoListDispositivo);
                if (oldIdrangoOfDispositivoListDispositivo != null) {
                    oldIdrangoOfDispositivoListDispositivo.getDispositivoList().remove(dispositivoListDispositivo);
                    oldIdrangoOfDispositivoListDispositivo = em.merge(oldIdrangoOfDispositivoListDispositivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RangoParametros rangoParametros) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RangoParametros persistentRangoParametros = em.find(RangoParametros.class, rangoParametros.getId());
            List<Dispositivo> dispositivoListOld = persistentRangoParametros.getDispositivoList();
            List<Dispositivo> dispositivoListNew = rangoParametros.getDispositivoList();
            List<String> illegalOrphanMessages = null;
            for (Dispositivo dispositivoListOldDispositivo : dispositivoListOld) {
                if (!dispositivoListNew.contains(dispositivoListOldDispositivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dispositivo " + dispositivoListOldDispositivo + " since its idrango field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dispositivo> attachedDispositivoListNew = new ArrayList<Dispositivo>();
            for (Dispositivo dispositivoListNewDispositivoToAttach : dispositivoListNew) {
                dispositivoListNewDispositivoToAttach = em.getReference(dispositivoListNewDispositivoToAttach.getClass(), dispositivoListNewDispositivoToAttach.getId());
                attachedDispositivoListNew.add(dispositivoListNewDispositivoToAttach);
            }
            dispositivoListNew = attachedDispositivoListNew;
            rangoParametros.setDispositivoList(dispositivoListNew);
            rangoParametros = em.merge(rangoParametros);
            for (Dispositivo dispositivoListNewDispositivo : dispositivoListNew) {
                if (!dispositivoListOld.contains(dispositivoListNewDispositivo)) {
                    RangoParametros oldIdrangoOfDispositivoListNewDispositivo = dispositivoListNewDispositivo.getIdrango();
                    dispositivoListNewDispositivo.setIdrango(rangoParametros);
                    dispositivoListNewDispositivo = em.merge(dispositivoListNewDispositivo);
                    if (oldIdrangoOfDispositivoListNewDispositivo != null && !oldIdrangoOfDispositivoListNewDispositivo.equals(rangoParametros)) {
                        oldIdrangoOfDispositivoListNewDispositivo.getDispositivoList().remove(dispositivoListNewDispositivo);
                        oldIdrangoOfDispositivoListNewDispositivo = em.merge(oldIdrangoOfDispositivoListNewDispositivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rangoParametros.getId();
                if (findRangoParametros(id) == null) {
                    throw new NonexistentEntityException("The rangoParametros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RangoParametros rangoParametros;
            try {
                rangoParametros = em.getReference(RangoParametros.class, id);
                rangoParametros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rangoParametros with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dispositivo> dispositivoListOrphanCheck = rangoParametros.getDispositivoList();
            for (Dispositivo dispositivoListOrphanCheckDispositivo : dispositivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RangoParametros (" + rangoParametros + ") cannot be destroyed since the Dispositivo " + dispositivoListOrphanCheckDispositivo + " in its dispositivoList field has a non-nullable idrango field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rangoParametros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RangoParametros> findRangoParametrosEntities() {
        return findRangoParametrosEntities(true, -1, -1);
    }

    public List<RangoParametros> findRangoParametrosEntities(int maxResults, int firstResult) {
        return findRangoParametrosEntities(false, maxResults, firstResult);
    }

    private List<RangoParametros> findRangoParametrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RangoParametros.class));
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

    public RangoParametros findRangoParametros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RangoParametros.class, id);
        } finally {
            em.close();
        }
    }

    public int getRangoParametrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RangoParametros> rt = cq.from(RangoParametros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
