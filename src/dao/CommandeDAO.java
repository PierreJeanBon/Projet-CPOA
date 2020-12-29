package dao;

import java.util.ArrayList;

import metier.MCommande;

public interface CommandeDAO extends DAO<MCommande>{

    public abstract ArrayList<MCommande> getByIdcommandeIdclient(int idco, int idcl) throws Exception;
}
