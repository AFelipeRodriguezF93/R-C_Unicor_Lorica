/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Controlador;

import Unicordoba.Registro_Control.Base_de_Datos.Controlador.exceptions.NonexistentEntityException;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) {
        if (estudiante.getMateriaList() == null) {
            estudiante.setMateriaList(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sedeid = estudiante.getSedeid();
            if (sedeid != null) {
                sedeid = em.getReference(sedeid.getClass(), sedeid.getId());
                estudiante.setSedeid(sedeid);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = estudiante.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI = em.getReference(informacionDeSeguridadidI.getClass(), informacionDeSeguridadidI.getIdI());
                estudiante.setInformacionDeSeguridadidI(informacionDeSeguridadidI);
            }
            InformacionBasica informacionBasicaId = estudiante.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId = em.getReference(informacionBasicaId.getClass(), informacionBasicaId.getId());
                estudiante.setInformacionBasicaId(informacionBasicaId);
            }
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : estudiante.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getId());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            estudiante.setMateriaList(attachedMateriaList);
            em.persist(estudiante);
            if (sedeid != null) {
                sedeid.getEstudianteList().add(estudiante);
                sedeid = em.merge(sedeid);
            }
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getEstudianteList().add(estudiante);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            if (informacionBasicaId != null) {
                informacionBasicaId.getEstudianteList().add(estudiante);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            for (Materia materiaListMateria : estudiante.getMateriaList()) {
                materiaListMateria.getEstudianteList().add(estudiante);
                materiaListMateria = em.merge(materiaListMateria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getIdEstudiante());
            Sede sedeidOld = persistentEstudiante.getSedeid();
            Sede sedeidNew = estudiante.getSedeid();
            InformacionDeSeguridad informacionDeSeguridadidIOld = persistentEstudiante.getInformacionDeSeguridadidI();
            InformacionDeSeguridad informacionDeSeguridadidINew = estudiante.getInformacionDeSeguridadidI();
            InformacionBasica informacionBasicaIdOld = persistentEstudiante.getInformacionBasicaId();
            InformacionBasica informacionBasicaIdNew = estudiante.getInformacionBasicaId();
            List<Materia> materiaListOld = persistentEstudiante.getMateriaList();
            List<Materia> materiaListNew = estudiante.getMateriaList();
            if (sedeidNew != null) {
                sedeidNew = em.getReference(sedeidNew.getClass(), sedeidNew.getId());
                estudiante.setSedeid(sedeidNew);
            }
            if (informacionDeSeguridadidINew != null) {
                informacionDeSeguridadidINew = em.getReference(informacionDeSeguridadidINew.getClass(), informacionDeSeguridadidINew.getIdI());
                estudiante.setInformacionDeSeguridadidI(informacionDeSeguridadidINew);
            }
            if (informacionBasicaIdNew != null) {
                informacionBasicaIdNew = em.getReference(informacionBasicaIdNew.getClass(), informacionBasicaIdNew.getId());
                estudiante.setInformacionBasicaId(informacionBasicaIdNew);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getId());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            estudiante.setMateriaList(materiaListNew);
            estudiante = em.merge(estudiante);
            if (sedeidOld != null && !sedeidOld.equals(sedeidNew)) {
                sedeidOld.getEstudianteList().remove(estudiante);
                sedeidOld = em.merge(sedeidOld);
            }
            if (sedeidNew != null && !sedeidNew.equals(sedeidOld)) {
                sedeidNew.getEstudianteList().add(estudiante);
                sedeidNew = em.merge(sedeidNew);
            }
            if (informacionDeSeguridadidIOld != null && !informacionDeSeguridadidIOld.equals(informacionDeSeguridadidINew)) {
                informacionDeSeguridadidIOld.getEstudianteList().remove(estudiante);
                informacionDeSeguridadidIOld = em.merge(informacionDeSeguridadidIOld);
            }
            if (informacionDeSeguridadidINew != null && !informacionDeSeguridadidINew.equals(informacionDeSeguridadidIOld)) {
                informacionDeSeguridadidINew.getEstudianteList().add(estudiante);
                informacionDeSeguridadidINew = em.merge(informacionDeSeguridadidINew);
            }
            if (informacionBasicaIdOld != null && !informacionBasicaIdOld.equals(informacionBasicaIdNew)) {
                informacionBasicaIdOld.getEstudianteList().remove(estudiante);
                informacionBasicaIdOld = em.merge(informacionBasicaIdOld);
            }
            if (informacionBasicaIdNew != null && !informacionBasicaIdNew.equals(informacionBasicaIdOld)) {
                informacionBasicaIdNew.getEstudianteList().add(estudiante);
                informacionBasicaIdNew = em.merge(informacionBasicaIdNew);
            }
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    materiaListOldMateria.getEstudianteList().remove(estudiante);
                    materiaListOldMateria = em.merge(materiaListOldMateria);
                }
            }
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    materiaListNewMateria.getEstudianteList().add(estudiante);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getIdEstudiante();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getIdEstudiante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            Sede sedeid = estudiante.getSedeid();
            if (sedeid != null) {
                sedeid.getEstudianteList().remove(estudiante);
                sedeid = em.merge(sedeid);
            }
            InformacionDeSeguridad informacionDeSeguridadidI = estudiante.getInformacionDeSeguridadidI();
            if (informacionDeSeguridadidI != null) {
                informacionDeSeguridadidI.getEstudianteList().remove(estudiante);
                informacionDeSeguridadidI = em.merge(informacionDeSeguridadidI);
            }
            InformacionBasica informacionBasicaId = estudiante.getInformacionBasicaId();
            if (informacionBasicaId != null) {
                informacionBasicaId.getEstudianteList().remove(estudiante);
                informacionBasicaId = em.merge(informacionBasicaId);
            }
            List<Materia> materiaList = estudiante.getMateriaList();
            for (Materia materiaListMateria : materiaList) {
                materiaListMateria.getEstudianteList().remove(estudiante);
                materiaListMateria = em.merge(materiaListMateria);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
