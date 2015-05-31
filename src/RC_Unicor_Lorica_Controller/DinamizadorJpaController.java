/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RC_Unicor_Lorica_Controller;

import RC_Unicor_Lorica_Controller.exceptions.NonexistentEntityException;
import RC_Unicor_Lorica_Entity.Dinamizador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RC_Unicor_Lorica_Entity.Sede;
import RC_Unicor_Lorica_Entity.InformacionBasica;
import RC_Unicor_Lorica_Entity.InformacionDeSeguridad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class DinamizadorJpaController implements Serializable {

    public DinamizadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dinamizador dinamizador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sedeid = dinamizador.getSedeid();
            if (sedeid != null) {
                sedeid = em.getReference(sedeid.getClass(), sedeid.getId());
                dinamizador.setSedeid(sedeid);
            }
            InformacionBasica informacionBasicaId = dinamizador.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId = em.getReference(informacionBasicaId.getClass(), informacionBasicaId.getId());
                dinamizador.setInformacionBasicaId(informacionBasicaId);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = dinamizador.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI = em.getReference(informacionDeSeguridadidI.getClass(), informacionDeSeguridadidI.getIdI());
                dinamizador.setInformacionDeSeguridadidI(informacionDeSeguridadidI);
            }
            em.persist(dinamizador);
            if (sedeid != null) {
                sedeid.getDinamizadorList().add(dinamizador);
                sedeid = em.merge(sedeid);
            }
            if (informacionBasicaId != null) {
                informacionBasicaId.getDinamizadorList().add(dinamizador);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getDinamizadorList().add(dinamizador);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dinamizador dinamizador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dinamizador persistentDinamizador = em.find(Dinamizador.class, dinamizador.getIdDinamizador());
            Sede sedeidOld = persistentDinamizador.getSedeid();
            Sede sedeidNew = dinamizador.getSedeid();
            InformacionBasica informacionBasicaIdOld = persistentDinamizador.getInformacionBasicaId();
            InformacionBasica informacionBasicaIdNew = dinamizador.getInformacionBasicaId();
            InformacionDeSeguridad informacionDeSeguridadidIOld = persistentDinamizador.getInformacionDeSeguridadidI();
            InformacionDeSeguridad informacionDeSeguridadidINew = dinamizador.getInformacionDeSeguridadidI();
            if (sedeidNew != null) {
                sedeidNew = em.getReference(sedeidNew.getClass(), sedeidNew.getId());
                dinamizador.setSedeid(sedeidNew);
            }
            if (informacionBasicaIdNew != null) {
                informacionBasicaIdNew = em.getReference(informacionBasicaIdNew.getClass(), informacionBasicaIdNew.getId());
                dinamizador.setInformacionBasicaId(informacionBasicaIdNew);
            }
            if (informacionDeSeguridadidINew != null) {
                informacionDeSeguridadidINew = em.getReference(informacionDeSeguridadidINew.getClass(), informacionDeSeguridadidINew.getIdI());
                dinamizador.setInformacionDeSeguridadidI(informacionDeSeguridadidINew);
            }
            dinamizador = em.merge(dinamizador);
            if (sedeidOld != null && !sedeidOld.equals(sedeidNew)) {
                sedeidOld.getDinamizadorList().remove(dinamizador);
                sedeidOld = em.merge(sedeidOld);
            }
            if (sedeidNew != null && !sedeidNew.equals(sedeidOld)) {
                sedeidNew.getDinamizadorList().add(dinamizador);
                sedeidNew = em.merge(sedeidNew);
            }
            if (informacionBasicaIdOld != null && !informacionBasicaIdOld.equals(informacionBasicaIdNew)) {
                informacionBasicaIdOld.getDinamizadorList().remove(dinamizador);
                informacionBasicaIdOld = em.merge(informacionBasicaIdOld);
            }
            if (informacionBasicaIdNew != null && !informacionBasicaIdNew.equals(informacionBasicaIdOld)) {
                informacionBasicaIdNew.getDinamizadorList().add(dinamizador);
                informacionBasicaIdNew = em.merge(informacionBasicaIdNew);
            }
            if (informacionDeSeguridadidIOld != null && !informacionDeSeguridadidIOld.equals(informacionDeSeguridadidINew)) {
                informacionDeSeguridadidIOld.getDinamizadorList().remove(dinamizador);
                informacionDeSeguridadidIOld = em.merge(informacionDeSeguridadidIOld);
            }
            if (informacionDeSeguridadidINew != null && !informacionDeSeguridadidINew.equals(informacionDeSeguridadidIOld)) {
                informacionDeSeguridadidINew.getDinamizadorList().add(dinamizador);
                informacionDeSeguridadidINew = em.merge(informacionDeSeguridadidINew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dinamizador.getIdDinamizador();
                if (findDinamizador(id) == null) {
                    throw new NonexistentEntityException("The dinamizador with id " + id + " no longer exists.");
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
            Dinamizador dinamizador;
            try {
                dinamizador = em.getReference(Dinamizador.class, id);
                dinamizador.getIdDinamizador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dinamizador with id " + id + " no longer exists.", enfe);
            }
            Sede sedeid = dinamizador.getSedeid();
            if (sedeid != null) {
                sedeid.getDinamizadorList().remove(dinamizador);
                sedeid = em.merge(sedeid);
            }
            InformacionBasica informacionBasicaId = dinamizador.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId.getDinamizadorList().remove(dinamizador);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = dinamizador.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getDinamizadorList().remove(dinamizador);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            em.remove(dinamizador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dinamizador> findDinamizadorEntities() {
        return findDinamizadorEntities(true, -1, -1);
    }

    public List<Dinamizador> findDinamizadorEntities(int maxResults, int firstResult) {
        return findDinamizadorEntities(false, maxResults, firstResult);
    }

    private List<Dinamizador> findDinamizadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dinamizador.class));
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

    public Dinamizador findDinamizador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dinamizador.class, id);
        } finally {
            em.close();
        }
    }

    public int getDinamizadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dinamizador> rt = cq.from(Dinamizador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
