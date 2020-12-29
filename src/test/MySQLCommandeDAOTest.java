package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SQL.MySQLCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import metier.MCommande;

public class MySQLCommandeDAOTest {

    private MCommande c;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
        c = new MCommande(1, "2020-09-02", 1);
        MySQLCommandeDAO.getInstance().create(c);
    }

    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
        MySQLCommandeDAO.getInstance().delete(c);
    }

    @Test
    public void testSelectExiste() throws Exception {




        int id=c.getId();

        MCommande cBdd=MySQLCommandeDAO.getInstance().getById(id);
        assertNotNull(cBdd);
    }
    @Test
    public void testGetbyid() throws Exception {


        try {
            DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(c.getId());}
        catch(Exception e) {
            fail("erreur de getbyid");
        }


    }
    @Test
    public void testCreate() throws Exception {
        MCommande c2 = new  MCommande(1, "2020-09-02", 1);
        try {

            MySQLCommandeDAO.getInstance().create(c2);

        }catch(Exception e) {
            fail("Erreur lors de l'insertion");
        }

        assertEquals(c.getDateCommande(),"2020-09-02");
        assertEquals(c.getIdClient(),1);


        MySQLCommandeDAO.getInstance().delete(c2);

    }
    @Test
    public void testDelete() throws Exception {


        MCommande c2 =new MCommande(1, "2020-09-02", 1);
        MySQLCommandeDAO.getInstance().create(c2);

        int id =c2.getId();
        assertTrue(MySQLCommandeDAO.getInstance().delete(c2));

        MCommande pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAOO().getById(id);
        assertNull(pr);

        assertFalse(MySQLCommandeDAO.getInstance().delete(pr));





    }

    @Test
    public void testUpdate() throws Exception {


        MCommande p2= new MCommande(c.getId(),"2020-09-02",1);
        DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAOO().update(p2);
        MCommande p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAOO().getById(p2.getId());

        assertEquals("2020-09-02", p3.getDateCommande());
        assertEquals(1, p3.getIdClient());

    }
}
