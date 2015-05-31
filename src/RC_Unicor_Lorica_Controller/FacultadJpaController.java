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
import RC_Unicor_Lorica_Entity.Universidad;
import RC_Unicor_Lorica_Entity.Docente;
import RC_Unicor_Lorica_Entity.Facultad;
import java.util.ArrayList;
import java.util.List;
import RC_Unicor_Lorica_Entity.Programa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class FacultadJpaController implements Serializable {

    public FacultadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facultad facultad) {
        if (facultad.getDocenteList() == null) {
            facultad.setDocenteList(new ArrayList<Docente>());
        }
        if (facultad.getProgramaList() == null) {
            facultad.setProgramaList(new ArrayList<Programa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad universidadid = facultad.getUniversidadid();
            if (universidadid != null) {
                universidadid = em.getReference(universidadid.getClass(), universidadid.getId());
                facultad.setUniversidadid(universidadid);
            }
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : facultad.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            facultad.setDocenteList(attachedDocenteList);
            List<Programa> attachedProgramaList = new ArrayList<Programa>();
            for (Programa programaListProgramaToAttach : facultad.getProgramaList()) {
                programaListProgramaToAttach = em.getReference(programaListProgramaToAttach.getClass(), programaListProgramaToAttach.getId());
                attachedProgramaList.add(programaListProgramaToAttach);
            }
            facultad.setProgramaList(attachedProgramaList);
            em.persist(facultad);
            if (universidadid != null) {
                universidadid.getFacultadList().add(facultad);
                universidadid = em.merge(universidadid);
            }
            for (Docente docenteListDocente : facultad.getDocenteList()) {
                Facultad oldFacultadidOfDocenteListDocente = docenteListDocente.getFacultadid();
                docenteListDocente.setFacultadid(facultad);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldFacultadidOfDocenteListDocente != null) {
                    oldFacultadidOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldFacultadidOfDocenteListDocente = em.merge(oldFacultadidOfDocenteListDocente);
                }
            }
            for (Programa programaListPrograma : facultad.getProgramaList()) {
                Facultad oldFacultadidOfProgramaListPrograma = programaListPrograma.getFacultadid();
                programaListPrograma.setFacultadid(facultad);
                programaListPrograma = em.merge(programaListPrograma);
                if (oldFacultadidOfProgramaListPrograma != null) {
                    oldFacultadidOfProgramaListPrograma.getProgramaList().remove(programaListPrograma);
                    oldFacultadidOfProgramaListPrograma = em.merge(oldFacultadidOfProgramaListPrograma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facultad facultad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad persistentFacultad = em.find(Facultad.class, facultad.getId());
            Universidad universidadidOld = persistentFacultad.getUniversidadid();
            Universidad universidadidNew = facultad.getUniversidadid();
            List<Docente> docenteListOld = persistentFacultad.getDocenteList();
            List<Docente> docenteListNew = facultad.getDocenteList();
            List<Programa> programaListOld = persistentFacultad.getProgramaList();
            List<Programa> programaListNew = facultad.getProgramaList();
            List<String> illegalOrphanMessages = null;
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its facultadid field is not nullable.");
                }
            }
            for (Programa programaListOldPrograma : programaListOld) {
                if (!programaListNew.contains(programaListOldPrograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programa " + programaListOldPrograma + " since its facultadid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (universidadidNew != null) {
                universidadidNew = em.getReference(universidadidNew.getClass(), universidadidNew.getId());
                facultad.setUniversidadid(universidadidNew);
            }
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            facultad.setDocenteList(docenteListNew);
            List<Programa> attachedProgramaListNew = new ArrayList<Programa>();
            for (Programa programaListNewProgramaToAttach : programaListNew) {
                programaListNewProgramaToAttach = em.getReference(programaListNewProgramaToAttach.getClass(), programaListNewProgramaToAttach.getId());
                attachedProgramaListNew.add(programaListNewProgramaToAttach);
            }
            programaListNew = attachedProgramaListNew;
            facultad.setProgramaList(programaListNew);
            facultad = em.merge(facultad);
            if (universidadidOld != null && !universidadidOld.equals(universidadidNew)) {
                universidadidOld.getFacultadList().remove(facultad);
                universidadidOld = em.merge(universidadidOld);
            }
            if (universidadidNew != null && !universidadidNew.equals(universidadidOld)) {
                universidadidNew.getFacultadList().add(facultad);
                universidadidNew = em.merge(universidadidNew);
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Facultad oldFacultadidOfDocenteListNewDocente = docenteListNewDocente.getFacultadid();
                    docenteListNewDocente.setFacultadid(facultad);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldFacultadidOfDocenteListNewDocente != null && !oldFacultadidOfDocenteListNewDocente.equals(facultad)) {
                        oldFacultadidOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldFacultadidOfDocenteListNewDocente = em.merge(oldFacultadidOfDocenteListNewDocente);
                    }
                }
            }
            for (Programa programaListNewPrograma : programaListNew) {
                if (!programaListOld.contains(programaListNewPrograma)) {
                    Facultad oldFacultadidOfProgramaListNewPrograma = programaListNewPrograma.getFacultadid();
                    programaListNewPrograma.setFacultadid(facultad);
                    programaListNewPrograma = em.merge(programaListNewPrograma);
                    if (oldFacultadidOfProgramaListNewPrograma != null && !oldFacultadidOfProgramaListNewPrograma.equals(facultad)) {
                        oldFacultadidOfProgramaListNewPrograma.getProgramaList().remove(programaListNewPrograma);
                        oldFacultadidOfProgramaListNewPrograma = em.merge(oldFacultadidOfProgramaListNewPrograma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facultad.getId();
                if (findFacultad(id) == null) {
                    throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.");
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
            Facultad facultad;
            try {
                facultad = em.getReference(Facultad.class, id);
                facultad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Docente> docenteListOrphanCheck = facultad.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facultad (" + facultad + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable facultadid field.");
            }
            List<Programa> programaListOrphanCheck = facultad.getProgramaList();
            for (Programa programaListOrphanCheckPrograma : programaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facultad (" + facultad + ") cannot be destroyed since the Programa " + programaListOrphanCheckPrograma + " in its programaList field has a non-nullable facultadid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Universidad universidadid = facultad.getUniversidadid();
            if (universidadid != null) {
                universidadid.getFacultadList().remove(facultad);
                universidadid = em.merge(universidadid);
            }
            em.remove(facultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facultad> findFacultadEntities() {
        return findFacultadEntities(true, -1, -1);
    }

    public List<Facultad> findFacultadEntities(int maxResults, int firstResult) {
        return findFacultadEntities(false, maxResults, firstResult);
    }

    private List<Facultad> findFacultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facultad.class));
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

    public Facultad findFacultad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facultad> rt = cq.from(Facultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
