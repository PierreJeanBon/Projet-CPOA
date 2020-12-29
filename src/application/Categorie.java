package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Categorie extends Connexion {
    int id;
    String titre;
    String visuel;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public static void addCategory(int id, String titre, String visuel) {

        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            PreparedStatement categAdd = laConnexion.prepareStatement("INSERT INTO Categorie  VALUES ('"+id+"', '"+titre+"', '"+visuel+"')");
            categAdd.executeUpdate();
            System.out.println("La ligne de cette cat�gorie a �t� ajoutee");


        }
        catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public static void deleteCategory(int id) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            PreparedStatement categRemove = laConnexion.prepareStatement("DELETE FROM Categorie WHERE id_categorie ="+id);
            categRemove.executeUpdate();
            System.out.println("La ligne de cette cat�gorie a �t� supprimee");
        }
        catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public static void modifyCategory(int id, String titre, String visuel){
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement state= laConnexion.createStatement();
            state.executeUpdate("UPDATE Categorie SET titre='"+titre+"',visuel='"+visuel+"' WHERE id_categorie="+id);
            state.close();
        }catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

    public static void afficheCateg() {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="SELECT * FROM Categorie ";
            ArrayList<String> listCategory = new ArrayList<>();
            ResultSet rs = requete.executeQuery(query);
            while(rs.next())
            {
                int id_categorie = rs.getInt(1);
                String titre = rs.getString(2);
                String visuel = rs.getString(3);
                listCategory.add(id_categorie+"");
                listCategory.add(titre);
                listCategory.add(visuel);

                System.out.println("id_categorie: " + id_categorie  + "  titre:"+titre +" visuel:"+visuel);
            }

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public void listCategoryNext() {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="SELECT * FROM Categorie ";
            ArrayList<Categorie> listCategoryNext = new ArrayList<Categorie>();
            ResultSet rs = requete.executeQuery(query);
            while(rs.next())
            {

                int id_categorie = rs.getInt(1);
                String titre = rs.getString(2);
                String visuel = rs.getString(3);

                Categorie ca = new Categorie();
                ca.setId(id_categorie);
                ca.setTitre(titre);
                ca.setVisuel(visuel);

                listCategoryNext.add(ca);

                System.out.println("id_categorie : " + id_categorie  + "  titre:"+titre +" visuel:"+visuel);
            }

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }
}



