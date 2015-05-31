/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RC_Unicor_Lorica_Controller;

import RC_Unicor_Lorica_Controller.exceptions.IllegalOrphanException;
import RC_Unicor_Lorica_Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RC_Unicor_Lorica_Entity.Sede;
import RC_Unicor_Lorica_Entity.RegistroAsistenciaDocente;
import java.util.ArrayList;
import java.util.List;
import RC_Unicor_Lorica_Entity.Curso;
import RC_Unicor_Lorica_Entity.Salon;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class SalonJpaController implements Serializable {

    public SalonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salon salon) {
        if (salon.getRegistroAsistenciaDocenteList() == null) {
            salon.setRegistroAsistenciaDocenteList(new ArrayList<RegistroAsistenciaDocente>());
        }
        if (salon.getCursoList() == null) {
            salon.setCursoList(new ArrayList<Curso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sedeid = salon.getSedeid();
            if (sedeid != null) {
                sedeid = em.getReference(sedeid.getClass(), sedeid.getId());
                salon.setSedeid(sedeid);
            }
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteList = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach : salon.getRegistroAsistenciaDocenteList()) {
                registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteList.add(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach);
            }
            salon.setRegistroAsistenciaDocenteList(attachedRegistroAsistenciaDocenteList);
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : salon.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getId());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            salon.setCursoList(attachedCursoList);
            em.persist(salon);
            if (sedeid != null) {
                sedeid.getSalonList().add(salon);
                sedeid = em.merge(sedeid);
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocente : salon.getRegistroAsistenciaDocenteList()) {
                Salon oldSalonidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = registroAsistenciaDocenteListRegistroAsistenciaDocente.getSalonid();
                registroAsistenciaDocenteListRegistroAsistenciaDocente.setSalonid(salon);
                registroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                if (oldSalonidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente != null) {
                    oldSalonidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                    oldSalonidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(oldSalonidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente);
                }
            }
            for (Curso cursoListCurso : salon.getCursoList()) {
                Salon oldSalonidOfCursoListCurso = cursoListCurso.getSalonid();
                cursoListCurso.setSalonid(salon);
                cursoListCurso = em.merge(cursoListCurso);
                if (oldSalonidOfCursoListCurso != null) {
                    oldSalonidOfCursoListCurso.getCursoList().remove(cursoListCurso);
                    oldSalonidOfCursoListCurso = em.merge(oldSalonidOfCursoListCurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salon salon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon persistentSalon = em.find(Salon.class, salon.getId());
            Sede sedeidOld = persistentSalon.getSedeid();
            Sede sedeidNew = salon.getSedeid();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOld = persistentSalon.getRegistroAsistenciaDocenteList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListNew = salon.getRegistroAsistenciaDocenteList();
            List<Curso> cursoListOld = persistentSalon.getCursoList();
            List<Curso> cursoListNew = salon.getCursoList();
            List<String> illegalOrphanMessages = null;
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOldRegistroAsistenciaDocente : registroAsistenciaDocenteListOld) {
                if (!registroAsistenciaDocenteListNew.contains(registroAsistenciaDocenteListOldRegistroAsistenciaDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroAsistenciaDocente " + registroAsistenciaDocenteListOldRegistroAsistenciaDocente + " since its salonid field is not nullable.");
                }
            }
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Curso " + cursoListOldCurso + " since its salonid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sedeidNew != null) {
                sedeidNew = em.getReference(sedeidNew.getClass(), sedeidNew.getId());
                salon.setSedeid(sedeidNew);
            }
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteListNew = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach : registroAsistenciaDocenteListNew) {
                registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteListNew.add(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach);
            }
            registroAsistenciaDocenteListNew = attachedRegistroAsistenciaDocenteListNew;
            salon.setRegistroAsistenciaDocenteList(registroAsistenciaDocenteListNew);
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getId());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            salon.setCursoList(cursoListNew);
            salon = em.merge(salon);
            if (sedeidOld != null && !sedeidOld.equals(sedeidNew)) {
                sedeidOld.getSalonList().remove(salon);
                sedeidOld = em.merge(sedeidOld);
            }
            if (sedeidNew != null && !sedeidNew.equals(sedeidOld)) {
                sedeidNew.getSalonList().add(salon);
                sedeidNew = em.merge(sedeidNew);
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocente : registroAsistenciaDocenteListNew) {
                if (!registroAsistenciaDocenteListOld.contains(registroAsistenciaDocenteListNewRegistroAsistenciaDocente)) {
                    Salon oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = registroAsistenciaDocenteListNewRegistroAsistenciaDocente.getSalonid();
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente.setSalonid(salon);
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    if (oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente != null && !oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.equals(salon)) {
                        oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                        oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(oldSalonidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    }
                }
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    Salon oldSalonidOfCursoListNewCurso = cursoListNewCurso.getSalonid();
                    cursoListNewCurso.setSalonid(salon);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                    if (oldSalonidOfCursoListNewCurso != null && !oldSalonidOfCursoListNewCurso.equals(salon)) {
                        oldSalonidOfCursoListNewCurso.getCursoList().remove(cursoListNewCurso);
                        oldSalonidOfCursoListNewCurso = em.merge(oldSalonidOfCursoListNewCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salon.getId();
                if (findSalon(id) == null) {
                    throw new NonexistentEntityException("The salon with id " + id + " no longer exists.");
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
            Salon salon;
            try {
                salon = em.getReference(Salon.class, id);
                salon.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOrphanCheck = salon.getRegistroAsistenciaDocenteList();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente : registroAsistenciaDocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Salon (" + salon + ") cannot be destroyed since the RegistroAsistenciaDocente " + registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente + " in its registroAsistenciaDocenteList field has a non-nullable salonid field.");
            }
            List<Curso> cursoListOrphanCheck = salon.getCursoList();
            for (Curso cursoListOrphanCheckCurso : cursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Salon (" + salon + ") cannot be destroyed since the Curso " + cursoListOrphanCheckCurso + " in its cursoList field has a non-nullable salonid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sede sedeid = salon.getSedeid();
            if (sedeid != null) {
                sedeid.getSalonList().remove(salon);
                sedeid = em.merge(sedeid);
            }
            em.remove(salon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salon> findSalonEntities() {
        return findSalonEntities(true, -1, -1);
    }

    public List<Salon> findSalonEntities(int maxResults, int firstResult) {
        return findSalonEntities(false, maxResults, firstResult);
    }

    private List<Salon> findSalonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salon.class));
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

    public Salon findSalon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salon.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salon> rt = cq.from(Salon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
