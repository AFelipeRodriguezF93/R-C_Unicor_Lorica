/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Controlador;

import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.IllegalOrphanException;
import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.NonexistentEntityException;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Facultad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad;
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
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) {
        if (docente.getMateriaList() == null) {
            docente.setMateriaList(new ArrayList<Materia>());
        }
        if (docente.getRegistroAsistenciaDocenteList() == null) {
            docente.setRegistroAsistenciaDocenteList(new ArrayList<RegistroAsistenciaDocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultadid = docente.getFacultadid();
            if (facultadid != null) {
                facultadid = em.getReference(facultadid.getClass(), facultadid.getId());
                docente.setFacultadid(facultadid);
            }
            InformacionBasica informacionBasicaId = docente.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId = em.getReference(informacionBasicaId.getClass(), informacionBasicaId.getId());
                docente.setInformacionBasicaId(informacionBasicaId);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = docente.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI = em.getReference(informacionDeSeguridadidI.getClass(), informacionDeSeguridadidI.getIdI());
                docente.setInformacionDeSeguridadidI(informacionDeSeguridadidI);
            }
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : docente.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getId());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            docente.setMateriaList(attachedMateriaList);
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteList = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach : docente.getRegistroAsistenciaDocenteList()) {
                registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteList.add(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach);
            }
            docente.setRegistroAsistenciaDocenteList(attachedRegistroAsistenciaDocenteList);
            em.persist(docente);
            if (facultadid != null) {
                facultadid.getDocenteList().add(docente);
                facultadid = em.merge(facultadid);
            }
            if (informacionBasicaId != null) {
                informacionBasicaId.getDocenteList().add(docente);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getDocenteList().add(docente);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            for (Materia materiaListMateria : docente.getMateriaList()) {
                materiaListMateria.getDocenteList().add(docente);
                materiaListMateria = em.merge(materiaListMateria);
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocente : docente.getRegistroAsistenciaDocenteList()) {
                Docente oldDocenteidDocenteOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = registroAsistenciaDocenteListRegistroAsistenciaDocente.getDocenteidDocente();
                registroAsistenciaDocenteListRegistroAsistenciaDocente.setDocenteidDocente(docente);
                registroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                if (oldDocenteidDocenteOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente != null) {
                    oldDocenteidDocenteOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                    oldDocenteidDocenteOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(oldDocenteidDocenteOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getIdDocente());
            Facultad facultadidOld = persistentDocente.getFacultadid();
            Facultad facultadidNew = docente.getFacultadid();
            InformacionBasica informacionBasicaIdOld = persistentDocente.getInformacionBasicaId();
            InformacionBasica informacionBasicaIdNew = docente.getInformacionBasicaId();
            InformacionDeSeguridad informacionDeSeguridadidIOld = persistentDocente.getInformacionDeSeguridadidI();
            InformacionDeSeguridad informacionDeSeguridadidINew = docente.getInformacionDeSeguridadidI();
            List<Materia> materiaListOld = persistentDocente.getMateriaList();
            List<Materia> materiaListNew = docente.getMateriaList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOld = persistentDocente.getRegistroAsistenciaDocenteList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListNew = docente.getRegistroAsistenciaDocenteList();
            List<String> illegalOrphanMessages = null;
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOldRegistroAsistenciaDocente : registroAsistenciaDocenteListOld) {
                if (!registroAsistenciaDocenteListNew.contains(registroAsistenciaDocenteListOldRegistroAsistenciaDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroAsistenciaDocente " + registroAsistenciaDocenteListOldRegistroAsistenciaDocente + " since its docenteidDocente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facultadidNew != null) {
                facultadidNew = em.getReference(facultadidNew.getClass(), facultadidNew.getId());
                docente.setFacultadid(facultadidNew);
            }
            if (informacionBasicaIdNew != null) {
                informacionBasicaIdNew = em.getReference(informacionBasicaIdNew.getClass(), informacionBasicaIdNew.getId());
                docente.setInformacionBasicaId(informacionBasicaIdNew);
            }
            if (informacionDeSeguridadidINew != null) {
                informacionDeSeguridadidINew = em.getReference(informacionDeSeguridadidINew.getClass(), informacionDeSeguridadidINew.getIdI());
                docente.setInformacionDeSeguridadidI(informacionDeSeguridadidINew);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getId());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            docente.setMateriaList(materiaListNew);
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteListNew = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach : registroAsistenciaDocenteListNew) {
                registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteListNew.add(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach);
            }
            registroAsistenciaDocenteListNew = attachedRegistroAsistenciaDocenteListNew;
            docente.setRegistroAsistenciaDocenteList(registroAsistenciaDocenteListNew);
            docente = em.merge(docente);
            if (facultadidOld != null && !facultadidOld.equals(facultadidNew)) {
                facultadidOld.getDocenteList().remove(docente);
                facultadidOld = em.merge(facultadidOld);
            }
            if (facultadidNew != null && !facultadidNew.equals(facultadidOld)) {
                facultadidNew.getDocenteList().add(docente);
                facultadidNew = em.merge(facultadidNew);
            }
            if (informacionBasicaIdOld != null && !informacionBasicaIdOld.equals(informacionBasicaIdNew)) {
                informacionBasicaIdOld.getDocenteList().remove(docente);
                informacionBasicaIdOld = em.merge(informacionBasicaIdOld);
            }
            if (informacionBasicaIdNew != null && !informacionBasicaIdNew.equals(informacionBasicaIdOld)) {
                informacionBasicaIdNew.getDocenteList().add(docente);
                informacionBasicaIdNew = em.merge(informacionBasicaIdNew);
            }
            if (informacionDeSeguridadidIOld != null && !informacionDeSeguridadidIOld.equals(informacionDeSeguridadidINew)) {
                informacionDeSeguridadidIOld.getDocenteList().remove(docente);
                informacionDeSeguridadidIOld = em.merge(informacionDeSeguridadidIOld);
            }
            if (informacionDeSeguridadidINew != null && !informacionDeSeguridadidINew.equals(informacionDeSeguridadidIOld)) {
                informacionDeSeguridadidINew.getDocenteList().add(docente);
                informacionDeSeguridadidINew = em.merge(informacionDeSeguridadidINew);
            }
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    materiaListOldMateria.getDocenteList().remove(docente);
                    materiaListOldMateria = em.merge(materiaListOldMateria);
                }
            }
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    materiaListNewMateria.getDocenteList().add(docente);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                }
            }
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocente : registroAsistenciaDocenteListNew) {
                if (!registroAsistenciaDocenteListOld.contains(registroAsistenciaDocenteListNewRegistroAsistenciaDocente)) {
                    Docente oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = registroAsistenciaDocenteListNewRegistroAsistenciaDocente.getDocenteidDocente();
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente.setDocenteidDocente(docente);
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    if (oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente != null && !oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.equals(docente)) {
                        oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                        oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(oldDocenteidDocenteOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getIdDocente();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getIdDocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOrphanCheck = docente.getRegistroAsistenciaDocenteList();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente : registroAsistenciaDocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the RegistroAsistenciaDocente " + registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente + " in its registroAsistenciaDocenteList field has a non-nullable docenteidDocente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facultadid = docente.getFacultadid();
            if (facultadid != null) {
                facultadid.getDocenteList().remove(docente);
                facultadid = em.merge(facultadid);
            }
            InformacionBasica informacionBasicaId = docente.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId.getDocenteList().remove(docente);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = docente.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getDocenteList().remove(docente);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            List<Materia> materiaList = docente.getMateriaList();
            for (Materia materiaListMateria : materiaList) {
                materiaListMateria.getDocenteList().remove(docente);
                materiaListMateria = em.merge(materiaListMateria);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
