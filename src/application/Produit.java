package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Produit extends Connexion {
    private String nom;
    private String descrip;
    private double tarif;
    private String categ;

    public Produit(String nom, String descrip, double tarif, String categ) {
        super();
        this.setNom(nom);
        this.setDescrip(descrip);
        this.setTarif(tarif);
        this.setCateg(categ);
    }
    @Override
    public String toString() {
        return "Nom : " + nom + " , Description : " + descrip + " , Tarif : " + tarif + " , Categorie : " + categ + "";
    }

    public String getNom() {
        return nom;
    }

    public String getDescrip() {

        return descrip;
    }

    public void setDescrip(String descrip) {
        if (descrip==null || descrip.trim().length()==0)
        {
            throw new IllegalArgumentException("Saisir une description");
        }
        this.descrip = descrip;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {

        this.tarif = tarif;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public void setNom(String nom) {
        if (nom==null || nom.trim().length()==0)
        {
            throw new IllegalArgumentException("Le nom est vide !");
        }
        this.nom = nom;
    }

    public static void addProduct(int id_produit,String nom, String description, float tarif, String visuel, int id_categorie) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="INSERT INTO Produit VALUES("+id_produit+",'"+nom+"','"+description+"',"+tarif+",'"+visuel+"',"+id_categorie+")";
            requete.executeUpdate(query);
            System.out.println("Ligne de produit ajoutee");
        }catch (SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void modifyProduct(int id_produit,String nom, String description, float tarif, String visuel, int id_categorie) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="UPDATE Produit SET nom='"+nom+"',"
                    + "description='"+description+"' ,"
                    + "tarif="+tarif+" ,"
                    + "visuel='"+visuel+"' ,"
                    + "id_categorie="+id_categorie+" "
                    + "WHERE id_produit="+id_produit;
            requete.executeUpdate(query);
            System.out.println("Ligne de produit modifiee");
        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void removeProduct(int id_produit) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="DELETE FROM Produit WHERE id_produit="+id_produit;
            requete.executeUpdate(query);
            System.out.println("Ligne de produit supprimee");

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void afficheProduit() {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="SELECT * FROM Produit ";
            ArrayList<String> listProduct = new ArrayList<>();
            ResultSet rs = requete.executeQuery(query);
            while(rs.next())
            {
                int id_produit = rs.getInt(1);
                String nom = rs.getString(2);
                String description = rs.getString(3);
                float  tarif = rs.getFloat(4);
                String visuel = rs.getString(5);
                int  id_categorie = rs.getInt(6);

                listProduct.add(id_produit+"");
                listProduct.add(nom);
                listProduct.add(description);
                listProduct.add(tarif+"");
                listProduct.add(visuel);
                listProduct.add(id_categorie+"'");

                System.out.println("id_produit : " + id_produit  + "  nom:"+nom +" description:"+description+"tarif: " + tarif  + "  visuel:"+visuel +" id_categorie:"+id_categorie);
            }

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }
    public static String corrigerReel(String newValue) {

        newValue = newValue.replaceAll(",", ".");

        if(newValue.contains(".")) {
            if(newValue.matches("[+-]?\\d+(?:\\.\\d+)?")) {
                return newValue;
            }


            String[] nombre = newValue.split("\\.", 2);



            if(nombre[0].matches("\\d*") && nombre[1].matches("\\d*")) {
                return newValue;
            }


            nombre[0] = nombre[0].replaceAll("\\.", "");
            nombre[0] = (nombre[0].replaceAll("[^\\d]", ""));

            nombre[1] = nombre[1].replaceAll("\\.", "");
            nombre[1] = (nombre[1].replaceAll("[^\\d]", ""));

            String str = nombre[0] + "." + nombre[1];
            return str;



        }else if (!newValue.matches("\\d*")) {
            return newValue.replaceAll("[^\\d]", "");
        }

        return newValue;


    }
}

