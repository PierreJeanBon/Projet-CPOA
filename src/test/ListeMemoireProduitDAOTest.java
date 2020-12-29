package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import listeMemoire.ListeMemoireProduitDAO;
import metier.MProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class ListeMemoireProduitDAOTest {
    private MProduit p;

    @Before
    public void Setup() throws Exception {

        p =new MProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
        ListeMemoireProduitDAO.getInstance().create(p);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSelectExiste() throws Exception {

        int id=p.getIdProduit();

        MProduit cLm=ListeMemoireProduitDAO.getInstance().getById(id);
        assertNotNull(cLm);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetbyId() throws Exception {

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO().getById(p.getIdProduit());} catch(Exception e) {
            fail("Erreur lors de la r�cup�ration");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreate() throws Exception {

        //assertEquals(c.getId(),1);
        assertEquals(p.getNom(),"aaa");
        assertEquals(p.getDescription(),"222xx");
        assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
        assertEquals(p.getVisuel(),"xxx.png");
        assertEquals(p.getIdCategorie(),10);

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testDelete() throws Exception {

        assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
        int id = p.getIdProduit();

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO().getById(id);
            fail("Le produit existe toujours");
        }catch(Exception e){
            ;
        }

        try {
            ListeMemoireProduitDAO.getInstance().delete(p);
            fail("Le produit existe toujours");
        }
        catch (Exception e){
            ;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdate() throws Exception {

        MProduit p2= new MProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",12);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO().update(p2);
        MProduit p3 = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO().getById(p2.getIdProduit());

        assertEquals("bbb", p3.getNom());
        assertEquals("333zz", p3.getDescription());
        assertEquals((float)1,0, p3.getTarif());
        assertEquals("yyy.png", p3.getVisuel());
        assertEquals(2, p3.getIdCategorie());
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testfindAll() throws Exception{

        MProduit c2=new MProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
        ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO();
        ArrayList<MProduit> ar = new ArrayList<MProduit>(lma.findAll());
        ar.add(c2);
        lma.create(c2);
        assertEquals(lma.findAll(), ar);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO().delete(p);

    }
}
