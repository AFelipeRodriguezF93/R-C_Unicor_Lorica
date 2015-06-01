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
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class InformacionDeSeguridadJpaController implements Serializable {

    public InformacionDeSeguridadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InformacionDeSeguridad informacionDeSeguridad) {
        if (informacionDeSeguridad.getEstudianteList() == null) {
            informacionDeSeguridad.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (informacionDeSeguridad.getDocenteList() == null) {
            informacionDeSeguridad.setDocenteList(new ArrayList<Docente>());
        }
        if (informacionDeSeguridad.getDinamizadorList() == null) {
            informacionDeSeguridad.setDinamizadorList(new ArrayList<Dinamizador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : informacionDeSeguridad.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiante());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            informacionDeSeguridad.setEstudianteList(attachedEstudianteList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : informacionDeSeguridad.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            informacionDeSeguridad.setDocenteList(attachedDocenteList);
            List<Dinamizador> attachedDinamizadorList = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListDinamizadorToAttach : informacionDeSeguridad.getDinamizadorList()) {
                dinamizadorListDinamizadorToAttach = em.getReference(dinamizadorListDinamizadorToAttach.getClass(), dinamizadorListDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorList.add(dinamizadorListDinamizadorToAttach);
            }
            informacionDeSeguridad.setDinamizadorList(attachedDinamizadorList);
            em.persist(informacionDeSeguridad);
            for (Estudiante estudianteListEstudiante : informacionDeSeguridad.getEstudianteList()) {
                InformacionDeSeguridad oldInformacionDeSeguridadidIOfEstudianteListEstudiante = estudianteListEstudiante.getInformacionDeSeguridadidI();
                estudianteListEstudiante.setInformacionDeSeguridadidI(informacionDeSeguridad);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldInformacionDeSeguridadidIOfEstudianteListEstudiante != null) {
                    oldInformacionDeSeguridadidIOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldInformacionDeSeguridadidIOfEstudianteListEstudiante = em.merge(oldInformacionDeSeguridadidIOfEstudianteListEstudiante);
                }
            }
            for (Docente docenteListDocente : informacionDeSeguridad.getDocenteList()) {
                InformacionDeSeguridad oldInformacionDeSeguridadidIOfDocenteListDocente = docenteListDocente.getInformacionDeSeguridadidI();
                docenteListDocente.setInformacionDeSeguridadidI(informacionDeSeguridad);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldInformacionDeSeguridadidIOfDocenteListDocente != null) {
                    oldInformacionDeSeguridadidIOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldInformacionDeSeguridadidIOfDocenteListDocente = em.merge(oldInformacionDeSeguridadidIOfDocenteListDocente);
                }
            }
            for (Dinamizador dinamizadorListDinamizador : informacionDeSeguridad.getDinamizadorList()) {
                InformacionDeSeguridad oldInformacionDeSeguridadidIOfDinamizadorListDinamizador = dinamizadorListDinamizador.getInformacionDeSeguridadidI();
                dinamizadorListDinamizador.setInformacionDeSeguridadidI(informacionDeSeguridad);
                dinamizadorListDinamizador = em.merge(dinamizadorListDinamizador);
                if (oldInformacionDeSeguridadidIOfDinamizadorListDinamizador != null) {
                    oldInformacionDeSeguridadidIOfDinamizadorListDinamizador.getDinamizadorList().remove(dinamizadorListDinamizador);
                    oldInformacionDeSeguridadidIOfDinamizadorListDinamizador = em.merge(oldInformacionDeSeguridadidIOfDinamizadorListDinamizador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InformacionDeSeguridad informacionDeSeguridad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InformacionDeSeguridad persistentInformacionDeSeguridad = em.find(InformacionDeSeguridad.class, informacionDeSeguridad.getIdI());
            List<Estudiante> estudianteListOld = persistentInformacionDeSeguridad.getEstudianteList();
            List<Estudiante> estudianteListNew = informacionDeSeguridad.getEstudianteList();
            List<Docente> docenteListOld = persistentInformacionDeSeguridad.getDocenteList();
            List<Docente> docenteListNew = informacionDeSeguridad.getDocenteList();
            List<Dinamizador> dinamizadorListOld = persistentInformacionDeSeguridad.getDinamizadorList();
            List<Dinamizador> dinamizadorListNew = informacionDeSeguridad.getDinamizadorList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its informacionDeSeguridadidI field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its informacionDeSeguridadidI field is not nullable.");
                }
            }
            for (Dinamizador dinamizadorListOldDinamizador : dinamizadorListOld) {
                if (!dinamizadorListNew.contains(dinamizadorListOldDinamizador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dinamizador " + dinamizadorListOldDinamizador + " since its informacionDeSeguridadidI field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiante());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            informacionDeSeguridad.setEstudianteList(estudianteListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            informacionDeSeguridad.setDocenteList(docenteListNew);
            List<Dinamizador> attachedDinamizadorListNew = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListNewDinamizadorToAttach : dinamizadorListNew) {
                dinamizadorListNewDinamizadorToAttach = em.getReference(dinamizadorListNewDinamizadorToAttach.getClass(), dinamizadorListNewDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorListNew.add(dinamizadorListNewDinamizadorToAttach);
            }
            dinamizadorListNew = attachedDinamizadorListNew;
            informacionDeSeguridad.setDinamizadorList(dinamizadorListNew);
            informacionDeSeguridad = em.merge(informacionDeSeguridad);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    InformacionDeSeguridad oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getInformacionDeSeguridadidI();
                    estudianteListNewEstudiante.setInformacionDeSeguridadidI(informacionDeSeguridad);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante != null && !oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante.equals(informacionDeSeguridad)) {
                        oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante = em.merge(oldInformacionDeSeguridadidIOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    InformacionDeSeguridad oldInformacionDeSeguridadidIOfDocenteListNewDocente = docenteListNewDocente.getInformacionDeSeguridadidI();
                    docenteListNewDocente.setInformacionDeSeguridadidI(informacionDeSeguridad);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldInformacionDeSeguridadidIOfDocenteListNewDocente != null && !oldInformacionDeSeguridadidIOfDocenteListNewDocente.equals(informacionDeSeguridad)) {
                        oldInformacionDeSeguridadidIOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldInformacionDeSeguridadidIOfDocenteListNewDocente = em.merge(oldInformacionDeSeguridadidIOfDocenteListNewDocente);
                    }
                }
            }
            for (Dinamizador dinamizadorListNewDinamizador : dinamizadorListNew) {
                if (!dinamizadorListOld.contains(dinamizadorListNewDinamizador)) {
                    InformacionDeSeguridad oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador = dinamizadorListNewDinamizador.getInformacionDeSeguridadidI();
                    dinamizadorListNewDinamizador.setInformacionDeSeguridadidI(informacionDeSeguridad);
                    dinamizadorListNewDinamizador = em.merge(dinamizadorListNewDinamizador);
                    if (oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador != null && !oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador.equals(informacionDeSeguridad)) {
                        oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador.getDinamizadorList().remove(dinamizadorListNewDinamizador);
                        oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador = em.merge(oldInformacionDeSeguridadidIOfDinamizadorListNewDinamizador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = informacionDeSeguridad.getIdI();
                if (findInformacionDeSeguridad(id) == null) {
                    throw new NonexistentEntityException("The informacionDeSeguridad with id " + id + " no longer exists.");
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
            InformacionDeSeguridad informacionDeSeguridad;
            try {
                informacionDeSeguridad = em.getReference(InformacionDeSeguridad.class, id);
                informacionDeSeguridad.getIdI();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informacionDeSeguridad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = informacionDeSeguridad.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionDeSeguridad (" + informacionDeSeguridad + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable informacionDeSeguridadidI field.");
            }
            List<Docente> docenteListOrphanCheck = informacionDeSeguridad.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionDeSeguridad (" + informacionDeSeguridad + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable informacionDeSeguridadidI field.");
            }
            List<Dinamizador> dinamizadorListOrphanCheck = informacionDeSeguridad.getDinamizadorList();
            for (Dinamizador dinamizadorListOrphanCheckDinamizador : dinamizadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionDeSeguridad (" + informacionDeSeguridad + ") cannot be destroyed since the Dinamizador " + dinamizadorListOrphanCheckDinamizador + " in its dinamizadorList field has a non-nullable informacionDeSeguridadidI field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(informacionDeSeguridad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InformacionDeSeguridad> findInformacionDeSeguridadEntities() {
        return findInformacionDeSeguridadEntities(true, -1, -1);
    }

    public List<InformacionDeSeguridad> findInformacionDeSeguridadEntities(int maxResults, int firstResult) {
        return findInformacionDeSeguridadEntities(false, maxResults, firstResult);
    }

    private List<InformacionDeSeguridad> findInformacionDeSeguridadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InformacionDeSeguridad.class));
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

    public InformacionDeSeguridad findInformacionDeSeguridad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InformacionDeSeguridad.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformacionDeSeguridadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InformacionDeSeguridad> rt = cq.from(InformacionDeSeguridad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
