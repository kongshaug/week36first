package facades;

import utils.EMF_Creator;
import entities.Movie;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.glassfish.grizzly.http.util.HttpStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
           
            String[] actors = {"man", "lady","goat"};
            String[] actorstwo = {"cat", "monkey","zebra"};
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(1995, "Some txt", actors));
            em.persist(new Movie(444, "aaa", actorstwo));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testCountMovies() {
        assertEquals(2, facade.getMoviesCount(), "Expects two rows in the database");
    }
    @Test
    public void testGetActors() {
        assertEquals(3, facade.getActors("aaa").length);
    }
    
    @Test
    public void testGetYear() {
        assertEquals(444, facade.getYear("aaa"));
    }
    
      @Test
    public void testGetAll() {
        assertEquals(facade.getMoviesCount(), facade.getAll().size());
    }
    
//     @Test
// public void testCount() throws Exception {
//
//    when().
//            get("http://localhost:8080/jpareststarter/api/xxx/all").
//    then().
//            statusCode(200).
//            body("lotto.lottoId", equalTo(5),
//                 "lotto.winners.winnerId", hasItems(23, 54));
//
//
// }

}
