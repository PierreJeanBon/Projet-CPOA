package SQL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.ProduitDAO;
import metier.MProduit;


public class MySQLProduitDAO implements ProduitDAO {
    private static MySQLProduitDAO instance;
    public static MySQLProduitDAO getInstance() {
        if (instance == null) {
            instance = new MySQLProduitDAO();
        }
        return instance;
    }
    @Override
    public MProduit getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException{
        MProduit produit = null;
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Produit WHERE id_produit = ?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            produit = new MProduit (id, res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6));
            MaConnection.close();
            req.close();
            res.close();
            return produit;
        }
        return produit;
    }
    @Override
    public MProduit getById (int id1, int id2) throws SQLException, InvalidPropertiesFormatException, IOException{
        return this.getById(id1);
    }
    @Override
    public boolean create(MProduit p ) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("INSERT INTO demuru1u_cpoa2020.Produit (id_produit, nom, description, tarif, visuel, id_categorie) VALUES (?,?,?,?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
        req.setInt(1, p.getIdProduit());
        req.setString(2, p.getNom());
        req.setString(3, p.getDescription());
        req.setFloat(4, p.getTarif());
        req.setString(5, p.getVisuel());
        req.setInt(6, p.getIdCategorie());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        int clef;
        if (res.next()) {
            clef = res.getInt(1);
            p.setIdProduit(clef);
        }
        laConnection.close();
        req.close();
        res.close();

        return nbLignes==1;
    }
    @Override
    public boolean update(MProduit p) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("UPDATE demuru1u_cpoa2020.Produit set nom=?," + "description =?, tarif =?, visuel =?, id_categorie=? WHERE id_produit =?");
        req.setInt(6, p.getIdProduit());
        req.setString(1, p.getNom());
        req.setString(2, p.getDescription());
        req.setFloat(3, p.getTarif());
        req.setString(4, p.getVisuel());
        req.setInt(5, p.getIdCategorie());
        int nbLignes = req.executeUpdate();

        laConnection.close();
        req.close();
        return nbLignes==1;
    }
    @Override
    public boolean delete(MProduit p) throws SQLException, InvalidPropertiesFormatException, IOException{
        try {
            Connection laConnection = Connexion.creeConnexion();
            PreparedStatement req = laConnection.prepareStatement("DELETE FROM Produit WHERE id_produit=?");
            req.setInt(1, p.getIdProduit());
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
    public ArrayList<MProduit> getByIdpndtvc(int idProduit, String nom, String description, float tarif, String visuel, int idCategorie) throws SQLException, InvalidPropertiesFormatException, IOException{
        ArrayList<MProduit> pl = new ArrayList<MProduit>();

        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Produit WHERE id_produit=?");
        req.setInt(1, idProduit);
        req.setString(2, nom);
        req.setString(3, description);
        req.setFloat(4, tarif);
        req.setString(5, visuel);
        req.setInt(6, idCategorie);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            pl.add(new MProduit(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6)));
        }
        req.close();
        res.close();
        return pl;
    }
    public ArrayList<MProduit> findAll() throws InvalidPropertiesFormatException, IOException, SQLException{
        ArrayList<MProduit> pl =new ArrayList<MProduit>();
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Produit");
        ResultSet res = req.executeQuery();
        while (res.next()) {
            pl.add(new MProduit(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6)));
        }
        req.close();
        res.close();
        return pl;
    }
}

