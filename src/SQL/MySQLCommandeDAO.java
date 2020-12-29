package SQL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.CommandeDAO;
import metier.MCommande;


public class MySQLCommandeDAO implements CommandeDAO {

    private static MySQLCommandeDAO instance;

    public static MySQLCommandeDAO getInstance() {
        if (instance == null) {
            instance = new MySQLCommandeDAO();
        }
        return instance;
    }
    @Override
    public MCommande getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException{
        MCommande categorie = null;
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Commande WHERE id_commande = ?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            categorie = new MCommande (id, res.getDate(2), res.getInt(3));
            MaConnection.close();
            req.close();
            res.close();
            return categorie;
        }
        return categorie;
    }
    @Override
    public MCommande getById (int id1, int id2) throws SQLException, InvalidPropertiesFormatException, IOException{
        return this.getById(id1);
    }
    @Override
    public boolean create(MCommande c ) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("INSERT INTO demuru1u_cpoa2020.Commande (id_commande, date_commande, id_client) VALUES (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
        req.setInt(10, c.getId());
        req.setDate(1, c.getDateCommande());
        req.setInt(2, c.getIdClient());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        int clef;
        if (res.next()) {
            clef = res.getInt(1);
            c.setId(clef);
        }
        laConnection.close();
        req.close();
        res.close();

        return nbLignes==1;
    }
    @Override
    public boolean update(MCommande c) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("UPDATE demuru1u_cpoa2020.Commande set date_commande =?, id_client =?,  WHERE id_commande =?");
        req.setInt(10, c.getId());
        req.setDate(1,  c.getDateCommande());
        req.setInt(2, c.getIdClient());
        int nbLignes = req.executeUpdate();

        laConnection.close();
        req.close();
        return nbLignes==1;
    }
    @Override
    public boolean delete(MCommande c) throws SQLException, InvalidPropertiesFormatException, IOException{
        try {
            Connection laConnection = Connexion.creeConnexion();
            PreparedStatement req = laConnection.prepareStatement("DELETE FROM Commande WHERE id_commande=?");
            req.setInt(1, c.getId());
            int nbLignes = req.executeUpdate();
            laConnection.close();
            req.close();
            return nbLignes == 1;
        }
        catch (Exception e) {
            return false;
        }
    }
    @Override
    public ArrayList<MCommande> getByIdcommandeIdclient(int idco, int idcl) throws SQLException, InvalidPropertiesFormatException, IOException{
        ArrayList<MCommande> cl = new ArrayList<MCommande>();

        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Commande WHERE id_commande=? ");
        req.setInt(1, idco);
        req.setInt(2, idcl);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MCommande(res.getInt(1), res.getDate(2), res.getInt(3)));
        }
        req.close();
        res.close();
        return cl;
    }
    public ArrayList<MCommande> findAll() throws InvalidPropertiesFormatException, IOException, SQLException{
        ArrayList<MCommande> cl =new ArrayList<MCommande>();
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Commande");
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MCommande(res.getInt(1), res.getDate(2), res.getInt(3)));
        }
        req.close();
        res.close();
        return cl;
    }

}



