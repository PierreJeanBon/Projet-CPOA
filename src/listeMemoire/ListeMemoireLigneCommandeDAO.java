package listeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.LigneCommandeDAO;
import metier.MLigneCommande;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO {

    private static ListeMemoireLigneCommandeDAO instance;

    private List<MLigneCommande> donnees;


    public static ListeMemoireLigneCommandeDAO getInstance() {

        if (instance == null) {
            instance = new ListeMemoireLigneCommandeDAO();
        }

        return instance;
    }

    private ListeMemoireLigneCommandeDAO() {

        this.donnees = new ArrayList<MLigneCommande>();

        this.donnees.add(new MLigneCommande(1,2,2,41.5));
        this.donnees.add(new MLigneCommande(1,6,1,15));
    }


    @Override
    public boolean create(MLigneCommande objet) {

        objet.setIdCommande(3);
        // Ne fonctionne que si l'objet m�tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setIdProduit(objet.getIdProduit() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(MLigneCommande objet) {

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de modification de la ligne commande, elle n'existe pas");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(MLigneCommande objet) {

        MLigneCommande supprime;

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de suppression de la ligne commande, elle n'existe pas");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public MLigneCommande getById(int id) {
        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(new MLigneCommande(id, 3, 25,180));
        if (idx == -1) {
            throw new IllegalArgumentException("L'identifiant de la commande n'existe pas");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<MLigneCommande> findAll() {
        return (ArrayList<MLigneCommande>) this.donnees;
    }

    @Override
    public MLigneCommande getById(int id, int id2) throws Exception {
        return getById(id);
    }

    @Override
    public ArrayList<MLigneCommande> getByIdcpqt(int idCommande, int idProduit, int quantite, double tarifUnitaire) throws Exception {
        ArrayList<MLigneCommande> ar = new ArrayList<MLigneCommande>();
        for(int i=0;i<this.donnees.size();i++) {
            if(this.donnees.get(i).getIdCommande()==idCommande && this.donnees.get(i).getIdProduit() == idProduit && this.donnees.get(i).getQuantite() == quantite && this.donnees.get(i).getTarifUnitaire() == tarifUnitaire) {
                ar.add(this.donnees.get(i));
            }
        }
        return ar;
    }
}