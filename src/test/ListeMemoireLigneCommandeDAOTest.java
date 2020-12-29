package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import listeMemoire.ListeMemoireLigneCommandeDAO;
import metier.MLigneCommande;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class ListeMemoireLigneCommandeDAOTest {

    private MLigneCommande lc;

    @Before
    public void Setup() throws Exception {

        lc =new MLigneCommande(1, 2, 3, 4);
        ListeMemoireLigneCommandeDAO.getInstance().create(lc);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSelectExiste() throws Exception {

        int id=lc.getIdCommande();

        MLigneCommande cLm=ListeMemoireLigneCommandeDAO.getInstance().getById(id);
        assertNotNull(cLm);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetbyId() throws Exception {

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO().getById(lc.getIdCommande());} catch(Exception e) {
            fail("Erreur lors de la r�cup�ration");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    @Test
    public void testCreate() throws Exception {

        //assertEquals(c.getId(),1);
        assertEquals(lc.getIdProduit(),2);
        assertEquals(lc.getQuantite(),3);
        assertEquals(lc.getTarifUnitaire(),4);


    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testDelete() throws Exception {

        assertTrue((ListeMemoireLigneCommandeDAO.getInstance().delete(lc)), "");
        int id = lc.getIdCommande();

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO().getById(id);
            fail("Le produit existe toujours");
        }catch(Exception e){
            ;
        }

        try {
            ListeMemoireLigneCommandeDAO.getInstance().delete(lc);
            fail("Le produit existe toujours");
        }
        catch (Exception e){
            ;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    @Test
    public void testUpdate() throws Exception {

        MLigneCommande lc2= new MLigneCommande(lc.getIdCommande(),22,33,44);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO().update(lc2);
        MLigneCommande lc3 = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO().getById(lc2.getIdCommande());

        assertEquals(22, lc3.getIdProduit());
        assertEquals(33, lc3.getQuantite());
        assertEquals(44, lc3.getTarifUnitaire());


    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testfindAll() throws Exception{

        MLigneCommande lc2=new MLigneCommande(1,2,3,4);
        ListeMemoireLigneCommandeDAO lma = (ListeMemoireLigneCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO();
        ArrayList<MLigneCommande> ar = new ArrayList<MLigneCommande>(lma.findAll());
        ar.add(lc2);
        lma.create(lc2);
        assertEquals(lma.findAll(), ar);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO().delete(lc);

    }
}

