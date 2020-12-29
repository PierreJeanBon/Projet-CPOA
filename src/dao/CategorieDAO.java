package dao;

import metier.MCategorie;

public interface CategorieDAO extends DAO<MCategorie> {

	public abstract MCategorie getByTitre(String n) throws Exception;
}
