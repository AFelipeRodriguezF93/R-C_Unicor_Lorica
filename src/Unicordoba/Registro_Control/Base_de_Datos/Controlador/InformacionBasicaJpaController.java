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
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class InformacionBasicaJpaController implements Serializable {

    public InformacionBasicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InformacionBasica informacionBasica) {
        if (informacionBasica.getEstudianteList() == null) {
            informacionBasica.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (informacionBasica.getDocenteList() == null) {
            informacionBasica.setDocenteList(new ArrayList<Docente>());
        }
        if (informacionBasica.getDinamizadorList() == null) {
            informacionBasica.setDinamizadorList(new ArrayList<Dinamizador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : informacionBasica.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiante());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            informacionBasica.setEstudianteList(attachedEstudianteList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : informacionBasica.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            informacionBasica.setDocenteList(attachedDocenteList);
            List<Dinamizador> attachedDinamizadorList = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListDinamizadorToAttach : informacionBasica.getDinamizadorList()) {
                dinamizadorListDinamizadorToAttach = em.getReference(dinamizadorListDinamizadorToAttach.getClass(), dinamizadorListDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorList.add(dinamizadorListDinamizadorToAttach);
            }
            informacionBasica.setDinamizadorList(attachedDinamizadorList);
            em.persist(informacionBasica);
            for (Estudiante estudianteListEstudiante : informacionBasica.getEstudianteList()) {
                InformacionBasica oldInformacionBasicaIdOfEstudianteListEstudiante = estudianteListEstudiante.getInformacionBasicaId();
                estudianteListEstudiante.setInformacionBasicaId(informacionBasica);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldInformacionBasicaIdOfEstudianteListEstudiante != null) {
                    oldInformacionBasicaIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldInformacionBasicaIdOfEstudianteListEstudiante = em.merge(oldInformacionBasicaIdOfEstudianteListEstudiante);
                }
            }
            for (Docente docenteListDocente : informacionBasica.getDocenteList()) {
                InformacionBasica oldInformacionBasicaIdOfDocenteListDocente = docenteListDocente.getInformacionBasicaId();
                docenteListDocente.setInformacionBasicaId(informacionBasica);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldInformacionBasicaIdOfDocenteListDocente != null) {
                    oldInformacionBasicaIdOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldInformacionBasicaIdOfDocenteListDocente = em.merge(oldInformacionBasicaIdOfDocenteListDocente);
                }
            }
            for (Dinamizador dinamizadorListDinamizador : informacionBasica.getDinamizadorList()) {
                InformacionBasica oldInformacionBasicaIdOfDinamizadorListDinamizador = dinamizadorListDinamizador.getInformacionBasicaId();
                dinamizadorListDinamizador.setInformacionBasicaId(informacionBasica);
                dinamizadorListDinamizador = em.merge(dinamizadorListDinamizador);
                if (oldInformacionBasicaIdOfDinamizadorListDinamizador != null) {
                    oldInformacionBasicaIdOfDinamizadorListDinamizador.getDinamizadorList().remove(dinamizadorListDinamizador);
                    oldInformacionBasicaIdOfDinamizadorListDinamizador = em.merge(oldInformacionBasicaIdOfDinamizadorListDinamizador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InformacionBasica informacionBasica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InformacionBasica persistentInformacionBasica = em.find(InformacionBasica.class, informacionBasica.getId());
            List<Estudiante> estudianteListOld = persistentInformacionBasica.getEstudianteList();
            List<Estudiante> estudianteListNew = informacionBasica.getEstudianteList();
            List<Docente> docenteListOld = persistentInformacionBasica.getDocenteList();
            List<Docente> docenteListNew = informacionBasica.getDocenteList();
            List<Dinamizador> dinamizadorListOld = persistentInformacionBasica.getDinamizadorList();
            List<Dinamizador> dinamizadorListNew = informacionBasica.getDinamizadorList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its informacionBasicaId field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its informacionBasicaId field is not nullable.");
                }
            }
            for (Dinamizador dinamizadorListOldDinamizador : dinamizadorListOld) {
                if (!dinamizadorListNew.contains(dinamizadorListOldDinamizador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dinamizador " + dinamizadorListOldDinamizador + " since its informacionBasicaId field is not nullable.");
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
            informacionBasica.setEstudianteList(estudianteListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            informacionBasica.setDocenteList(docenteListNew);
            List<Dinamizador> attachedDinamizadorListNew = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListNewDinamizadorToAttach : dinamizadorListNew) {
                dinamizadorListNewDinamizadorToAttach = em.getReference(dinamizadorListNewDinamizadorToAttach.getClass(), dinamizadorListNewDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorListNew.add(dinamizadorListNewDinamizadorToAttach);
            }
            dinamizadorListNew = attachedDinamizadorListNew;
            informacionBasica.setDinamizadorList(dinamizadorListNew);
            informacionBasica = em.merge(informacionBasica);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    InformacionBasica oldInformacionBasicaIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getInformacionBasicaId();
                    estudianteListNewEstudiante.setInformacionBasicaId(informacionBasica);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldInformacionBasicaIdOfEstudianteListNewEstudiante != null && !oldInformacionBasicaIdOfEstudianteListNewEstudiante.equals(informacionBasica)) {
                        oldInformacionBasicaIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldInformacionBasicaIdOfEstudianteListNewEstudiante = em.merge(oldInformacionBasicaIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    InformacionBasica oldInformacionBasicaIdOfDocenteListNewDocente = docenteListNewDocente.getInformacionBasicaId();
                    docenteListNewDocente.setInformacionBasicaId(informacionBasica);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldInformacionBasicaIdOfDocenteListNewDocente != null && !oldInformacionBasicaIdOfDocenteListNewDocente.equals(informacionBasica)) {
                        oldInformacionBasicaIdOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldInformacionBasicaIdOfDocenteListNewDocente = em.merge(oldInformacionBasicaIdOfDocenteListNewDocente);
                    }
                }
            }
            for (Dinamizador dinamizadorListNewDinamizador : dinamizadorListNew) {
                if (!dinamizadorListOld.contains(dinamizadorListNewDinamizador)) {
                    InformacionBasica oldInformacionBasicaIdOfDinamizadorListNewDinamizador = dinamizadorListNewDinamizador.getInformacionBasicaId();
                    dinamizadorListNewDinamizador.setInformacionBasicaId(informacionBasica);
                    dinamizadorListNewDinamizador = em.merge(dinamizadorListNewDinamizador);
                    if (oldInformacionBasicaIdOfDinamizadorListNewDinamizador != null && !oldInformacionBasicaIdOfDinamizadorListNewDinamizador.equals(informacionBasica)) {
                        oldInformacionBasicaIdOfDinamizadorListNewDinamizador.getDinamizadorList().remove(dinamizadorListNewDinamizador);
                        oldInformacionBasicaIdOfDinamizadorListNewDinamizador = em.merge(oldInformacionBasicaIdOfDinamizadorListNewDinamizador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = informacionBasica.getId();
                if (findInformacionBasica(id) == null) {
                    throw new NonexistentEntityException("The informacionBasica with id " + id + " no longer exists.");
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
            InformacionBasica informacionBasica;
            try {
                informacionBasica = em.getReference(InformacionBasica.class, id);
                informacionBasica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informacionBasica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = informacionBasica.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionBasica (" + informacionBasica + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable informacionBasicaId field.");
            }
            List<Docente> docenteListOrphanCheck = informacionBasica.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionBasica (" + informacionBasica + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable informacionBasicaId field.");
            }
            List<Dinamizador> dinamizadorListOrphanCheck = informacionBasica.getDinamizadorList();
            for (Dinamizador dinamizadorListOrphanCheckDinamizador : dinamizadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InformacionBasica (" + informacionBasica + ") cannot be destroyed since the Dinamizador " + dinamizadorListOrphanCheckDinamizador + " in its dinamizadorList field has a non-nullable informacionBasicaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(informacionBasica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InformacionBasica> findInformacionBasicaEntities() {
        return findInformacionBasicaEntities(true, -1, -1);
    }

    public List<InformacionBasica> findInformacionBasicaEntities(int maxResults, int firstResult) {
        return findInformacionBasicaEntities(false, maxResults, firstResult);
    }

    private List<InformacionBasica> findInformacionBasicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InformacionBasica.class));
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

    public InformacionBasica findInformacionBasica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InformacionBasica.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformacionBasicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InformacionBasica> rt = cq.from(InformacionBasica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
