package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class Client {


    public String nom;
    public String prenom;
    public String id_client;
    public String mot_de_passe;
    public String adr_numero;
    public String adr_voie;
    public String adr_code_postal;
    public String adr_ville;
    public String adr_pays;

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", id_client='" + id_client + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", adr_numero='" + adr_numero + '\'' +
                ", adr_voie='" + adr_voie + '\'' +
                ", adr_code_postal='" + adr_code_postal + '\'' +
                ", adr_ville='" + adr_ville + '\'' +
                ", adr_pays='" + adr_pays + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getAdr_numero() {
        return adr_numero;
    }

    public void setAdr_numero(String adr_numero) {
        this.adr_numero = adr_numero;
    }

    public String getAdr_voie() {
        return adr_voie;
    }

    public void setAdr_voie(String adr_voie) {
        this.adr_voie = adr_voie;
    }

    public String getAdr_code_postal() {
        return adr_code_postal;
    }

    public void setAdr_code_postal(String adr_code_postal) {
        this.adr_code_postal = adr_code_postal;
    }

    public String getAdr_ville() {
        return adr_ville;
    }

    public void setAdr_ville(String adr_ville) {
        this.adr_ville = adr_ville;
    }

    public String getAdr_pays() {
        return adr_pays;
    }

    public void setAdr_pays(String adr_pays) {
        this.adr_pays = adr_pays;
    }

    public Client(String nom, String prenom, String id_client, String mot_de_passe, String adr_numero, String adr_voie, String adr_code_postal, String adr_ville, String adr_pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.id_client = id_client;
        this.mot_de_passe = mot_de_passe;
        this.adr_numero = adr_numero;
        this.adr_voie = adr_voie;
        this.adr_code_postal = adr_code_postal;
        this.adr_ville = adr_ville;
        this.adr_pays = adr_pays;
    }

    public static void addClient(int id_client, String nom, String prenom, String identifiant, String mot_de_passe, String adr_numero, String adr_voie, String adr_code_postal, String adr_ville, String adr_pays) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="INSERT INTO Client VALUES("+id_client+",'"+nom+"','"+prenom+"','"+identifiant+"','"+mot_de_passe+"','"+adr_numero+"','"+adr_voie+"','"+adr_code_postal+"','"+adr_ville+"','"+adr_pays+"')";
            requete.executeUpdate(query);
            System.out.println("Ligne de client ajoutee");
        }catch (SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void modifyClient(int id_client,String nom, String prenom, String identifiant,String mot_de_passe,String adr_numero,String adr_voie,String adr_code_postal,String adr_ville,String adr_pays) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="UPDATE Client SET nom='"+nom+"',prenom='"+prenom+"',identifiant='"+identifiant+"',mot_de_passe='"+mot_de_passe+"',adr_numero='"+adr_numero+"',adr_voie='"+adr_voie+"',adr_code_postal='"+adr_code_postal+"',adr_ville='"+adr_ville+"',adr_pays='"+adr_pays+"' WHERE id_client="+id_client;
            requete.executeUpdate(query);
            System.out.println("Ligne de client modifiee");
        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

    public static void removeClient(int id_client) {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="DELETE FROM Client WHERE id_client="+id_client;
            requete.executeUpdate(query);
            System.out.println("Ligne de client supprimee");

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }


    public static void afficheClient() {
        try {
            Connection laConnexion = SQL.Connexion.creeConnexion();
            Statement requete = laConnexion.createStatement();
            String query="SELECT * FROM Client ";

            ArrayList<String> listClient = new ArrayList<>();

            ResultSet rs = requete.executeQuery(query);
            while(rs.next())
            {
                int id_client = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String identifiant = rs.getString(4);
                String mot_de_passe = rs.getString(5);
                String adr_numero = rs.getString(6);
                String adr_voie = rs.getString(7);
                String adr_code_postal = rs.getString(8);
                String adr_ville = rs.getString(9);
                String adr_pays = rs.getString(10);

                listClient.add(id_client+"");
                listClient.add(nom);
                listClient.add(prenom);
                listClient.add(identifiant);
                listClient.add(mot_de_passe);
                listClient.add(adr_numero);
                listClient.add(adr_voie);
                listClient.add(adr_code_postal);
                listClient.add(adr_ville);
                listClient.add(adr_pays);

                System.out.println("id_client : " + id_client  + "  nom:"+nom +" prenom:"+prenom+" identifiant:"+identifiant+" mot_de_passe:"+mot_de_passe
                        +" adr_numero:"+adr_numero+" adr_voie:"+adr_voie+" adr_code_postal:"+adr_code_postal+" adr_ville:"+adr_ville+" adr_pays:"+adr_pays);
            }

        }catch(SQLException sqle) {
            System.out.println("Pb select :" + sqle.getMessage());
        }
    }

}