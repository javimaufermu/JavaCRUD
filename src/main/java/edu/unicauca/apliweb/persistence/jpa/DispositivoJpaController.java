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
import edu.unicauca.apliweb.persistence.entities.Usuario;
import edu.unicauca.apliweb.persistence.entities.RangoParametros;
import edu.unicauca.apliweb.persistence.entities.Alerta;
import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import java.util.ArrayList;
import java.util.List;
import edu.unicauca.apliweb.persistence.entities.Parametros;
import edu.unicauca.apliweb.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Javier Fernandez
 */
public class DispositivoJpaController implements Serializable {

    public DispositivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dispositivo dispositivo) {
        if (dispositivo.getAlertaList() == null) {
            dispositivo.setAlertaList(new ArrayList<Alerta>());
        }
        if (dispositivo.getParametrosList() == null) {
            dispositivo.setParametrosList(new ArrayList<Parametros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = dispositivo.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getId());
                dispositivo.setIdusuario(idusuario);
            }
            RangoParametros idrango = dispositivo.getIdrango();
            if (idrango != null) {
                idrango = em.getReference(idrango.getClass(), idrango.getId());
                dispositivo.setIdrango(idrango);
            }
            List<Alerta> attachedAlertaList = new ArrayList<Alerta>();
            for (Alerta alertaListAlertaToAttach : dispositivo.getAlertaList()) {
                alertaListAlertaToAttach = em.getReference(alertaListAlertaToAttach.getClass(), alertaListAlertaToAttach.getId());
                attachedAlertaList.add(alertaListAlertaToAttach);
            }
            dispositivo.setAlertaList(attachedAlertaList);
            List<Parametros> attachedParametrosList = new ArrayList<Parametros>();
            for (Parametros parametrosListParametrosToAttach : dispositivo.getParametrosList()) {
                parametrosListParametrosToAttach = em.getReference(parametrosListParametrosToAttach.getClass(), parametrosListParametrosToAttach.getId());
                attachedParametrosList.add(parametrosListParametrosToAttach);
            }
            dispositivo.setParametrosList(attachedParametrosList);
            em.persist(dispositivo);
            if (idusuario != null) {
                idusuario.getDispositivoList().add(dispositivo);
                idusuario = em.merge(idusuario);
            }
            if (idrango != null) {
                idrango.getDispositivoList().add(dispositivo);
                idrango = em.merge(idrango);
            }
            for (Alerta alertaListAlerta : dispositivo.getAlertaList()) {
                Dispositivo oldIddispositivoOfAlertaListAlerta = alertaListAlerta.getIddispositivo();
                alertaListAlerta.setIddispositivo(dispositivo);
                alertaListAlerta = em.merge(alertaListAlerta);
                if (oldIddispositivoOfAlertaListAlerta != null) {
                    oldIddispositivoOfAlertaListAlerta.getAlertaList().remove(alertaListAlerta);
                    oldIddispositivoOfAlertaListAlerta = em.merge(oldIddispositivoOfAlertaListAlerta);
                }
            }
            for (Parametros parametrosListParametros : dispositivo.getParametrosList()) {
                Dispositivo oldIddispositivoOfParametrosListParametros = parametrosListParametros.getIddispositivo();
                parametrosListParametros.setIddispositivo(dispositivo);
                parametrosListParametros = em.merge(parametrosListParametros);
                if (oldIddispositivoOfParametrosListParametros != null) {
                    oldIddispositivoOfParametrosListParametros.getParametrosList().remove(parametrosListParametros);
                    oldIddispositivoOfParametrosListParametros = em.merge(oldIddispositivoOfParametrosListParametros);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dispositivo dispositivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dispositivo persistentDispositivo = em.find(Dispositivo.class, dispositivo.getId());
            Usuario idusuarioOld = persistentDispositivo.getIdusuario();
            Usuario idusuarioNew = dispositivo.getIdusuario();
            RangoParametros idrangoOld = persistentDispositivo.getIdrango();
            RangoParametros idrangoNew = dispositivo.getIdrango();
            List<Alerta> alertaListOld = persistentDispositivo.getAlertaList();
            List<Alerta> alertaListNew = dispositivo.getAlertaList();
            List<Parametros> parametrosListOld = persistentDispositivo.getParametrosList();
            List<Parametros> parametrosListNew = dispositivo.getParametrosList();
            List<String> illegalOrphanMessages = null;
            /*for (Alerta alertaListOldAlerta : alertaListOld) {
                if (!alertaListNew.contains(alertaListOldAlerta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alerta " + alertaListOldAlerta + " since its iddispositivo field is not nullable.");
                }
            }
            for (Parametros parametrosListOldParametros : parametrosListOld) {
                if (!parametrosListNew.contains(parametrosListOldParametros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Parametros " + parametrosListOldParametros + " since its iddispositivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getId());
                dispositivo.setIdusuario(idusuarioNew);
            }
            if (idrangoNew != null) {
                idrangoNew = em.getReference(idrangoNew.getClass(), idrangoNew.getId());
                dispositivo.setIdrango(idrangoNew);
            }
            List<Alerta> attachedAlertaListNew = new ArrayList<Alerta>();
            for (Alerta alertaListNewAlertaToAttach : alertaListNew) {
                alertaListNewAlertaToAttach = em.getReference(alertaListNewAlertaToAttach.getClass(), alertaListNewAlertaToAttach.getId());
                attachedAlertaListNew.add(alertaListNewAlertaToAttach);
            }
            alertaListNew = attachedAlertaListNew;
            dispositivo.setAlertaList(alertaListNew);
            List<Parametros> attachedParametrosListNew = new ArrayList<Parametros>();
            for (Parametros parametrosListNewParametrosToAttach : parametrosListNew) {
                parametrosListNewParametrosToAttach = em.getReference(parametrosListNewParametrosToAttach.getClass(), parametrosListNewParametrosToAttach.getId());
                attachedParametrosListNew.add(parametrosListNewParametrosToAttach);
            }
            parametrosListNew = attachedParametrosListNew;
            dispositivo.setParametrosList(parametrosListNew);*/
            dispositivo = em.merge(dispositivo);
            /*if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getDispositivoList().remove(dispositivo);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getDispositivoList().add(dispositivo);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idrangoOld != null && !idrangoOld.equals(idrangoNew)) {
                idrangoOld.getDispositivoList().remove(dispositivo);
                idrangoOld = em.merge(idrangoOld);
            }
            if (idrangoNew != null && !idrangoNew.equals(idrangoOld)) {
                idrangoNew.getDispositivoList().add(dispositivo);
                idrangoNew = em.merge(idrangoNew);
            }
            for (Alerta alertaListNewAlerta : alertaListNew) {
                if (!alertaListOld.contains(alertaListNewAlerta)) {
                    Dispositivo oldIddispositivoOfAlertaListNewAlerta = alertaListNewAlerta.getIddispositivo();
                    alertaListNewAlerta.setIddispositivo(dispositivo);
                    alertaListNewAlerta = em.merge(alertaListNewAlerta);
                    if (oldIddispositivoOfAlertaListNewAlerta != null && !oldIddispositivoOfAlertaListNewAlerta.equals(dispositivo)) {
                        oldIddispositivoOfAlertaListNewAlerta.getAlertaList().remove(alertaListNewAlerta);
                        oldIddispositivoOfAlertaListNewAlerta = em.merge(oldIddispositivoOfAlertaListNewAlerta);
                    }
                }
            }
            for (Parametros parametrosListNewParametros : parametrosListNew) {
                if (!parametrosListOld.contains(parametrosListNewParametros)) {
                    Dispositivo oldIddispositivoOfParametrosListNewParametros = parametrosListNewParametros.getIddispositivo();
                    parametrosListNewParametros.setIddispositivo(dispositivo);
                    parametrosListNewParametros = em.merge(parametrosListNewParametros);
                    if (oldIddispositivoOfParametrosListNewParametros != null && !oldIddispositivoOfParametrosListNewParametros.equals(dispositivo)) {
                        oldIddispositivoOfParametrosListNewParametros.getParametrosList().remove(parametrosListNewParametros);
                        oldIddispositivoOfParametrosListNewParametros = em.merge(oldIddispositivoOfParametrosListNewParametros);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dispositivo.getId();
                if (findDispositivo(id) == null) {
                    throw new NonexistentEntityException("The dispositivo with id " + id + " no longer exists.");
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
            Dispositivo dispositivo;
            try {
                dispositivo = em.getReference(Dispositivo.class, id);
                dispositivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dispositivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Alerta> alertaListOrphanCheck = dispositivo.getAlertaList();
            for (Alerta alertaListOrphanCheckAlerta : alertaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dispositivo (" + dispositivo + ") cannot be destroyed since the Alerta " + alertaListOrphanCheckAlerta + " in its alertaList field has a non-nullable iddispositivo field.");
            }
            List<Parametros> parametrosListOrphanCheck = dispositivo.getParametrosList();
            for (Parametros parametrosListOrphanCheckParametros : parametrosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dispositivo (" + dispositivo + ") cannot be destroyed since the Parametros " + parametrosListOrphanCheckParametros + " in its parametrosList field has a non-nullable iddispositivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = dispositivo.getIdusuario();
            if (idusuario != null) {
                idusuario.getDispositivoList().remove(dispositivo);
                idusuario = em.merge(idusuario);
            }
            RangoParametros idrango = dispositivo.getIdrango();
            if (idrango != null) {
                idrango.getDispositivoList().remove(dispositivo);
                idrango = em.merge(idrango);
            }
            em.remove(dispositivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dispositivo> findDispositivoEntities() {
        return findDispositivoEntities(true, -1, -1);
    }

    public List<Dispositivo> findDispositivoEntities(int maxResults, int firstResult) {
        return findDispositivoEntities(false, maxResults, firstResult);
    }

    private List<Dispositivo> findDispositivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dispositivo.class));
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

    public Dispositivo findDispositivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dispositivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDispositivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dispositivo> rt = cq.from(Dispositivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
