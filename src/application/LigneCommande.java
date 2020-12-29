package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LigneCommande extends Connexion {

    public static void ajouterLigneCommande(int id_commande,int id_produit, int quantite, float tarif_unitaire) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="INSERT INTO Produit VALUES("+id_commande+","+id_produit+","+quantite+","+tarif_unitaire+")";
            requete.executeUpdate(query);
            System.out.println("Ligne de LigneCommande ajoutee");
        }catch (SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void modifLigneCommande(int id_commande,int id_produit, int quantite, float tarif_unitaire) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="UPDATE Ligne_commande SET quantite='"+quantite+"',"
                    + "tarif_unitaire="+tarif_unitaire+" ,"
                    + "WHERE id_commande="+id_commande;
            requete.executeUpdate(query);
            System.out.println("Ligne de ligneCommande modifiee");
        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void supprLigneCommande(int id_commande) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="DELETE FROM Ligne_commande WHERE id_commande="+id_commande;
            requete.executeUpdate(query);
            System.out.println("Ligne de ligneCommande supprimee");

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void afficheLigneCommande() {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="SELECT * FROM Ligne_commande ";
            ArrayList<String> ligneCommandelist = new ArrayList<>();
            ResultSet rs = requete.executeQuery(query);
            while(rs.next())
            {
                int id_commande = rs.getInt(1);
                int id_produit = rs.getInt(2);
                int quantite = rs.getInt(3);
                float  tarif_unitaire = rs.getFloat(4);

                ligneCommandelist.add(id_commande+"");
                ligneCommandelist.add(id_produit+"");
                ligneCommandelist.add(quantite+"");
                ligneCommandelist.add(tarif_unitaire+"");

                System.out.println("id_commande : " + id_commande  + "  id_produit :"+id_produit +" quantite :"+quantite+"tarif_unitaire : " + tarif_unitaire);
            }

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }
}

