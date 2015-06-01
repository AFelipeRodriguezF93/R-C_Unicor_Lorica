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
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Universidad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author AndresFelipe
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sede sede) {
        if (sede.getEstudianteList() == null) {
            sede.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (sede.getDinamizadorList() == null) {
            sede.setDinamizadorList(new ArrayList<Dinamizador>());
        }
        if (sede.getSalonList() == null) {
            sede.setSalonList(new ArrayList<Salon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad universidadid = sede.getUniversidadid();
            if (universidadid != null) {
                universidadid = em.getReference(universidadid.getClass(), universidadid.getId());
                sede.setUniversidadid(universidadid);
            }
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : sede.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiante());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            sede.setEstudianteList(attachedEstudianteList);
            List<Dinamizador> attachedDinamizadorList = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListDinamizadorToAttach : sede.getDinamizadorList()) {
                dinamizadorListDinamizadorToAttach = em.getReference(dinamizadorListDinamizadorToAttach.getClass(), dinamizadorListDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorList.add(dinamizadorListDinamizadorToAttach);
            }
            sede.setDinamizadorList(attachedDinamizadorList);
            List<Salon> attachedSalonList = new ArrayList<Salon>();
            for (Salon salonListSalonToAttach : sede.getSalonList()) {
                salonListSalonToAttach = em.getReference(salonListSalonToAttach.getClass(), salonListSalonToAttach.getId());
                attachedSalonList.add(salonListSalonToAttach);
            }
            sede.setSalonList(attachedSalonList);
            em.persist(sede);
            if (universidadid != null) {
                universidadid.getSedeList().add(sede);
                universidadid = em.merge(universidadid);
            }
            for (Estudiante estudianteListEstudiante : sede.getEstudianteList()) {
                Sede oldSedeidOfEstudianteListEstudiante = estudianteListEstudiante.getSedeid();
                estudianteListEstudiante.setSedeid(sede);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldSedeidOfEstudianteListEstudiante != null) {
                    oldSedeidOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldSedeidOfEstudianteListEstudiante = em.merge(oldSedeidOfEstudianteListEstudiante);
                }
            }
            for (Dinamizador dinamizadorListDinamizador : sede.getDinamizadorList()) {
                Sede oldSedeidOfDinamizadorListDinamizador = dinamizadorListDinamizador.getSedeid();
                dinamizadorListDinamizador.setSedeid(sede);
                dinamizadorListDinamizador = em.merge(dinamizadorListDinamizador);
                if (oldSedeidOfDinamizadorListDinamizador != null) {
                    oldSedeidOfDinamizadorListDinamizador.getDinamizadorList().remove(dinamizadorListDinamizador);
                    oldSedeidOfDinamizadorListDinamizador = em.merge(oldSedeidOfDinamizadorListDinamizador);
                }
            }
            for (Salon salonListSalon : sede.getSalonList()) {
                Sede oldSedeidOfSalonListSalon = salonListSalon.getSedeid();
                salonListSalon.setSedeid(sede);
                salonListSalon = em.merge(salonListSalon);
                if (oldSedeidOfSalonListSalon != null) {
                    oldSedeidOfSalonListSalon.getSalonList().remove(salonListSalon);
                    oldSedeidOfSalonListSalon = em.merge(oldSedeidOfSalonListSalon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getId());
            Universidad universidadidOld = persistentSede.getUniversidadid();
            Universidad universidadidNew = sede.getUniversidadid();
            List<Estudiante> estudianteListOld = persistentSede.getEstudianteList();
            List<Estudiante> estudianteListNew = sede.getEstudianteList();
            List<Dinamizador> dinamizadorListOld = persistentSede.getDinamizadorList();
            List<Dinamizador> dinamizadorListNew = sede.getDinamizadorList();
            List<Salon> salonListOld = persistentSede.getSalonList();
            List<Salon> salonListNew = sede.getSalonList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its sedeid field is not nullable.");
                }
            }
            for (Dinamizador dinamizadorListOldDinamizador : dinamizadorListOld) {
                if (!dinamizadorListNew.contains(dinamizadorListOldDinamizador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dinamizador " + dinamizadorListOldDinamizador + " since its sedeid field is not nullable.");
                }
            }
            for (Salon salonListOldSalon : salonListOld) {
                if (!salonListNew.contains(salonListOldSalon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Salon " + salonListOldSalon + " since its sedeid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (universidadidNew != null) {
                universidadidNew = em.getReference(universidadidNew.getClass(), universidadidNew.getId());
                sede.setUniversidadid(universidadidNew);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiante());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            sede.setEstudianteList(estudianteListNew);
            List<Dinamizador> attachedDinamizadorListNew = new ArrayList<Dinamizador>();
            for (Dinamizador dinamizadorListNewDinamizadorToAttach : dinamizadorListNew) {
                dinamizadorListNewDinamizadorToAttach = em.getReference(dinamizadorListNewDinamizadorToAttach.getClass(), dinamizadorListNewDinamizadorToAttach.getIdDinamizador());
                attachedDinamizadorListNew.add(dinamizadorListNewDinamizadorToAttach);
            }
            dinamizadorListNew = attachedDinamizadorListNew;
            sede.setDinamizadorList(dinamizadorListNew);
            List<Salon> attachedSalonListNew = new ArrayList<Salon>();
            for (Salon salonListNewSalonToAttach : salonListNew) {
                salonListNewSalonToAttach = em.getReference(salonListNewSalonToAttach.getClass(), salonListNewSalonToAttach.getId());
                attachedSalonListNew.add(salonListNewSalonToAttach);
            }
            salonListNew = attachedSalonListNew;
            sede.setSalonList(salonListNew);
            sede = em.merge(sede);
            if (universidadidOld != null && !universidadidOld.equals(universidadidNew)) {
                universidadidOld.getSedeList().remove(sede);
                universidadidOld = em.merge(universidadidOld);
            }
            if (universidadidNew != null && !universidadidNew.equals(universidadidOld)) {
                universidadidNew.getSedeList().add(sede);
                universidadidNew = em.merge(universidadidNew);
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Sede oldSedeidOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getSedeid();
                    estudianteListNewEstudiante.setSedeid(sede);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldSedeidOfEstudianteListNewEstudiante != null && !oldSedeidOfEstudianteListNewEstudiante.equals(sede)) {
                        oldSedeidOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldSedeidOfEstudianteListNewEstudiante = em.merge(oldSedeidOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Dinamizador dinamizadorListNewDinamizador : dinamizadorListNew) {
                if (!dinamizadorListOld.contains(dinamizadorListNewDinamizador)) {
                    Sede oldSedeidOfDinamizadorListNewDinamizador = dinamizadorListNewDinamizador.getSedeid();
                    dinamizadorListNewDinamizador.setSedeid(sede);
                    dinamizadorListNewDinamizador = em.merge(dinamizadorListNewDinamizador);
                    if (oldSedeidOfDinamizadorListNewDinamizador != null && !oldSedeidOfDinamizadorListNewDinamizador.equals(sede)) {
                        oldSedeidOfDinamizadorListNewDinamizador.getDinamizadorList().remove(dinamizadorListNewDinamizador);
                        oldSedeidOfDinamizadorListNewDinamizador = em.merge(oldSedeidOfDinamizadorListNewDinamizador);
                    }
                }
            }
            for (Salon salonListNewSalon : salonListNew) {
                if (!salonListOld.contains(salonListNewSalon)) {
                    Sede oldSedeidOfSalonListNewSalon = salonListNewSalon.getSedeid();
                    salonListNewSalon.setSedeid(sede);
                    salonListNewSalon = em.merge(salonListNewSalon);
                    if (oldSedeidOfSalonListNewSalon != null && !oldSedeidOfSalonListNewSalon.equals(sede)) {
                        oldSedeidOfSalonListNewSalon.getSalonList().remove(salonListNewSalon);
                        oldSedeidOfSalonListNewSalon = em.merge(oldSedeidOfSalonListNewSalon);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getId();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = sede.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable sedeid field.");
            }
            List<Dinamizador> dinamizadorListOrphanCheck = sede.getDinamizadorList();
            for (Dinamizador dinamizadorListOrphanCheckDinamizador : dinamizadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Dinamizador " + dinamizadorListOrphanCheckDinamizador + " in its dinamizadorList field has a non-nullable sedeid field.");
            }
            List<Salon> salonListOrphanCheck = sede.getSalonList();
            for (Salon salonListOrphanCheckSalon : salonListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Salon " + salonListOrphanCheckSalon + " in its salonList field has a non-nullable sedeid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Universidad universidadid = sede.getUniversidadid();
            if (universidadid != null) {
                universidadid.getSedeList().remove(sede);
                universidadid = em.merge(universidadid);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
