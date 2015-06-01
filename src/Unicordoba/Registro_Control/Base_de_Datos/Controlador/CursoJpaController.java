/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Controlador;

import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.IllegalOrphanException;
import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.NonexistentEntityException;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Programa;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import java.util.ArrayList;
import java.util.List;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Curso curso) {
        if (curso.getMateriaList() == null) {
            curso.setMateriaList(new ArrayList<Materia>());
        }
        if (curso.getRegistroAsistenciaDocenteList() == null) {
            curso.setRegistroAsistenciaDocenteList(new ArrayList<RegistroAsistenciaDocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaid = curso.getProgramaid();
            if (programaid != null) {
                programaid = em.getReference(programaid.getClass(), programaid.getId());
                curso.setProgramaid(programaid);
            }
            Salon salonid = curso.getSalonid();
            if (salonid != null) {
                salonid = em.getReference(salonid.getClass(), salonid.getId());
                curso.setSalonid(salonid);
            }
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : curso.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getId());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            curso.setMateriaList(attachedMateriaList);
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteList = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach : curso.getRegistroAsistenciaDocenteList()) {
                registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteList.add(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach);
            }
            curso.setRegistroAsistenciaDocenteList(attachedRegistroAsistenciaDocenteList);
            em.persist(curso);
            if (programaid != null) {
                programaid.getCursoList().add(curso);
                programaid = em.merge(programaid);
            }
            if (salonid != null) {
                salonid.getCursoList().add(curso);
                salonid = em.merge(salonid);
            }
            for (Materia materiaListMateria : curso.getMateriaList()) {
                materiaListMateria.getCursoList().add(curso);
                materiaListMateria = em.merge(materiaListMateria);
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocente : curso.getRegistroAsistenciaDocenteList()) {
                Curso oldCursoidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = registroAsistenciaDocenteListRegistroAsistenciaDocente.getCursoid();
                registroAsistenciaDocenteListRegistroAsistenciaDocente.setCursoid(curso);
                registroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                if (oldCursoidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente != null) {
                    oldCursoidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                    oldCursoidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(oldCursoidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getId());
            Programa programaidOld = persistentCurso.getProgramaid();
            Programa programaidNew = curso.getProgramaid();
            Salon salonidOld = persistentCurso.getSalonid();
            Salon salonidNew = curso.getSalonid();
            List<Materia> materiaListOld = persistentCurso.getMateriaList();
            List<Materia> materiaListNew = curso.getMateriaList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOld = persistentCurso.getRegistroAsistenciaDocenteList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListNew = curso.getRegistroAsistenciaDocenteList();
            List<String> illegalOrphanMessages = null;
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOldRegistroAsistenciaDocente : registroAsistenciaDocenteListOld) {
                if (!registroAsistenciaDocenteListNew.contains(registroAsistenciaDocenteListOldRegistroAsistenciaDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroAsistenciaDocente " + registroAsistenciaDocenteListOldRegistroAsistenciaDocente + " since its cursoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaidNew != null) {
                programaidNew = em.getReference(programaidNew.getClass(), programaidNew.getId());
                curso.setProgramaid(programaidNew);
            }
            if (salonidNew != null) {
                salonidNew = em.getReference(salonidNew.getClass(), salonidNew.getId());
                curso.setSalonid(salonidNew);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getId());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            curso.setMateriaList(materiaListNew);
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteListNew = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach : registroAsistenciaDocenteListNew) {
                registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteListNew.add(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach);
            }
            registroAsistenciaDocenteListNew = attachedRegistroAsistenciaDocenteListNew;
            curso.setRegistroAsistenciaDocenteList(registroAsistenciaDocenteListNew);
            curso = em.merge(curso);
            if (programaidOld != null && !programaidOld.equals(programaidNew)) {
                programaidOld.getCursoList().remove(curso);
                programaidOld = em.merge(programaidOld);
            }
            if (programaidNew != null && !programaidNew.equals(programaidOld)) {
                programaidNew.getCursoList().add(curso);
                programaidNew = em.merge(programaidNew);
            }
            if (salonidOld != null && !salonidOld.equals(salonidNew)) {
                salonidOld.getCursoList().remove(curso);
                salonidOld = em.merge(salonidOld);
            }
            if (salonidNew != null && !salonidNew.equals(salonidOld)) {
                salonidNew.getCursoList().add(curso);
                salonidNew = em.merge(salonidNew);
            }
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    materiaListOldMateria.getCursoList().remove(curso);
                    materiaListOldMateria = em.merge(materiaListOldMateria);
                }
            }
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    materiaListNewMateria.getCursoList().add(curso);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                }
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocente : registroAsistenciaDocenteListNew) {
                if (!registroAsistenciaDocenteListOld.contains(registroAsistenciaDocenteListNewRegistroAsistenciaDocente)) {
                    Curso oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = registroAsistenciaDocenteListNewRegistroAsistenciaDocente.getCursoid();
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente.setCursoid(curso);
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    if (oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente != null && !oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.equals(curso)) {
                        oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                        oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(oldCursoidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curso.getId();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOrphanCheck = curso.getRegistroAsistenciaDocenteList();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente : registroAsistenciaDocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the RegistroAsistenciaDocente " + registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente + " in its registroAsistenciaDocenteList field has a non-nullable cursoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaid = curso.getProgramaid();
            if (programaid != null) {
                programaid.getCursoList().remove(curso);
                programaid = em.merge(programaid);
            }
            Salon salonid = curso.getSalonid();
            if (salonid != null) {
                salonid.getCursoList().remove(curso);
                salonid = em.merge(salonid);
            }
            List<Materia> materiaList = curso.getMateriaList();
            for (Materia materiaListMateria : materiaList) {
                materiaListMateria.getCursoList().remove(curso);
                materiaListMateria = em.merge(materiaListMateria);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    public Curso findCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
