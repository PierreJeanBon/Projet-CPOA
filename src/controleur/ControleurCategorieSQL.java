package controleur;

import dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import metier.MCategorie;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurCategorieSQL implements Initializable {


    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;

    //pageGestionCategorieSQL
    @FXML private TextField txtTitreCategorieSQL, txtVisuelCategorieSQL;
    @FXML private Button ajouterCategorieSQL, modifierCategorieSQL, supprimerCategorieSQL;
    @FXML private TableView<MCategorie> tableCategorieSQL;
    @FXML private TableColumn <MCategorie, String> listeTitreCategorieSQL, listeVisuelCategorieSQL;

    //****************************************** Chargement de la page retour  ******************************************
    public void onClickRetourSQL() {
        try {
            URL fxmlURL = getClass().getResource("/vue/choixListeMemoire.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Stage gestionLM = new Stage();
            Scene scene = new Scene((AnchorPane) root, 600, 400);
            gestionLM.setScene(scene);
            gestionLM.setTitle("Accueil Liste Mémoire");
            gestionLM.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    //****************************************** MESSAGE D'ERREUR ******************************************
    //si un champ n'est pas complété dans la page CategorieSQL
    public boolean errorCategorieSQL() {
        String erreur="";
        if(txtTitreCategorieSQL.getText().trim().isEmpty()) {
            erreur+="Veuillez saisir le titre \n";
        }
        if(txtVisuelCategorieSQL.getText().trim().isEmpty()) {
            erreur+="Veuiiez saisir le visuel \n";
        }

        if(!erreur.equals("")) {
            Alert alert=new Alert(Alert.AlertType.ERROR,erreur,ButtonType.OK);
            alert.showAndWait();
            return true;
        }
        return false;
    }

    //****************************************** Creation en cliquant sur le bouton ******************************************


    //Creation d'une Categorie
    @FXML void onClickAjouterCategorieSQL() throws Exception {
        creerCategorieSQL();
        createCategorieViewSQL(txtTitreCategorieSQL, txtVisuelCategorieSQL);
    }

    //****************************************** VIDAGE DES CHAMPS ******************************************

    //Vidage Champ Gestion Categorie SQL
    public void createCategorieViewSQL(javafx.scene.control.TextField txtFieldCategorieSQL, TextField txtVisuelCategorieSQL){
        txtFieldCategorieSQL.setText("");
        txtVisuelCategorieSQL.setText("");
    }

    //****************************************** Méthodes de creation en SQL ******************************************

    //Création d'une CategorieSQL
    public void creerCategorieSQL() throws Exception{
        if(!errorCategorieSQL()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

            String titre = txtTitreCategorieSQL.getText();
            String visuel = txtVisuelCategorieSQL.getText();

            MCategorie c = new MCategorie(titre, visuel);
            daos.getCategorieDAO().create(c);

        }
    }

    //****************************************** Affichage dans les colonnes des tables  ******************************************

    private ObservableList<MCategorie> MC;
    DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

    //Afficher Categorie SQL
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MC= FXCollections.observableArrayList(daos.getCategorieDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MCategorie> filteredData= new FilteredList<>(MC, p->true);

        SortedList<MCategorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCategorieSQL.comparatorProperty());
        this.tableCategorieSQL.setItems(sortedData);

        listeTitreCategorieSQL.setCellValueFactory(new PropertyValueFactory<>("titre"));
        listeVisuelCategorieSQL.setCellValueFactory(new PropertyValueFactory<>("visuel"));

        ajouterCategorieSQL.setDisable(true);
        modifierCategorieSQL.setDisable(true);
        supprimerCategorieSQL.setDisable(true);
    }
    //****************************************** Deésactiver les boutons  ******************************************
    public void clickTableCategorieSQL() {
        ajouterCategorieSQL.setDisable(true);
        modifierCategorieSQL.setDisable(false);
        supprimerCategorieSQL.setDisable(false);
    }
    public void clickTextCategorieSQL() {
        ajouterCategorieSQL.setDisable(false);
        modifierCategorieSQL.setDisable(true);
        supprimerCategorieSQL.setDisable(true);
    }
    public void clickRienCategorieSQL() {
        ajouterCategorieSQL.setDisable(true);
        modifierCategorieSQL.setDisable(true);
        supprimerCategorieSQL.setDisable(true);
        this.tableCategorieSQL.getSelectionModel().clearSelection();
    }


    //****************************************** Suppression  ******************************************

    //Supprimer CategorieSQL
    public void onClickSupprimerCategorieSQL() {
        MCategorie pro= this.tableCategorieSQL.getSelectionModel().getSelectedItem();
        try {
            MC.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCategorieDAO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //****************************************** Recherche  ******************************************
    //****************************************** Modification  ******************************************

}
