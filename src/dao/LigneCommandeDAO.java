package dao;

import java.util.ArrayList;

import metier.MLigneCommande;

public interface LigneCommandeDAO extends DAO<MLigneCommande> {

    public abstract ArrayList<MLigneCommande> getByIdcpqt(int idCommande, int idProduit, int quantite, double tarifUnitaire) throws Exception;
}
