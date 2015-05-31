/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RC_Unicor_Lorica_Ope;

import RC_Unicor_Lorica_Entity.Facultad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndresFelipe
 */
public class Facultad_Ope {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("R-C_Unicor_LoricaPU");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public void Agregar(Facultad facultad){
        entityManager.getTransaction().begin();
        entityManager.persist(facultad);
        entityManager.getTransaction().commit();    
    }       
    
    public List<Facultad> Listar(){
        Query query = entityManager.createNamedQuery("Facultad.findAll");
        return query.getResultList();
    }
        
}
