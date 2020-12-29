package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import listeMemoire.ListeMemoireClientDAO;
import metier.MClient;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class ListeMemoireClientDAOTest {

    private MClient c;

    @Before
    public void Setup() throws Exception {

        c =new MClient(9, "aaa", "bbb", "ccc", "ddd", 9, "eee", 9, "fff", "ggg");
        ListeMemoireClientDAO.getInstance().create(c);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSelectExiste() throws Exception {

        int id=c.getIdClient();

        MClient cLm=ListeMemoireClientDAO.getInstance().getById(id);
        assertNotNull(cLm);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetbyId() throws Exception {

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getById(c.getIdClient());} catch(Exception e) {
            fail("Erreur lors de la r�cup�ration");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreate() throws Exception {

        //assertEquals(c.getId(),1);
        assertEquals(c.getNom(),"aaa");
        assertEquals(c.getPrenom(),"bbb");
        assertEquals(c.getIdentifiant(),"ccc");
        assertEquals(c.getMdp(),"ddd");
        assertEquals(c.getAdrNumero(),9);
        assertEquals(c.getAdrVoie(),"eee");
        assertEquals(c.getAdrPostale(),9);
        assertEquals(c.getAdrVille(),"fff");
        assertEquals(c.getAdrPays(),"ggg");

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testDelete() throws Exception {

        assertTrue((ListeMemoireClientDAO.getInstance().delete(c)), "");
        int id = c.getIdClient();

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getById(id);
            fail("Le produit existe toujours");
        }catch(Exception e){
            ;
        }

        try {
            ListeMemoireClientDAO.getInstance().delete(c);
            fail("Le produit existe toujours");
        }
        catch (Exception e){
            ;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdate() throws Exception {

        MClient c2= new MClient(c.getIdClient(),"aaaaaa", "bbbbbb", "cccccc", "dddddd", 99, "eeeeee", 99, "ffffff", "gggggg");
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().update(c2);
        MClient c3 = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getById(c2.getIdClient());

        assertEquals("aaaaaa",c3.getNom());
        assertEquals("bbbbbb",c3.getPrenom());
        assertEquals("cccccc",c3.getIdentifiant());
        assertEquals("dddddd",c3.getMdp());
        assertEquals(99,c3.getAdrNumero());
        assertEquals("eeeeee",c3.getAdrVoie());
        assertEquals(99,c3.getAdrPostale());
        assertEquals("ffffff",c3.getAdrVille());
        assertEquals("gggggg",c3.getAdrPays());
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testfindAll() throws Exception{

        MClient c2=new MClient(9, "aaa", "bbb", "ccc", "ddd", 9, "eee", 9, "fff", "ggg");
        ListeMemoireClientDAO lma = (ListeMemoireClientDAO) DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO();
        ArrayList<MClient> ar = new ArrayList<MClient>(lma.findAll());
        ar.add(c2);
        lma.create(c2);
        assertEquals(lma.findAll(), ar);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().delete(c);

    }
}
