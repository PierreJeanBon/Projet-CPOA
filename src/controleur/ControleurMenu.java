package controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class ControleurMenu {
    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;


    //****************************** PARTIE CHANGEMENT DE PAGES ******************************
    //Changer de page vers le menu SQL
    public void versChoixSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/ChoixSQL.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 400);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Accueil MySql");
            accueilSql.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le ClientSQL
    public void onClickVersGestionClientSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionClientSQL.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 1024, 728);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Gestion d'un client en SQL");
            accueilSql.show();

        } catch(Exception e) {
            e.printStackTrace();

        }
    }
    //Changer de page vers le ProduitSQL
    public void onClickVersGestionProduitSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionProduitSQL.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 1024, 768);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Gestion d'un Produit en SQL");
            accueilSql.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le CategorieSQL
    public void onClickVersGestionCategorieSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionCategorieSQL.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 550);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Gestion d'une Categorie en SQL");
            accueilSql.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le CategorieListeMemoire
    public void onClickVersGestionCategorieLM() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionCategorieLM.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 550);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Gestion d'une Categorie en Liste Mémoire");
            accueilSql.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le CommandeSQL
    public void onClickVersGestionCommandeSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionCommandeSQL.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilSql = new Stage();
            Scene scene = new Scene((AnchorPane) root, 783, 550);
            accueilSql.setScene(scene);
            accueilSql.setTitle("Gestion d'une Commande en SQL");
            accueilSql.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //Changer de page vers le menu Liste Memoire
    public void versChoixLM() {
        try {
            URL fxmlURL = getClass().getResource("/vue/ChoixListeMemoire.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage accueilLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 400);
            accueilLM.setScene(scene);
            accueilLM.setTitle("Accueil Liste Mémoire");
            accueilLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    //Changer de page vers le Client Liste Memoire
    public void onClickVersGestionClientLM() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionClientListeMemoire.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage gestionLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 1024, 728);
            gestionLM.setScene(scene);
            gestionLM.setTitle("Gestion d'un client en Liste Mémoire");
            gestionLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le Produit Liste Memoire
    public void onClickVersGestionProduitLM() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionProduitListeMemoire.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage gestionLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 700);
            gestionLM.setScene(scene);
            gestionLM.setTitle("Gestion d'un Produit en Liste Mémoire");
            gestionLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Changer de page vers le Commande Liste Memoire
    public void onClickVersGestionCommandeLM() {
        try {
            URL fxmlURL = getClass().getResource("/vue/GestionCommandeListeMemoire.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage gestionLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 783, 550);
            gestionLM.setScene(scene);
            gestionLM.setTitle("Gestion d'une Commande en Liste Mémoire");
            gestionLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void onClickRetourSQLouListe() {
        try {
            URL fxmlURL = getClass().getResource("/vue/sqlOuListe.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage gestionLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 400);
            gestionLM.setScene(scene);
            gestionLM.setTitle("Choix");
            gestionLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }




    @FXML
    void onClickQuitter(){
        Stage stage = (Stage) quitter.getScene().getWindow();
        stage.close();
    }


}
