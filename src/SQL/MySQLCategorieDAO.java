package SQL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.CategorieDAO;
import metier.MCategorie;


public class MySQLCategorieDAO implements CategorieDAO {

    private static MySQLCategorieDAO instance;

    public static MySQLCategorieDAO getInstance() {
        if (instance == null) {
            instance = new MySQLCategorieDAO();
        }
        return instance;
    }
    @Override
    public MCategorie getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException{
        MCategorie categorie = null;
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Categorie WHERE id_categorie = ?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            categorie = new MCategorie (id, res.getString(2), res.getString(3));
            MaConnection.close();
            req.close();
            res.close();
            return categorie;
        }
        return categorie;
    }
    @Override
    public MCategorie getById (int id1, int id2) throws SQLException, InvalidPropertiesFormatException, IOException{
        return this.getById(id1);
    }
    @Override
    public boolean create(MCategorie c ) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("INSERT INTO demuru1u_cpoa2020.Categorie (id_categorie, titre, visuel) VALUES (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
        req.setInt(10, c.getId());
        req.setString(1,  c.getTitre());
        req.setString(2, c.getVisuel());
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
    public boolean update(MCategorie c) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("UPDATE demuru1u_cpoa2020.Categorie set titre =?, visuel =?,  WHERE id_categorie =?");
        req.setInt(10, c.getId());
        req.setString(1,  c.getTitre());
        req.setString(2, c.getVisuel());
        int nbLignes = req.executeUpdate();

        laConnection.close();
        req.close();
        return nbLignes==1;
    }
    @Override
    public boolean delete(MCategorie c) throws SQLException, InvalidPropertiesFormatException, IOException{
        try {
            Connection laConnection = Connexion.creeConnexion();
            PreparedStatement req = laConnection.prepareStatement("DELETE FROM Categorie WHERE id_categorie=?");
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
    public ArrayList<MCategorie> getByIdTitreVisuel(int id, String a, String b) throws SQLException, InvalidPropertiesFormatException, IOException{
        ArrayList<MCategorie> cl = new ArrayList<MCategorie>();

        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Categorie WHERE id_categorie=? ");
        req.setInt(1, id);
        req.setString(2, a);
        req.setString(3, b);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MCategorie(res.getInt(1), res.getString(2), res.getString(3)));
        }
        req.close();
        res.close();
        return cl;
    }
    public ArrayList<MCategorie> findAll() throws InvalidPropertiesFormatException, IOException, SQLException{
        ArrayList<MCategorie> cl =new ArrayList<MCategorie>();
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Categorie");
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MCategorie(res.getInt(1), res.getString(2), res.getString(3)));
        }
        req.close();
        res.close();
        return cl;
    }

    @Override
    public MCategorie getByTitre(String n) throws Exception {
        return null;
    }
}

