/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Controlador;

import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.IllegalOrphanException;
import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import java.util.ArrayList;
import java.util.List;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Horario;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) {
        if (materia.getDocenteList() == null) {
            materia.setDocenteList(new ArrayList<Docente>());
        }
        if (materia.getCursoList() == null) {
            materia.setCursoList(new ArrayList<Curso>());
        }
        if (materia.getEstudianteList() == null) {
            materia.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (materia.getHorarioList() == null) {
            materia.setHorarioList(new ArrayList<Horario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : materia.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            materia.setDocenteList(attachedDocenteList);
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : materia.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getId());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            materia.setCursoList(attachedCursoList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : materia.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiante());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            materia.setEstudianteList(attachedEstudianteList);
            List<Horario> attachedHorarioList = new ArrayList<Horario>();
            for (Horario horarioListHorarioToAttach : materia.getHorarioList()) {
                horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getId());
                attachedHorarioList.add(horarioListHorarioToAttach);
            }
            materia.setHorarioList(attachedHorarioList);
            em.persist(materia);
            for (Docente docenteListDocente : materia.getDocenteList()) {
                docenteListDocente.getMateriaList().add(materia);
                docenteListDocente = em.merge(docenteListDocente);
            }
            for (Curso cursoListCurso : materia.getCursoList()) {
                cursoListCurso.getMateriaList().add(materia);
                cursoListCurso = em.merge(cursoListCurso);
            }
            for (Estudiante estudianteListEstudiante : materia.getEstudianteList()) {
                estudianteListEstudiante.getMateriaList().add(materia);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            for (Horario horarioListHorario : materia.getHorarioList()) {
                Materia oldMateriaidOfHorarioListHorario = horarioListHorario.getMateriaid();
                horarioListHorario.setMateriaid(materia);
                horarioListHorario = em.merge(horarioListHorario);
                if (oldMateriaidOfHorarioListHorario != null) {
                    oldMateriaidOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
                    oldMateriaidOfHorarioListHorario = em.merge(oldMateriaidOfHorarioListHorario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getId());
            List<Docente> docenteListOld = persistentMateria.getDocenteList();
            List<Docente> docenteListNew = materia.getDocenteList();
            List<Curso> cursoListOld = persistentMateria.getCursoList();
            List<Curso> cursoListNew = materia.getCursoList();
            List<Estudiante> estudianteListOld = persistentMateria.getEstudianteList();
            List<Estudiante> estudianteListNew = materia.getEstudianteList();
            List<Horario> horarioListOld = persistentMateria.getHorarioList();
            List<Horario> horarioListNew = materia.getHorarioList();
            List<String> illegalOrphanMessages = null;
            for (Horario horarioListOldHorario : horarioListOld) {
                if (!horarioListNew.contains(horarioListOldHorario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its materiaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            materia.setDocenteList(docenteListNew);
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getId());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            materia.setCursoList(cursoListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiante());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            materia.setEstudianteList(estudianteListNew);
            List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
            for (Horario horarioListNewHorarioToAttach : horarioListNew) {
                horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getId());
                attachedHorarioListNew.add(horarioListNewHorarioToAttach);
            }
            horarioListNew = attachedHorarioListNew;
            materia.setHorarioList(horarioListNew);
            materia = em.merge(materia);
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    docenteListOldDocente.getMateriaList().remove(materia);
                    docenteListOldDocente = em.merge(docenteListOldDocente);
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    docenteListNewDocente.getMateriaList().add(materia);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                }
            }
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    cursoListOldCurso.getMateriaList().remove(materia);
                    cursoListOldCurso = em.merge(cursoListOldCurso);
                }
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    cursoListNewCurso.getMateriaList().add(materia);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.getMateriaList().remove(materia);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    estudianteListNewEstudiante.getMateriaList().add(materia);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                }
            }
            for (Horario horarioListNewHorario : horarioListNew) {
                if (!horarioListOld.contains(horarioListNewHorario)) {
                    Materia oldMateriaidOfHorarioListNewHorario = horarioListNewHorario.getMateriaid();
                    horarioListNewHorario.setMateriaid(materia);
                    horarioListNewHorario = em.merge(horarioListNewHorario);
                    if (oldMateriaidOfHorarioListNewHorario != null && !oldMateriaidOfHorarioListNewHorario.equals(materia)) {
                        oldMateriaidOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
                        oldMateriaidOfHorarioListNewHorario = em.merge(oldMateriaidOfHorarioListNewHorario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materia.getId();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
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
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Horario> horarioListOrphanCheck = materia.getHorarioList();
            for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materia (" + materia + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable materiaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Docente> docenteList = materia.getDocenteList();
            for (Docente docenteListDocente : docenteList) {
                docenteListDocente.getMateriaList().remove(materia);
                docenteListDocente = em.merge(docenteListDocente);
            }
            List<Curso> cursoList = materia.getCursoList();
            for (Curso cursoListCurso : cursoList) {
                cursoListCurso.getMateriaList().remove(materia);
                cursoListCurso = em.merge(cursoListCurso);
            }
            List<Estudiante> estudianteList = materia.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.getMateriaList().remove(materia);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
