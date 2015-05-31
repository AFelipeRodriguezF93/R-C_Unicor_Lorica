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
import RC_Unicor_Lorica_Entity.Facultad;
import RC_Unicor_Lorica_Entity.Curso;
import RC_Unicor_Lorica_Entity.Programa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) {
        if (programa.getCursoList() == null) {
            programa.setCursoList(new ArrayList<Curso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultadid = programa.getFacultadid();
            if (facultadid != null) {
                facultadid = em.getReference(facultadid.getClass(), facultadid.getId());
                programa.setFacultadid(facultadid);
            }
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : programa.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getId());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            programa.setCursoList(attachedCursoList);
            em.persist(programa);
            if (facultadid != null) {
                facultadid.getProgramaList().add(programa);
                facultadid = em.merge(facultadid);
            }
            for (Curso cursoListCurso : programa.getCursoList()) {
                Programa oldProgramaidOfCursoListCurso = cursoListCurso.getProgramaid();
                cursoListCurso.setProgramaid(programa);
                cursoListCurso = em.merge(cursoListCurso);
                if (oldProgramaidOfCursoListCurso != null) {
                    oldProgramaidOfCursoListCurso.getCursoList().remove(cursoListCurso);
                    oldProgramaidOfCursoListCurso = em.merge(oldProgramaidOfCursoListCurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getId());
            Facultad facultadidOld = persistentPrograma.getFacultadid();
            Facultad facultadidNew = programa.getFacultadid();
            List<Curso> cursoListOld = persistentPrograma.getCursoList();
            List<Curso> cursoListNew = programa.getCursoList();
            List<String> illegalOrphanMessages = null;
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Curso " + cursoListOldCurso + " since its programaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facultadidNew != null) {
                facultadidNew = em.getReference(facultadidNew.getClass(), facultadidNew.getId());
                programa.setFacultadid(facultadidNew);
            }
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getId());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            programa.setCursoList(cursoListNew);
            programa = em.merge(programa);
            if (facultadidOld != null && !facultadidOld.equals(facultadidNew)) {
                facultadidOld.getProgramaList().remove(programa);
                facultadidOld = em.merge(facultadidOld);
            }
            if (facultadidNew != null && !facultadidNew.equals(facultadidOld)) {
                facultadidNew.getProgramaList().add(programa);
                facultadidNew = em.merge(facultadidNew);
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    Programa oldProgramaidOfCursoListNewCurso = cursoListNewCurso.getProgramaid();
                    cursoListNewCurso.setProgramaid(programa);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                    if (oldProgramaidOfCursoListNewCurso != null && !oldProgramaidOfCursoListNewCurso.equals(programa)) {
                        oldProgramaidOfCursoListNewCurso.getCursoList().remove(cursoListNewCurso);
                        oldProgramaidOfCursoListNewCurso = em.merge(oldProgramaidOfCursoListNewCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programa.getId();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Curso> cursoListOrphanCheck = programa.getCursoList();
            for (Curso cursoListOrphanCheckCurso : cursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Curso " + cursoListOrphanCheckCurso + " in its cursoList field has a non-nullable programaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facultadid = programa.getFacultadid();
            if (facultadid != null) {
                facultadid.getProgramaList().remove(programa);
                facultadid = em.merge(facultadid);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
