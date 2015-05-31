/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RC_Unicor_Lorica_Controller;

import RC_Unicor_Lorica_Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RC_Unicor_Lorica_Entity.Docente;
import RC_Unicor_Lorica_Entity.Salon;
import RC_Unicor_Lorica_Entity.Curso;
import RC_Unicor_Lorica_Entity.RegistroAsistenciaDocente;
import RC_Unicor_Lorica_Entity.UtensiliosDeClase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class RegistroAsistenciaDocenteJpaController implements Serializable {

    public RegistroAsistenciaDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroAsistenciaDocente registroAsistenciaDocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docenteidDocente = registroAsistenciaDocente.getDocenteidDocente();
            if (docenteidDocente != null) {
                docenteidDocente = em.getReference(docenteidDocente.getClass(), docenteidDocente.getIdDocente());
                registroAsistenciaDocente.setDocenteidDocente(docenteidDocente);
            }
            Salon salonid = registroAsistenciaDocente.getSalonid();
            if (salonid != null) {
                salonid = em.getReference(salonid.getClass(), salonid.getId());
                registroAsistenciaDocente.setSalonid(salonid);
            }
            Curso cursoid = registroAsistenciaDocente.getCursoid();
            if (cursoid != null) {
                cursoid = em.getReference(cursoid.getClass(), cursoid.getId());
                registroAsistenciaDocente.setCursoid(cursoid);
            }
            UtensiliosDeClase utensiliosDeClaseid = registroAsistenciaDocente.getUtensiliosDeClaseid();
            if (utensiliosDeClaseid != null) {
                utensiliosDeClaseid = em.getReference(utensiliosDeClaseid.getClass(), utensiliosDeClaseid.getId());
                registroAsistenciaDocente.setUtensiliosDeClaseid(utensiliosDeClaseid);
            }
            em.persist(registroAsistenciaDocente);
            if (docenteidDocente != null) {
                docenteidDocente.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                docenteidDocente = em.merge(docenteidDocente);
            }
            if (salonid != null) {
                salonid.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                salonid = em.merge(salonid);
            }
            if (cursoid != null) {
                cursoid.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                cursoid = em.merge(cursoid);
            }
            if (utensiliosDeClaseid != null) {
                utensiliosDeClaseid.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                utensiliosDeClaseid = em.merge(utensiliosDeClaseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroAsistenciaDocente registroAsistenciaDocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroAsistenciaDocente persistentRegistroAsistenciaDocente = em.find(RegistroAsistenciaDocente.class, registroAsistenciaDocente.getId());
            Docente docenteidDocenteOld = persistentRegistroAsistenciaDocente.getDocenteidDocente();
            Docente docenteidDocenteNew = registroAsistenciaDocente.getDocenteidDocente();
            Salon salonidOld = persistentRegistroAsistenciaDocente.getSalonid();
            Salon salonidNew = registroAsistenciaDocente.getSalonid();
            Curso cursoidOld = persistentRegistroAsistenciaDocente.getCursoid();
            Curso cursoidNew = registroAsistenciaDocente.getCursoid();
            UtensiliosDeClase utensiliosDeClaseidOld = persistentRegistroAsistenciaDocente.getUtensiliosDeClaseid();
            UtensiliosDeClase utensiliosDeClaseidNew = registroAsistenciaDocente.getUtensiliosDeClaseid();
            if (docenteidDocenteNew != null) {
                docenteidDocenteNew = em.getReference(docenteidDocenteNew.getClass(), docenteidDocenteNew.getIdDocente());
                registroAsistenciaDocente.setDocenteidDocente(docenteidDocenteNew);
            }
            if (salonidNew != null) {
                salonidNew = em.getReference(salonidNew.getClass(), salonidNew.getId());
                registroAsistenciaDocente.setSalonid(salonidNew);
            }
            if (cursoidNew != null) {
                cursoidNew = em.getReference(cursoidNew.getClass(), cursoidNew.getId());
                registroAsistenciaDocente.setCursoid(cursoidNew);
            }
            if (utensiliosDeClaseidNew != null) {
                utensiliosDeClaseidNew = em.getReference(utensiliosDeClaseidNew.getClass(), utensiliosDeClaseidNew.getId());
                registroAsistenciaDocente.setUtensiliosDeClaseid(utensiliosDeClaseidNew);
            }
            registroAsistenciaDocente = em.merge(registroAsistenciaDocente);
            if (docenteidDocenteOld != null && !docenteidDocenteOld.equals(docenteidDocenteNew)) {
                docenteidDocenteOld.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                docenteidDocenteOld = em.merge(docenteidDocenteOld);
            }
            if (docenteidDocenteNew != null && !docenteidDocenteNew.equals(docenteidDocenteOld)) {
                docenteidDocenteNew.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                docenteidDocenteNew = em.merge(docenteidDocenteNew);
            }
            if (salonidOld != null && !salonidOld.equals(salonidNew)) {
                salonidOld.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                salonidOld = em.merge(salonidOld);
            }
            if (salonidNew != null && !salonidNew.equals(salonidOld)) {
                salonidNew.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                salonidNew = em.merge(salonidNew);
            }
            if (cursoidOld != null && !cursoidOld.equals(cursoidNew)) {
                cursoidOld.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                cursoidOld = em.merge(cursoidOld);
            }
            if (cursoidNew != null && !cursoidNew.equals(cursoidOld)) {
                cursoidNew.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                cursoidNew = em.merge(cursoidNew);
            }
            if (utensiliosDeClaseidOld != null && !utensiliosDeClaseidOld.equals(utensiliosDeClaseidNew)) {
                utensiliosDeClaseidOld.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                utensiliosDeClaseidOld = em.merge(utensiliosDeClaseidOld);
            }
            if (utensiliosDeClaseidNew != null && !utensiliosDeClaseidNew.equals(utensiliosDeClaseidOld)) {
                utensiliosDeClaseidNew.getRegistroAsistenciaDocenteList().add(registroAsistenciaDocente);
                utensiliosDeClaseidNew = em.merge(utensiliosDeClaseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroAsistenciaDocente.getId();
                if (findRegistroAsistenciaDocente(id) == null) {
                    throw new NonexistentEntityException("The registroAsistenciaDocente with id " + id + " no longer exists.");
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
            RegistroAsistenciaDocente registroAsistenciaDocente;
            try {
                registroAsistenciaDocente = em.getReference(RegistroAsistenciaDocente.class, id);
                registroAsistenciaDocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroAsistenciaDocente with id " + id + " no longer exists.", enfe);
            }
            Docente docenteidDocente = registroAsistenciaDocente.getDocenteidDocente();
            if (docenteidDocente != null) {
                docenteidDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                docenteidDocente = em.merge(docenteidDocente);
            }
            Salon salonid = registroAsistenciaDocente.getSalonid();
            if (salonid != null) {
                salonid.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                salonid = em.merge(salonid);
            }
            Curso cursoid = registroAsistenciaDocente.getCursoid();
            if (cursoid != null) {
                cursoid.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                cursoid = em.merge(cursoid);
            }
            UtensiliosDeClase utensiliosDeClaseid = registroAsistenciaDocente.getUtensiliosDeClaseid();
            if (utensiliosDeClaseid != null) {
                utensiliosDeClaseid.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocente);
                utensiliosDeClaseid = em.merge(utensiliosDeClaseid);
            }
            em.remove(registroAsistenciaDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroAsistenciaDocente> findRegistroAsistenciaDocenteEntities() {
        return findRegistroAsistenciaDocenteEntities(true, -1, -1);
    }

    public List<RegistroAsistenciaDocente> findRegistroAsistenciaDocenteEntities(int maxResults, int firstResult) {
        return findRegistroAsistenciaDocenteEntities(false, maxResults, firstResult);
    }

    private List<RegistroAsistenciaDocente> findRegistroAsistenciaDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroAsistenciaDocente.class));
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

    public RegistroAsistenciaDocente findRegistroAsistenciaDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroAsistenciaDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroAsistenciaDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroAsistenciaDocente> rt = cq.from(RegistroAsistenciaDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
