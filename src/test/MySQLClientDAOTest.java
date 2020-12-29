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

import SQL.MySQLClientDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import metier.MClient;

public class MySQLClientDAOTest {

    private MClient c;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
        c = new MClient(1, "jean", "caillou", "mail", "toto", 15, "rue", 57500, "Metz", "France");
        MySQLClientDAO.getInstance().create(c);
    }

    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
        MySQLClientDAO.getInstance().delete(c);
    }

    @Test
    public void testSelectExiste() throws Exception {




        int id=c.getIdClient();

        MClient cBdd=MySQLClientDAO.getInstance().getById(id);
        assertNotNull(cBdd);
    }
    @Test
    public void testGetbyid() throws Exception {


        try {
            DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(c.getIdClient());}
        catch(Exception e) {
            fail("erreur de getbyid");
        }


    }
    @Test
    public void testCreate() throws Exception {
        MClient c2 = new MClient(1, "jean", "caillou", "mail", "toto", 15, "rue", 57500, "Metz", "France");
        try {

            MySQLClientDAO.getInstance().create(c2);

        }catch(Exception e) {
            fail("Erreur lors de l'insertion");
        }

        assertEquals(c.getNom(),"jean");
        assertEquals(c.getPrenom(),"caillou");
        assertEquals(c.getIdentifiant(),"mail");
        assertEquals(c.getMdp(),"toto");
        assertEquals(c.getAdrNumero(),15);
        assertEquals(c.getAdrVoie(),"rue");
        assertEquals(c.getAdrPostale(),57500);
        assertEquals(c.getAdrVille(),"Metz");
        assertEquals(c.getAdrPays(),"France");


        MySQLClientDAO.getInstance().delete(c2);

    }
    @Test
    public void testDelete() throws Exception {


        MClient c2 =new MClient(1, "jean", "caillou", "mail", "toto", 15, "rue", 57500, "Metz", "France");
        MySQLClientDAO.getInstance().create(c2);

        int id =c2.getIdClient();
        assertTrue(MySQLClientDAO.getInstance().delete(c2));

        MClient pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(id);
        assertNull(pr);

        assertFalse(MySQLClientDAO.getInstance().delete(pr));





    }

    @Test
    public void testUpdate() throws Exception {


        MClient p2= new MClient(c.getIdClient(),"jean", "caillou", "mail", "toto", 15, "rue", 57500, "Metz", "France");
        DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().update(p2);
        MClient p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(p2.getIdClient());

        assertEquals("jean", p3.getNom());
        assertEquals("caillou", p3.getPrenom());
        assertEquals("mail", p3.getIdentifiant());
        assertEquals("toto", p3.getMdp());
        assertEquals(15, p3.getAdrNumero());
        assertEquals("rue", p3.getAdrVoie());
        assertEquals(57500, p3.getAdrPostale());
        assertEquals("Metz", p3.getAdrVille());
        assertEquals("France", p3.getAdrPays());
    }
}
