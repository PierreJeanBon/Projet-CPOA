
package SQL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.ClientDAO;
import metier.MClient;


public class MySQLClientDAO implements ClientDAO {
    private static MySQLClientDAO instance;
    public static MySQLClientDAO getInstance() {
        if (instance == null) {
            instance = new MySQLClientDAO();
        }
        return instance;
    }
    @Override
    public MClient getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException{
        MClient client = null;
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Client WHERE id_client = ?");
        req.setInt(1, id);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            client = new MClient (id, res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getInt(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10));
            MaConnection.close();
            req.close();
            res.close();
            return client;
        }
        return client;
    }
    @Override
    public MClient getById (int id1, int id2) throws SQLException, InvalidPropertiesFormatException, IOException{
        return this.getById(id1);
    }
    @Override
    public boolean create(MClient c ) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("INSERT INTO demuru1u_cpoa2020.Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES (?,?,?,?,?,?,?,?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
        req.setInt(1, c.getIdClient());
        req.setString(2, c.getNom());
        req.setString(3, c.getPrenom());
        req.setString(4, c.getIdentifiant());
        req.setString(5, c.getMdp());
        req.setInt(6, c.getAdrNumero());
        req.setString(7, c.getAdrVoie());
        req.setInt(8, c.getAdrPostale());
        req.setString(9, c.getAdrVille());
        req.setString(10, c.getAdrPays());
        int nbLignes = req.executeUpdate();
        ResultSet res = req.getGeneratedKeys();
        int clef;
        if (res.next()) {
            clef = res.getInt(1);
            c.setIdClient(clef);
        }
        laConnection.close();
        req.close();
        res.close();

        return nbLignes==1;
    }
    @Override
    public boolean update(MClient c) throws SQLException, InvalidPropertiesFormatException, IOException{
        Connection laConnection = Connexion.creeConnexion();
        PreparedStatement req = laConnection.prepareStatement("UPDATE demuru1u_cpoa2020.Client set nom=?," + "prenom =?, identifiant =?, mot_de_passe =?, adr_numero=?, adr_voie =?, adr_code_postal =?, adr_ville =?, adr_pays =? WHERE id_client =?");
        req.setInt(10, c.getIdClient());
        req.setString(1,  c.getNom());
        req.setString(2, c.getPrenom());
        req.setString(3, c.getIdentifiant());
        req.setString(4, c.getMdp());
        req.setInt(5, c.getAdrNumero());
        req.setString(6,  c.getAdrVoie());
        req.setInt(7, c.getAdrPostale());
        req.setString(8, c.getAdrVille());
        req.setString(9, c.getAdrPays());
        int nbLignes = req.executeUpdate();

        laConnection.close();
        req.close();
        return nbLignes==1;
    }
    @Override
    public boolean delete(MClient c) throws SQLException, InvalidPropertiesFormatException, IOException{
        try {
            Connection laConnection = Connexion.creeConnexion();
            PreparedStatement req = laConnection.prepareStatement("DELETE FROM Client WHERE id_client=?");
            req.setInt(1, c.getIdClient());
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
    public ArrayList<MClient> getByNomPrenom(String n, String p) throws SQLException, InvalidPropertiesFormatException, IOException{
        ArrayList<MClient> cl = new ArrayList<MClient>();

        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Client WHERE nom=? AND prenom=?");
        req.setString(1, n);
        req.setString(2, p);
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MClient(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getInt(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10)));
        }
        req.close();
        res.close();
        return cl;
    }
    public ArrayList<MClient> findAll() throws InvalidPropertiesFormatException, IOException, SQLException{
        ArrayList<MClient> cl =new ArrayList<MClient>();
        Connection MaConnection = Connexion.creeConnexion();
        PreparedStatement req = MaConnection.prepareStatement("SELECT * FROM Client");
        ResultSet res = req.executeQuery();
        while (res.next()) {
            cl.add(new MClient(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getInt(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10)));
        }
        req.close();
        res.close();
        return cl;
    }

}
