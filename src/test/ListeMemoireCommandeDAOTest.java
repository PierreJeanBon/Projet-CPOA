package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import listeMemoire.ListeMemoireCommandeDAO;
import metier.MCommande;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class ListeMemoireCommandeDAOTest {

    private MCommande co;

    @Before
    public void Setup() throws Exception {

        co =new MCommande(9, "02-02-2020", 9);
        ListeMemoireCommandeDAO.getInstance().create(co);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSelectExiste() throws Exception {

        int id=co.getId();

        MCommande cLm=ListeMemoireCommandeDAO.getInstance().getById(id);
        assertNotNull(cLm);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetbyId() throws Exception {

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO().getById(co.getId());} catch(Exception e) {
            fail("Erreur lors de la r�cup�ration");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreate() throws Exception {

        //assertEquals(c.getId(),1);
        assertEquals(co.getIdClient(),9);
        assertEquals(co.getDateCommande(),"02-02-2020");


    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testDelete() throws Exception {

        assertTrue((ListeMemoireCommandeDAO.getInstance().delete(co)), "");
        int id = co.getId();

        try {
            DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO().getById(id);
            fail("Le produit existe toujours");
        }catch(Exception e){
            ;
        }

        try {
            ListeMemoireCommandeDAO.getInstance().delete(co);
            fail("Le produit existe toujours");
        }
        catch (Exception e){
            ;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdate() throws Exception {

        MCommande co2= new MCommande(co.getId(),"02-02-2020",99);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO().update(co2);
        MCommande co3 = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO().getById(co2.getId());

        assertEquals(9, co3.getIdClient());
        assertEquals("02-02-2020", co3.getDateCommande());

    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testfindAll() throws Exception{

        MCommande co2=new MCommande(9,"02-02-2020",9);
        ListeMemoireCommandeDAO lma = (ListeMemoireCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO();
        ArrayList<MCommande> ar = new ArrayList<MCommande>(lma.findAll());
        ar.add(co2);
        lma.create(co2);
        assertEquals(lma.findAll(), ar);
        DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAOO().delete(co);

    }
}
