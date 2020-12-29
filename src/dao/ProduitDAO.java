package dao;

import java.util.ArrayList;

import metier.MProduit;

public interface ProduitDAO extends DAO<MProduit> {

    public abstract ArrayList<MProduit> getByIdpndtvc(int idProduit, String nom, String description, float tarif, String visuel, int idCategorie) throws Exception;
}
