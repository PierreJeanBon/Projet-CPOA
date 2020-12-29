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

public class ControleurCategorieLM implements Initializable {


    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;


    //pageGestionCategorieLM
    @FXML private TextField txtTitreCategorieLM, txtVisuelCategorieLM;
    @FXML private Button ajouterCategorieLM, modifierCategorieLM, supprimerCategorieLM;
    @FXML private TableView<MCategorie> tableCategorieLM;
    @FXML private TableColumn <MCategorie, String> listeTitreCategorieLM, listeVisuelCategorieLM;


    //****************************************** Chargement de la page retour ******************************************
    public void onClickRetourLM() {
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
    //Si un Champ n'est pas complété dans la page Categorie LM
    public boolean errorCategorieLM() {
        String erreur="";
        if(txtTitreCategorieLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le nom de la categorie \n";
        }
        if(txtVisuelCategorieLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le visuel de la categorie \n";
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
    @FXML void onClickAjouterCategorieLM() throws Exception {
        creerCategorieLM();
        createCategorieViewLM(txtTitreCategorieLM, txtVisuelCategorieLM);
    }


    //****************************************** VIDAGE DES CHAMPS ******************************************

    //Vidage Champ Gestion Categorie Liste Memoire
    public void createCategorieViewLM(javafx.scene.control.TextField txtFieldCategorieLM, TextField txtVisuelCategorieLM){
        txtFieldCategorieLM.setText("");
        txtVisuelCategorieLM.setText("");
    }

    //****************************************** Méthodes de creation en LM ******************************************

    //Création d'une CategorieListeMemoire
    public void creerCategorieLM() throws Exception{
        if(!errorCategorieLM()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

            String titre = txtTitreCategorieLM.getText();
            String visuel = txtVisuelCategorieLM.getText();

            MCategorie c = new MCategorie(titre, visuel);
            daos.getCategorieDAO().create(c);

        }
    }
    //****************************************** Affichage dans les colonnes  ******************************************
    private ObservableList<MCategorie> MC;
    DAOFactory daof =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

    //Afficher CategorieListeMemoire
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MC= FXCollections.observableArrayList(daof.getCategorieDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MCategorie> filteredData= new FilteredList<>(MC, p->true);

        SortedList<MCategorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCategorieLM.comparatorProperty());
        this.tableCategorieLM.setItems(sortedData);

        listeTitreCategorieLM.setCellValueFactory(new PropertyValueFactory<>("titre"));
        listeVisuelCategorieLM.setCellValueFactory(new PropertyValueFactory<>("visuel"));

        ajouterCategorieLM.setDisable(true);
        modifierCategorieLM.setDisable(true);
        supprimerCategorieLM.setDisable(true);
    }

    //****************************************** Désactiver les boutons  ******************************************
    public void clickTableCategorieLM() {
        ajouterCategorieLM.setDisable(true);
        modifierCategorieLM.setDisable(false);
        supprimerCategorieLM.setDisable(false);
    }
    public void clickTextCategorieLM() {
        ajouterCategorieLM.setDisable(false);
        modifierCategorieLM.setDisable(true);
        supprimerCategorieLM.setDisable(true);
    }
    public void clickRienCategorieLM() {
        ajouterCategorieLM.setDisable(true);
        modifierCategorieLM.setDisable(true);
        supprimerCategorieLM.setDisable(true);
        this.tableCategorieLM.getSelectionModel().clearSelection();
    }

    //****************************************** Suppression  ******************************************

    //Supprimer CategorieListeMemoire
    public void onClickSupprimerCategorieLM() {
        MCategorie pro= this.tableCategorieLM.getSelectionModel().getSelectedItem();
        try {
            MC.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCategorieDAO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //****************************************** Recherche  ******************************************

    //****************************************** Modification  ******************************************


}
