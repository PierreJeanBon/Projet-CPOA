package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import listeMemoire.ListeMemoireProduitDAO;
import metier.MProduit;
import SQL.MySQLProduitDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLProduitDAOTest {
    private MProduit p;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
        p=new MProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
        MySQLProduitDAO.getInstance().create(p);
    }

    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
        MySQLProduitDAO.getInstance().delete(p);
    }

    @Test
    public void testSelectExiste() throws Exception {




        int id=p.getIdProduit();

        MProduit cBdd=MySQLProduitDAO.getInstance().getById(id);
        assertNotNull(cBdd);
    }
    @Test
    public void testGetbyid() throws Exception {


        try {
            DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p.getIdProduit());}
        catch(Exception e) {
            fail("erreur de getbyid");
        }


    }
    @Test
    public void testCreate() throws Exception {
        MProduit c2 = new MProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
        try {

            MySQLProduitDAO.getInstance().create(c2);

        }catch(Exception e) {
            fail("Erreur lors de l'insertion");
        }

        assertEquals(p.getNom(),"aaa");
        assertEquals(p.getDescription(),"222xx");
        assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
        assertEquals(p.getVisuel(),"xxx.png");
        assertEquals(p.getIdCategorie(),10);


        MySQLProduitDAO.getInstance().delete(c2);

    }
    @Test
    public void testDelete() throws Exception {


        MProduit p2 =new MProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
        MySQLProduitDAO.getInstance().create(p2);

        int idd =p2.getIdProduit();
        assertTrue(MySQLProduitDAO.getInstance().delete(p2));

        MProduit pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(idd);
        assertNull(pr);

        assertFalse(MySQLProduitDAO.getInstance().delete(pr));





    }

    @Test
    public void testUpdate() throws Exception {


        MProduit p2= new MProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",2);
        DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().update(p2);
        MProduit p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p2.getIdProduit());

        assertEquals("bbb", p3.getNom());
        assertEquals("333zz", p3.getDescription());
        assertEquals((float)1,0, p3.getTarif());
        assertEquals("yyy.png", p3.getVisuel());
        assertEquals(2, p3.getIdCategorie());
    }

}
