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
import edu.unicauca.apliweb.persistence.entities.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getDispositivoList() == null) {
            usuario.setDispositivoList(new ArrayList<Dispositivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dispositivo> attachedDispositivoList = new ArrayList<Dispositivo>();
            for (Dispositivo dispositivoListDispositivoToAttach : usuario.getDispositivoList()) {
                dispositivoListDispositivoToAttach = em.getReference(dispositivoListDispositivoToAttach.getClass(), dispositivoListDispositivoToAttach.getId());
                attachedDispositivoList.add(dispositivoListDispositivoToAttach);
            }
            usuario.setDispositivoList(attachedDispositivoList);
            em.persist(usuario);
            for (Dispositivo dispositivoListDispositivo : usuario.getDispositivoList()) {
                Usuario oldIdusuarioOfDispositivoListDispositivo = dispositivoListDispositivo.getIdusuario();
                dispositivoListDispositivo.setIdusuario(usuario);
                dispositivoListDispositivo = em.merge(dispositivoListDispositivo);
                if (oldIdusuarioOfDispositivoListDispositivo != null) {
                    oldIdusuarioOfDispositivoListDispositivo.getDispositivoList().remove(dispositivoListDispositivo);
                    oldIdusuarioOfDispositivoListDispositivo = em.merge(oldIdusuarioOfDispositivoListDispositivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Dispositivo> dispositivoListOld = persistentUsuario.getDispositivoList();
            List<Dispositivo> dispositivoListNew = usuario.getDispositivoList();
            List<String> illegalOrphanMessages = null;
            for (Dispositivo dispositivoListOldDispositivo : dispositivoListOld) {
                if (!dispositivoListNew.contains(dispositivoListOldDispositivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dispositivo " + dispositivoListOldDispositivo + " since its idusuario field is not nullable.");
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
            usuario.setDispositivoList(dispositivoListNew);
            usuario = em.merge(usuario);
            for (Dispositivo dispositivoListNewDispositivo : dispositivoListNew) {
                if (!dispositivoListOld.contains(dispositivoListNewDispositivo)) {
                    Usuario oldIdusuarioOfDispositivoListNewDispositivo = dispositivoListNewDispositivo.getIdusuario();
                    dispositivoListNewDispositivo.setIdusuario(usuario);
                    dispositivoListNewDispositivo = em.merge(dispositivoListNewDispositivo);
                    if (oldIdusuarioOfDispositivoListNewDispositivo != null && !oldIdusuarioOfDispositivoListNewDispositivo.equals(usuario)) {
                        oldIdusuarioOfDispositivoListNewDispositivo.getDispositivoList().remove(dispositivoListNewDispositivo);
                        oldIdusuarioOfDispositivoListNewDispositivo = em.merge(oldIdusuarioOfDispositivoListNewDispositivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dispositivo> dispositivoListOrphanCheck = usuario.getDispositivoList();
            for (Dispositivo dispositivoListOrphanCheckDispositivo : dispositivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Dispositivo " + dispositivoListOrphanCheckDispositivo + " in its dispositivoList field has a non-nullable idusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
