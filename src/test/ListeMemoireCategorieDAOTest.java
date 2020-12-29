package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import listeMemoire.ListeMemoireCategorieDAO;
import metier.MCategorie;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class ListeMemoireCategorieDAOTest {

    private MCategorie ca;

    @Before
    public void Setup() throws Exception {

        ca =new MCategorie(9, "aa", "bb");
        ListeMemoireCategorieDAO.getInstance().create(ca);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSelectExiste() throws Exception {

        int id=ca.getId();

        MCategorie cLm=ListeMemoireCategorieDAO.getInstance().getById(id);
        assertNotNull(cLm);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetbyId() throws Exception {

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO().getById(ca.getId());} catch(Exception e) {
            fail("Erreur lors de la r�cup�ration");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreate() throws Exception {

        //assertEquals(c.getId(),1);
        assertEquals(ca.getTitre(),"aa");
        assertEquals(ca.getVisuel(),"bb");


    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testDelete() throws Exception {

        assertTrue((ListeMemoireCategorieDAO.getInstance().delete(ca)), "");
        int id = ca.getId();

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO().getById(id);
            fail("Le produit existe toujours");
        }catch(Exception e){
            ;
        }

        try {
            ListeMemoireCategorieDAO.getInstance().delete(ca);
            fail("Le produit existe toujours");
        }
        catch (Exception e){
            ;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdate() throws Exception {

        MCategorie ca2= new MCategorie(ca.getId(),"aaaa","bbbb");
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO().update(ca2);
        MCategorie ca3 = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO().getById(ca2.getId());

        assertEquals("aaaa", ca3.getTitre());
        assertEquals("bbbb", ca3.getVisuel());

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testfindAll() throws Exception{

        MCategorie ca2=new MCategorie(9, "aa", "bb");
        ListeMemoireCategorieDAO lma = (ListeMemoireCategorieDAO) DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO();
        ArrayList<MCategorie> ar = new ArrayList<MCategorie>(lma.findAll());
        ar.add(ca2);
        lma.create(ca2);
        assertEquals(lma.findAll(), ar);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO().delete(ca);

    }
}


