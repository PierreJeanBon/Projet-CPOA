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

import SQL.MySQLCategorieDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import metier.MCategorie;

public class MySQLCategorieDAOTest {

    private MCategorie c;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
        c = new MCategorie(1, "Pulls", "lp.png");
        MySQLCategorieDAO.getInstance().create(c);
    }

    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
        MySQLCategorieDAO.getInstance().delete(c);
    }

    @Test
    public void testSelectExiste() throws Exception {




        int id=c.getId();

        MCategorie cBdd=MySQLCategorieDAO.getInstance().getById(id);
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
        MCategorie c2 = new MCategorie(1, "Pulls", "lp.png");
        try {

            MySQLCategorieDAO.getInstance().create(c2);

        }catch(Exception e) {
            fail("Erreur lors de l'insertion");
        }

        assertEquals(c.getTitre(),"Pulls");
        assertEquals(c.getVisuel(),"lp.png");


        MySQLCategorieDAO.getInstance().delete(c2);

    }
    @Test
    public void testDelete() throws Exception {


        MCategorie c2 =new MCategorie(1, "Pulls", "lp.png");
        MySQLCategorieDAO.getInstance().create(c2);

        int id =c2.getId();
        assertTrue(MySQLCategorieDAO.getInstance().delete(c2));

        MCategorie pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(id);
        assertNull(pr);

        assertFalse(MySQLCategorieDAO.getInstance().delete(pr));





    }

    @Test
    public void testUpdate() throws Exception {


        MCategorie p2= new MCategorie(c.getId(),"momo","mo.png");
        DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().update(p2);
        MCategorie p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(p2.getId());

        assertEquals("momo", p3.getTitre());
        assertEquals("mo.png", p3.getVisuel());

    }

}
