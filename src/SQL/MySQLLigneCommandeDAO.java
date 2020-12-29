package SQL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.LigneCommandeDAO;
import metier.MLigneCommande;


public class MySQLLigneCommandeDAO implements LigneCommandeDAO {
    private static MySQLLigneCommandeDAO instance;
    public static MySQLLigneCommandeDAO getInstance() {
        if (instance == null) {
            instance = new MySQLLigneCommandeDAO();
        }
        return instance;
    }
    @Override
    public MLigneCommande getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException{
        MLigneCommande lignecommande = null;
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande = ?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            lignecommande = new MLigneCommande (id, res.getInt(2), res.getInt(3), res.getDouble(4));
            MaConnection.close();
            req.close();
            res.close();
            return lignecommande;
        }
        return lignecommande;
    }
    @Override
    public MLigneCommande getById (int id1, int id2) throws SQLException, InvalidPropertiesFormatException, IOException{
        return this.getById(id1);
    }
    @Override
    public boolean create(MLigneCommande lc ) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("INSERT INTO demuru1u_cpoa2020.Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) VALUES (?,?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
        req.setInt(1, lc.getIdCommande());
        req.setInt(2, lc.getIdProduit());
        req.setInt(3, lc.getQuantite());
        req.setDouble(4, lc.getTarifUnitaire());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        int clef;
        if (res.next()) {
            clef = res.getInt(1);
            lc.setIdCommande(clef);
        }
        laConnection.close();
        req.close();
        res.close();

        return nbLignes==1;
    }
    @Override
    public boolean update(MLigneCommande lc) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("UPDATE demuru1u_cpoa2020.Ligne_commande set id_produit=?," + "quantite =?, tarif_unitaire =? WHERE id_commande =?");
        req.setInt(4, lc.getIdCommande());
        req.setInt(1, lc.getIdProduit());
        req.setInt(2, lc.getQuantite());
        req.setDouble(3, lc.getTarifUnitaire());
        int nbLignes = req.executeUpdate();

        laConnection.close();
        req.close();
        return nbLignes==1;
    }
    @Override
    public boolean delete(MLigneCommande lc) throws SQLException, InvalidPropertiesFormatException, IOException{
        try {
            Connection laConnection = Connexion.creeConnexion();
            PreparedStatement req = laConnection.prepareStatement("DELETE FROM Ligne_commande WHERE id_commande=? AND id_produit=?");
            req.setInt(1, lc.getIdCommande());
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
    public ArrayList<MLigneCommande> getByIdcpqt(int idCommande, int idProduit, int quantite, double tarifUnitaire) throws SQLException, InvalidPropertiesFormatException, IOException{
        ArrayList<MLigneCommande> lc = new ArrayList<MLigneCommande>();

        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=? AND id_produit=?");
        req.setInt(1, idCommande);
        req.setInt(2, idProduit);
        req.setInt(3, quantite);
        req.setDouble(4, tarifUnitaire);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            lc.add(new MLigneCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
        }
        req.close();
        res.close();
        return lc;
    }
    public ArrayList<MLigneCommande> findAll() throws InvalidPropertiesFormatException, IOException, SQLException{
        ArrayList<MLigneCommande> lc =new ArrayList<MLigneCommande>();
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Ligne_commande");
        ResultSet res = req.executeQuery();
        while (res.next()) {
            lc.add(new MLigneCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
        }
        req.close();
        res.close();
        return lc;
    }
}

