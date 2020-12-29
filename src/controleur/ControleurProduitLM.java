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
import metier.MProduit;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurProduitLM implements Initializable {


    //page GestionProduitLM
    @FXML private Button ajouterProduitLM, modifierProduitLM,  supprimerProduitLM, retourProduitLM, rechercherProduitLM;
    @FXML private TextField  nomProduitLM, descriptionProduitLM, tarifProduitLM, rechercheProduitLM;
    @FXML private ChoiceBox<String> categorieProduitLM;
    @FXML private TableView<MProduit> tableProduitLM;
    @FXML private TableColumn <MProduit, String> listeNomLM, listeDescriptionLM, listeVisuelLM;
    @FXML private TableColumn <MProduit, Float>listeTarifLM;
    @FXML private TableColumn <MProduit, Integer>listeIdCategorieLM;


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
    //Si un Champ n'est pas complété dans la page ProduitLM
    public boolean errorProduitLM() {
        String erreur="";
        if(nomProduitLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le nom du produit \n";
        }
        if(descriptionProduitLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la description du produit \n";
        }
        if(tarifProduitLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le tarif du produit \n";
        }
        if(!erreur.equals("")) {
            Alert alert=new Alert(Alert.AlertType.ERROR,erreur,ButtonType.OK);
            alert.showAndWait();
            return true;
        }
        return false;
    }



    //****************************************** Creation en cliquant sur le bouton ******************************************

    @FXML void onClickAjouterProduitLM() throws Exception {
        creerProduitLM();
        createProduitViewLM(nomProduitLM, descriptionProduitLM, tarifProduitLM);
    }

    //****************************************** VIDAGE DES CHAMPS ******************************************
    //Vidage champ Gestion Produit ListeMemoire
    public void createProduitViewLM(TextField nomProduitLM, TextField descriptionProduitLM, TextField tarifProduitLM){
        nomProduitLM.setText("");
        descriptionProduitLM.setText("");
        tarifProduitLM.setText("");
    }

    //****************************************** Méthodes de creation en LM ******************************************
    //Création d'un ProduitListeMemoire
    public void creerProduitLM() throws Exception{
        if(!errorProduitLM()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

            String nom = listeNomLM.getText();
            String description = listeDescriptionLM.getText();
            Float  tarif = Float.parseFloat(listeTarifLM.getText());
            String visuel = listeVisuelLM.getText();

            MCategorie p = new MCategorie(nom, description, tarif, visuel, daos.getCategorieDAO().getByTitre(categorieProduitLM.getSelectionModel().getSelectedItem()));
            daos.getCategorieDAO().create(p);

        }
    }

    //****************************************** Affichage dans les colonnes des tables et la choixebox  ******************************************
    // Affichage des Produit ListeMemoire
    private ObservableList<MProduit> MP;
    DAOFactory daof =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(int i=0;i<DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCategorieDAO().findAll().size();i++) {
                categorieProduitLM.getItems().add(DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCategorieDAO().findAll().get(i).getTitre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MP= FXCollections.observableArrayList(daof.getProduitDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MProduit> filteredData= new FilteredList<>(MP, p->true);

        SortedList<MProduit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduitLM.comparatorProperty());
        this.tableProduitLM.setItems(sortedData);

        listeNomLM.setCellValueFactory(new PropertyValueFactory<>("nom"));
        listeDescriptionLM.setCellValueFactory(new PropertyValueFactory<>("description"));
        listeTarifLM.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        listeVisuelLM.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        listeIdCategorieLM.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        this.tableProduitLM.getSortOrder().add(listeIdCategorieLM);

        ajouterProduitLM.setDisable(true);
        modifierProduitLM.setDisable(true);
        supprimerProduitLM.setDisable(true);
        rechercherProduitLM.setDisable(true);

    }

    //****************************************** Désactiver les boutons  ******************************************
    public void clickTableProduitLM() {
        ajouterProduitLM.setDisable(true);
        modifierProduitLM.setDisable(false);
        supprimerProduitLM.setDisable(false);
        rechercherProduitLM.setDisable(true);
    }
    public void clickTextProduitLM() {
        ajouterProduitLM.setDisable(false);
        modifierProduitLM.setDisable(true);
        supprimerProduitLM.setDisable(true);
        rechercherProduitLM.setDisable(true);
    }
    public void clickRienProduitLM() {
        ajouterProduitLM.setDisable(true);
        modifierProduitLM.setDisable(true);
        supprimerProduitLM.setDisable(true);
        rechercherProduitLM.setDisable(true);
        this.tableProduitLM.getSelectionModel().clearSelection();
    }
    public void clickRechercheProduitLM() {
        ajouterProduitLM.setDisable(true);
        modifierProduitLM.setDisable(true);
        supprimerProduitLM.setDisable(true);
        rechercherProduitLM.setDisable(false);
    }

    //****************************************** Suppression  ******************************************
    //Supprimer ProduitListeMemoire
    public void onClickSupprimerProduitLM() {
        MProduit pro= this.tableProduitLM.getSelectionModel().getSelectedItem();
        try {
            MP.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getProduitDAO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //****************************************** Recherche  ******************************************

        //****************************************** Modification  *****************************************
    }

}

