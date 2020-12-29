package listeMemoire;

import java.util.ArrayList;
import java.util.List;

import metier.MClient;
import dao.ClientDAO;


public class ListeMemoireClientDAO implements ClientDAO {

    private static ListeMemoireClientDAO instance;

    private List<MClient> donnees;

    public ListeMemoireClientDAO(String nom, String prenom, String id_client, String mot_de_passe, String adr_numero, String adr_voie, String adr_code_postal, String adr_ville, String adr_pays) {
    }


    public static ListeMemoireClientDAO getInstance() {

        if (instance == null) {
            instance = new ListeMemoireClientDAO();
        }

        return instance;
    }

    public ListeMemoireClientDAO() {

        this.donnees = new ArrayList<MClient>();

        this.donnees.add(new MClient(1,"laroche","pierre","pl@ul.fr","toto",12,"rue des etudiants ",57990,"Metz", "France"));
    }

    @Override
    public boolean create(MClient objet) {

        objet.setIdClient(3);
        // Ne fonctionne que si l'objet métier est bien fait...
        while (this.donnees.contains(objet)) {
            objet.setIdClient(objet.getIdClient() + 1);

        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(MClient objet) throws IllegalArgumentException{

        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de modification du client, il n'existe pas");
        }

        this.donnees.set(idx, objet);


        return true;
    }

    @Override
    public boolean delete(MClient objet) throws IllegalArgumentException{
        MClient supprime;

        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Erreur de suppression du client, il n'existe pas");
        }
        supprime = this.donnees.remove(idx);


        return objet.equals(supprime);
    }

    @Override
    public MClient getById(int idClient) throws Exception  {
        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(new MClient(idClient,"laroche","pierre","pl@ul.fr","toto",12,"rue des etudiants ",57990,"Metz", "France"));
        if (idx == -1) {
            throw new IllegalArgumentException("L'identifiant du client n'existe pas");
        }

        return this.donnees.get(idx);

    }

    @Override
    public ArrayList<MClient> getByNomPrenom(String n, String p) throws Exception {
        ArrayList<MClient> ar = new ArrayList<MClient>();
        for(int i=0;i<this.donnees.size();i++) {
            if(this.donnees.get(i).getNom()==n && this.donnees.get(i).getPrenom() == p) {
                ar.add(this.donnees.get(i));
            }
        }
        return ar;
    }

    @Override
    public MClient getById(int id, int id2) throws Exception {
        return getById(id);
    }

    public ArrayList<MClient> findAll() {

        return (ArrayList<MClient>) this.donnees;
    }


}