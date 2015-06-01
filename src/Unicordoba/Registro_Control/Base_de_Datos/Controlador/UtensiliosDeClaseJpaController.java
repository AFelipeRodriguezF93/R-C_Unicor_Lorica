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
import Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.UtensiliosDeClase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class UtensiliosDeClaseJpaController implements Serializable {

    public UtensiliosDeClaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UtensiliosDeClase utensiliosDeClase) {
        if (utensiliosDeClase.getRegistroAsistenciaDocenteList() == null) {
            utensiliosDeClase.setRegistroAsistenciaDocenteList(new ArrayList<RegistroAsistenciaDocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteList = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach : utensiliosDeClase.getRegistroAsistenciaDocenteList()) {
                registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteList.add(registroAsistenciaDocenteListRegistroAsistenciaDocenteToAttach);
            }
            utensiliosDeClase.setRegistroAsistenciaDocenteList(attachedRegistroAsistenciaDocenteList);
            em.persist(utensiliosDeClase);
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListRegistroAsistenciaDocente : utensiliosDeClase.getRegistroAsistenciaDocenteList()) {
                UtensiliosDeClase oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = registroAsistenciaDocenteListRegistroAsistenciaDocente.getUtensiliosDeClaseid();
                registroAsistenciaDocenteListRegistroAsistenciaDocente.setUtensiliosDeClaseid(utensiliosDeClase);
                registroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                if (oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente != null) {
                    oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListRegistroAsistenciaDocente);
                    oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente = em.merge(oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListRegistroAsistenciaDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UtensiliosDeClase utensiliosDeClase) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UtensiliosDeClase persistentUtensiliosDeClase = em.find(UtensiliosDeClase.class, utensiliosDeClase.getId());
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOld = persistentUtensiliosDeClase.getRegistroAsistenciaDocenteList();
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListNew = utensiliosDeClase.getRegistroAsistenciaDocenteList();
            List<String> illegalOrphanMessages = null;
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOldRegistroAsistenciaDocente : registroAsistenciaDocenteListOld) {
                if (!registroAsistenciaDocenteListNew.contains(registroAsistenciaDocenteListOldRegistroAsistenciaDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroAsistenciaDocente " + registroAsistenciaDocenteListOldRegistroAsistenciaDocente + " since its utensiliosDeClaseid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<RegistroAsistenciaDocente> attachedRegistroAsistenciaDocenteListNew = new ArrayList<RegistroAsistenciaDocente>();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach : registroAsistenciaDocenteListNew) {
                registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach = em.getReference(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getClass(), registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach.getId());
                attachedRegistroAsistenciaDocenteListNew.add(registroAsistenciaDocenteListNewRegistroAsistenciaDocenteToAttach);
            }
            registroAsistenciaDocenteListNew = attachedRegistroAsistenciaDocenteListNew;
            utensiliosDeClase.setRegistroAsistenciaDocenteList(registroAsistenciaDocenteListNew);
            utensiliosDeClase = em.merge(utensiliosDeClase);
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListNewRegistroAsistenciaDocente : registroAsistenciaDocenteListNew) {
                if (!registroAsistenciaDocenteListOld.contains(registroAsistenciaDocenteListNewRegistroAsistenciaDocente)) {
                    UtensiliosDeClase oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = registroAsistenciaDocenteListNewRegistroAsistenciaDocente.getUtensiliosDeClaseid();
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente.setUtensiliosDeClaseid(utensiliosDeClase);
                    registroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    if (oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente != null && !oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.equals(utensiliosDeClase)) {
                        oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente.getRegistroAsistenciaDocenteList().remove(registroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                        oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente = em.merge(oldUtensiliosDeClaseidOfRegistroAsistenciaDocenteListNewRegistroAsistenciaDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = utensiliosDeClase.getId();
                if (findUtensiliosDeClase(id) == null) {
                    throw new NonexistentEntityException("The utensiliosDeClase with id " + id + " no longer exists.");
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
            UtensiliosDeClase utensiliosDeClase;
            try {
                utensiliosDeClase = em.getReference(UtensiliosDeClase.class, id);
                utensiliosDeClase.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utensiliosDeClase with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RegistroAsistenciaDocente> registroAsistenciaDocenteListOrphanCheck = utensiliosDeClase.getRegistroAsistenciaDocenteList();
            for (RegistroAsistenciaDocente registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente : registroAsistenciaDocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UtensiliosDeClase (" + utensiliosDeClase + ") cannot be destroyed since the RegistroAsistenciaDocente " + registroAsistenciaDocenteListOrphanCheckRegistroAsistenciaDocente + " in its registroAsistenciaDocenteList field has a non-nullable utensiliosDeClaseid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(utensiliosDeClase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UtensiliosDeClase> findUtensiliosDeClaseEntities() {
        return findUtensiliosDeClaseEntities(true, -1, -1);
    }

    public List<UtensiliosDeClase> findUtensiliosDeClaseEntities(int maxResults, int firstResult) {
        return findUtensiliosDeClaseEntities(false, maxResults, firstResult);
    }

    private List<UtensiliosDeClase> findUtensiliosDeClaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UtensiliosDeClase.class));
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

    public UtensiliosDeClase findUtensiliosDeClase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UtensiliosDeClase.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtensiliosDeClaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UtensiliosDeClase> rt = cq.from(UtensiliosDeClase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
