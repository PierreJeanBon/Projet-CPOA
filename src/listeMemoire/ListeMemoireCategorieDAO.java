package listeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import metier.MCategorie;

public class ListeMemoireCategorieDAO implements CategorieDAO {

	private static ListeMemoireCategorieDAO instance;

	private List<MCategorie> donnees;


	public static ListeMemoireCategorieDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}

		return instance;
	}

	private ListeMemoireCategorieDAO() {

		this.donnees = new ArrayList<MCategorie>();

		this.donnees.add(new MCategorie(1, "Pulls", "pulls.png"));
		this.donnees.add(new MCategorie(2, "Bonnets", "bonnets.png"));
	}


	@Override
	public boolean create(MCategorie objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet m�tier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);

		return ok;
	}

	@Override
	public boolean update(MCategorie objet) {

		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Erreur de la modifiaction de la cat�gorie, elle n'existe pas");
		} else {

			this.donnees.set(idx, objet);
		}

		return true;
	}

	@Override
	public boolean delete(MCategorie objet) {

		MCategorie supprime;

		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Erreur de suppression de la categorie, elle n'existe pas");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	@Override
	public MCategorie getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new MCategorie(id, "test", "test.png"));
		if (idx == -1) {
			throw new IllegalArgumentException("L'identifiant de cette categorie n'existe pas");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<MCategorie> findAll() {
		return (ArrayList<MCategorie>) this.donnees;
	}

	@Override
	public MCategorie getById(int id, int id2) throws Exception {
		return getById(id);
	}

	public ArrayList<MCategorie> getByIdTitreVisuel(int id, String t, String v) throws Exception {
		ArrayList<MCategorie> ar = new ArrayList<MCategorie>();
		for(int i=0;i<this.donnees.size();i++) {
			if(this.donnees.get(i).getId()==id && this.donnees.get(i).getTitre() == t && this.donnees.get(i).getVisuel() == v) {
				ar.add(this.donnees.get(i));
			}
		}
		return ar;
	}

	@Override
	public MCategorie getByTitre(String n) throws Exception {
		return null;
	}
}


