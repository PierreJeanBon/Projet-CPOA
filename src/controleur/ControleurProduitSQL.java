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

public class ControleurProduitSQL implements Initializable {

    //page GestionProduitSQL
    @FXML private Button ajouterProduitSQL, modifierProduitSQL, supprimerProduitSQL, retourProduitSQL, rechercherProduitSQL;
    @FXML private TextField  nomProduitSQL, descriptionProduitSQL, tarifProduitSQL, rechercheProduitSQL;
    @FXML private ChoiceBox categorieProduitSQL;
    @FXML private TableView<MProduit> tableProduitSQL;
    @FXML private TableColumn <MProduit, String>listeNomSQL, listeDescriptionSQL,  listeVisuelSQL;
    @FXML private TableColumn <MProduit, Float>listeTarifSQL;
    @FXML private TableColumn <MProduit, Integer>listeIdCategorieSQL;

    //****************************************** Chargement de la page retour ******************************************
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
    //Si un Champ n'est pas complété dans la page ProduitSQL
    public boolean errorProduitSQL() {
        String erreur="";
        if(nomProduitSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le nom du produit \n";
        }
        if(descriptionProduitSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la description du produit \n";
        }
        if(tarifProduitSQL.getText().trim().isEmpty()) {
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
    //Creation d'un Produit
    @FXML void onClickAjouterProduitSQL() throws Exception {
        creerProduitSQL();
        createProduitViewSQL(nomProduitSQL, descriptionProduitSQL, tarifProduitSQL);
    }

    //****************************************** VIDAGE DES CHAMPS ******************************************
    //Vidage champ Gestion Produit SQL
    public void createProduitViewSQL(javafx.scene.control.TextField nomProduitSQL, TextField descriptionProduitSQL, TextField tarifProduitSQL){
        nomProduitSQL.setText("");
        descriptionProduitSQL.setText("");
        tarifProduitSQL.setText("");
    }


    //****************************************** Méthodes de creation en SQL ******************************************
    //Création d'un ProduitSQL
    public void creerProduitSQL() throws Exception{
        if(!errorProduitSQL()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

            String nom = listeNomSQL.getText();
            String description = listeDescriptionSQL.getText();
            Float  tarif = Float.parseFloat(listeTarifSQL.getText());
            String visuel = listeVisuelSQL.getText();

            MCategorie p = new MCategorie(nom, description, tarif, visuel, daos.getCategorieDAO().getByTitre((String) categorieProduitSQL.getSelectionModel().getSelectedItem()));
            daos.getCategorieDAO().create(p);

        }
    }

    //****************************************** Affichage dans les colonnes des tables et la choixebox  ******************************************
    //Afficher Produit SQL
    private ObservableList<MProduit> MP;
    DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(int i=0;i<DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCategorieDAO().findAll().size();i++) {
                categorieProduitSQL.getItems().add(DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCategorieDAO().findAll().get(i).getTitre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MP= FXCollections.observableArrayList(daos.getProduitDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MProduit> filteredData= new FilteredList<>(MP, p->true);

        SortedList<MProduit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduitSQL.comparatorProperty());
        this.tableProduitSQL.setItems(sortedData);

        listeNomSQL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        listeDescriptionSQL.setCellValueFactory(new PropertyValueFactory<>("description"));
        listeTarifSQL.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        listeVisuelSQL.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        listeIdCategorieSQL.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        this.tableProduitSQL.getSortOrder().add(listeIdCategorieSQL);

        ajouterProduitSQL.setDisable(true);
        modifierProduitSQL.setDisable(true);
        supprimerProduitSQL.setDisable(true);
        rechercherProduitSQL.setDisable(true);

    }
    //****************************************** Désactiver les boutons  ******************************************
    public void clickTableProduitSQL() {
        supprimerProduitSQL.setDisable(false);
        ajouterProduitSQL.setDisable(true);
        modifierProduitSQL.setDisable(false);
        rechercherProduitSQL.setDisable(true);
    }
    public void clickTextProduitSQL() {
        ajouterProduitSQL.setDisable(false);
        modifierProduitSQL.setDisable(true);
        supprimerProduitSQL.setDisable(true);
        rechercherProduitSQL.setDisable(true);
    }
    public void clickRienProduitSQL() {
        ajouterProduitSQL.setDisable(true);
        modifierProduitSQL.setDisable(true);
        supprimerProduitSQL.setDisable(true);
        rechercherProduitSQL.setDisable(true);
        this.tableProduitSQL.getSelectionModel().clearSelection();
    }
    public void clickRechercheProduitSQL() {
        ajouterProduitSQL.setDisable(true);
        modifierProduitSQL.setDisable(true);
        supprimerProduitSQL.setDisable(true);
        rechercherProduitSQL.setDisable(false);
    }

    //****************************************** Suppression  ******************************************
    public void onClickSupprimerProduitSQL() {
        MProduit pro= this.tableProduitSQL.getSelectionModel().getSelectedItem();
        try {
            MP.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getProduitDAO().delete(pro);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    //****************************************** Recherche  ******************************************

    //****************************************** Modification  *****************************************
}
