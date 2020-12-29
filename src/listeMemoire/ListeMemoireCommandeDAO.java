package listeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.CommandeDAO;
import metier.MCommande;

public class ListeMemoireCommandeDAO implements CommandeDAO {

    private static ListeMemoireCommandeDAO instance;

    private List<MCommande> donnees;


    public static ListeMemoireCommandeDAO getInstance() {

        if (instance == null) {
            instance = new ListeMemoireCommandeDAO();
        }

        return instance;
    }

    private ListeMemoireCommandeDAO() {

        this.donnees = new ArrayList<MCommande>();

        this.donnees.add(new MCommande(1, "2020-09-02",1));
        this.donnees.add(new MCommande(2, "2020-08-30",1));
    }


    @Override
    public boolean create(MCommande objet) {

        objet.setId(3);
        // Ne fonctionne que si l'objet m�tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(MCommande objet) {

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de modification de la commande, elle n'existe pas");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(MCommande objet) {

        MCommande supprime;

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de suppression de la commande, elle n'existe pas");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public MCommande getById(int id) {
        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(new MCommande(id,"2000-6-7",2));
        if (idx == -1) {
            throw new IllegalArgumentException("L'identifiant de la commande n'existe pas");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<MCommande> findAll() {
        return (ArrayList<MCommande>) this.donnees;
    }

    @Override
    public MCommande getById(int id, int id2) throws Exception {
        return getById(id);
    }

    @Override
    public ArrayList<MCommande> getByIdcommandeIdclient(int idco, int idcl) throws Exception {
        ArrayList<MCommande> ar = new ArrayList<MCommande>();
        for(int i=0;i<this.donnees.size();i++) {
            if(this.donnees.get(i).getId()==idco && this.donnees.get(i).getId() == idcl) {
                ar.add(this.donnees.get(i));
            }
        }
        return ar;
    }


}



