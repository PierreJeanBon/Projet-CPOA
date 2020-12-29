package dao;

import java.util.ArrayList;

import metier.MClient;

public interface ClientDAO extends DAO<MClient> {

    public abstract ArrayList<MClient> getByNomPrenom(String n,String p) throws Exception;



}
