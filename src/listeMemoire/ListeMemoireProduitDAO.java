package listeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import metier.MProduit;

public class ListeMemoireProduitDAO implements ProduitDAO {

    private static ListeMemoireProduitDAO instance;

    private List<MProduit> donnees;


    public static ListeMemoireProduitDAO getInstance() {

        if (instance == null) {
            instance = new ListeMemoireProduitDAO();
        }

        return instance;
    }

    private ListeMemoireProduitDAO() {

        this.donnees = new ArrayList<MProduit>();

        this.donnees.add(new MProduit(2,"Sonic te kiffe", "Inspir� par la saga S�ga (c'est plus fort que toi ...",(float) 41.5,"pull1.png",1));
        this.donnees.add(new MProduit(6,"La chaleur des rennes","Classique mais efficace, un bonnet dont l'�l�gance...",15,"bonnet0.png",2));
    }


    @Override
    public boolean create(MProduit objet) {

        objet.setIdProduit(3);
        // Ne fonctionne que si l'objet m�tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setIdProduit(objet.getIdProduit() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(MProduit objet) {

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de modification du produit, il n'existe pas");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(MProduit objet) {

        MProduit supprime;

        // Ne fonctionne que si l'objet m�tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de suppression du produit, il n'existe pas");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public MProduit getById(int id) {
        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(new MProduit(id, "Sonic te kiffe", "Inspir� par la saga S�ga (c'est plus fort que toi ...",(float) 41.5,"pull1.png",1));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun produit ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public ArrayList<MProduit> findAll() {
        return (ArrayList<MProduit>) this.donnees;
    }

    @Override
    public MProduit getById(int id, int id2) throws Exception {
        return getById(id);
    }

    @Override
    public ArrayList<MProduit> getByIdpndtvc(int idProduit, String nom, String description, float tarif, String visuel, int idCategorie) throws Exception {
        ArrayList<MProduit> ar = new ArrayList<MProduit>();
        for(int i=0;i<this.donnees.size();i++) {
            if(this.donnees.get(i).getIdProduit()==idProduit && this.donnees.get(i).getNom() == nom && this.donnees.get(i).getDescription() == description && this.donnees.get(i).getTarif() == tarif && this.donnees.get(i).getVisuel() == visuel && this.donnees.get(i).getIdCategorie() == idCategorie) {
                ar.add(this.donnees.get(i));
            }
        }
        return ar;
    }
}
