package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMoviesCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
         public String[] getActors(String name){
        EntityManager em = emf.createEntityManager();
        try{
            String[] actors = (String[])em.createQuery("SELECT r.actors FROM Movie r where r.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            
            return actors;
        }finally{  
            em.close();
        }
        
    }
         
          public int getYear(String name){
        EntityManager em = emf.createEntityManager();
        try{
            int actors = (int) em.createQuery("SELECT r.year FROM Movie r where r.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            
            return actors;
        }finally{  
            em.close();
        }
        
    }

}
