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
import java.util.ArrayList;
import java.util.List;
import RC_Unicor_Lorica_Entity.Facultad;
import RC_Unicor_Lorica_Entity.Universidad;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class UniversidadJpaController implements Serializable {

    public UniversidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Universidad universidad) {
        if (universidad.getSedeList() == null) {
            universidad.setSedeList(new ArrayList<Sede>());
        }
        if (universidad.getFacultadList() == null) {
            universidad.setFacultadList(new ArrayList<Facultad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sede> attachedSedeList = new ArrayList<Sede>();
            for (Sede sedeListSedeToAttach : universidad.getSedeList()) {
                sedeListSedeToAttach = em.getReference(sedeListSedeToAttach.getClass(), sedeListSedeToAttach.getId());
                attachedSedeList.add(sedeListSedeToAttach);
            }
            universidad.setSedeList(attachedSedeList);
            List<Facultad> attachedFacultadList = new ArrayList<Facultad>();
            for (Facultad facultadListFacultadToAttach : universidad.getFacultadList()) {
                facultadListFacultadToAttach = em.getReference(facultadListFacultadToAttach.getClass(), facultadListFacultadToAttach.getId());
                attachedFacultadList.add(facultadListFacultadToAttach);
            }
            universidad.setFacultadList(attachedFacultadList);
            em.persist(universidad);
            for (Sede sedeListSede : universidad.getSedeList()) {
                Universidad oldUniversidadidOfSedeListSede = sedeListSede.getUniversidadid();
                sedeListSede.setUniversidadid(universidad);
                sedeListSede = em.merge(sedeListSede);
                if (oldUniversidadidOfSedeListSede != null) {
                    oldUniversidadidOfSedeListSede.getSedeList().remove(sedeListSede);
                    oldUniversidadidOfSedeListSede = em.merge(oldUniversidadidOfSedeListSede);
                }
            }
            for (Facultad facultadListFacultad : universidad.getFacultadList()) {
                Universidad oldUniversidadidOfFacultadListFacultad = facultadListFacultad.getUniversidadid();
                facultadListFacultad.setUniversidadid(universidad);
                facultadListFacultad = em.merge(facultadListFacultad);
                if (oldUniversidadidOfFacultadListFacultad != null) {
                    oldUniversidadidOfFacultadListFacultad.getFacultadList().remove(facultadListFacultad);
                    oldUniversidadidOfFacultadListFacultad = em.merge(oldUniversidadidOfFacultadListFacultad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Universidad universidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad persistentUniversidad = em.find(Universidad.class, universidad.getId());
            List<Sede> sedeListOld = persistentUniversidad.getSedeList();
            List<Sede> sedeListNew = universidad.getSedeList();
            List<Facultad> facultadListOld = persistentUniversidad.getFacultadList();
            List<Facultad> facultadListNew = universidad.getFacultadList();
            List<String> illegalOrphanMessages = null;
            for (Sede sedeListOldSede : sedeListOld) {
                if (!sedeListNew.contains(sedeListOldSede)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sede " + sedeListOldSede + " since its universidadid field is not nullable.");
                }
            }
            for (Facultad facultadListOldFacultad : facultadListOld) {
                if (!facultadListNew.contains(facultadListOldFacultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facultad " + facultadListOldFacultad + " since its universidadid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Sede> attachedSedeListNew = new ArrayList<Sede>();
            for (Sede sedeListNewSedeToAttach : sedeListNew) {
                sedeListNewSedeToAttach = em.getReference(sedeListNewSedeToAttach.getClass(), sedeListNewSedeToAttach.getId());
                attachedSedeListNew.add(sedeListNewSedeToAttach);
            }
            sedeListNew = attachedSedeListNew;
            universidad.setSedeList(sedeListNew);
            List<Facultad> attachedFacultadListNew = new ArrayList<Facultad>();
            for (Facultad facultadListNewFacultadToAttach : facultadListNew) {
                facultadListNewFacultadToAttach = em.getReference(facultadListNewFacultadToAttach.getClass(), facultadListNewFacultadToAttach.getId());
                attachedFacultadListNew.add(facultadListNewFacultadToAttach);
            }
            facultadListNew = attachedFacultadListNew;
            universidad.setFacultadList(facultadListNew);
            universidad = em.merge(universidad);
            for (Sede sedeListNewSede : sedeListNew) {
                if (!sedeListOld.contains(sedeListNewSede)) {
                    Universidad oldUniversidadidOfSedeListNewSede = sedeListNewSede.getUniversidadid();
                    sedeListNewSede.setUniversidadid(universidad);
                    sedeListNewSede = em.merge(sedeListNewSede);
                    if (oldUniversidadidOfSedeListNewSede != null && !oldUniversidadidOfSedeListNewSede.equals(universidad)) {
                        oldUniversidadidOfSedeListNewSede.getSedeList().remove(sedeListNewSede);
                        oldUniversidadidOfSedeListNewSede = em.merge(oldUniversidadidOfSedeListNewSede);
                    }
                }
            }
            for (Facultad facultadListNewFacultad : facultadListNew) {
                if (!facultadListOld.contains(facultadListNewFacultad)) {
                    Universidad oldUniversidadidOfFacultadListNewFacultad = facultadListNewFacultad.getUniversidadid();
                    facultadListNewFacultad.setUniversidadid(universidad);
                    facultadListNewFacultad = em.merge(facultadListNewFacultad);
                    if (oldUniversidadidOfFacultadListNewFacultad != null && !oldUniversidadidOfFacultadListNewFacultad.equals(universidad)) {
                        oldUniversidadidOfFacultadListNewFacultad.getFacultadList().remove(facultadListNewFacultad);
                        oldUniversidadidOfFacultadListNewFacultad = em.merge(oldUniversidadidOfFacultadListNewFacultad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = universidad.getId();
                if (findUniversidad(id) == null) {
                    throw new NonexistentEntityException("The universidad with id " + id + " no longer exists.");
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
            Universidad universidad;
            try {
                universidad = em.getReference(Universidad.class, id);
                universidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The universidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Sede> sedeListOrphanCheck = universidad.getSedeList();
            for (Sede sedeListOrphanCheckSede : sedeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Universidad (" + universidad + ") cannot be destroyed since the Sede " + sedeListOrphanCheckSede + " in its sedeList field has a non-nullable universidadid field.");
            }
            List<Facultad> facultadListOrphanCheck = universidad.getFacultadList();
            for (Facultad facultadListOrphanCheckFacultad : facultadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Universidad (" + universidad + ") cannot be destroyed since the Facultad " + facultadListOrphanCheckFacultad + " in its facultadList field has a non-nullable universidadid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(universidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Universidad> findUniversidadEntities() {
        return findUniversidadEntities(true, -1, -1);
    }

    public List<Universidad> findUniversidadEntities(int maxResults, int firstResult) {
        return findUniversidadEntities(false, maxResults, firstResult);
    }

    private List<Universidad> findUniversidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Universidad.class));
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

    public Universidad findUniversidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Universidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniversidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Universidad> rt = cq.from(Universidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
