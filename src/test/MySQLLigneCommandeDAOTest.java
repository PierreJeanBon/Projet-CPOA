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

import SQL.MySQLLigneCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import metier.MLigneCommande;

public class MySQLLigneCommandeDAOTest {

    private MLigneCommande lc;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
        lc = new MLigneCommande(1, 2, 5, 41.5);
        MySQLLigneCommandeDAO.getInstance().create(lc);
    }

    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
        MySQLLigneCommandeDAO.getInstance().delete(lc);
    }

    @Test
    public void testSelectExiste() throws Exception {




        int id=lc.getIdCommande();

        MLigneCommande cBdd=MySQLLigneCommandeDAO.getInstance().getById(id);
        assertNotNull(cBdd);
    }
    @Test
    public void testGetbyid() throws Exception {


        try {
            DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(lc.getIdCommande());}
        catch(Exception e) {
            fail("erreur de getbyid");
        }


    }
    @SuppressWarnings("deprecation")
    @Test
    public void testCreate() throws Exception {
        MLigneCommande c2 = new MLigneCommande(1, 2, 5, 41.5);
        try {

            MySQLLigneCommandeDAO.getInstance().create(c2);

        }catch(Exception e) {
            fail("Erreur lors de l'insertion");
        }

        assertEquals(lc.getIdProduit(),2);
        assertEquals(lc.getQuantite(),5);
        assertEquals(lc.getTarifUnitaire(),41.5);


        MySQLLigneCommandeDAO.getInstance().delete(c2);

    }
    @Test
    public void testDelete() throws Exception {


        MLigneCommande c2 = new MLigneCommande(1, 2, 5, 41.5);
        MySQLLigneCommandeDAO.getInstance().create(c2);

        int id =c2.getIdCommande();
        assertTrue(MySQLLigneCommandeDAO.getInstance().delete(c2));

        MLigneCommande pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO().getById(id);
        assertNull(pr);

        assertFalse(MySQLLigneCommandeDAO.getInstance().delete(pr));





    }

    @SuppressWarnings("deprecation")
    @Test
    public void testUpdate() throws Exception {


        MLigneCommande p2= new MLigneCommande(lc.getIdCommande(),2, 5, 41.5);
        DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO().update(p2);
        MLigneCommande p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO().getById(p2.getIdCommande());

        assertEquals(2, p3.getIdProduit());
        assertEquals(5, p3.getQuantite());
        assertEquals(41.5, p3.getTarifUnitaire());

    }
}
