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
import metier.MClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurClientSQL implements Initializable {

    //page GestionClientSQL
    @FXML
    private TextField txtNomClientSQL, txtPrenomClientSQL, txtVilleClientSQL, rechercheClientSQL, txtIdentifiantClientSQL, txtMdpClientSQL, txtNoAdrClientSQL, txtVoieAdrClientSQL, txtCodePostalClientSQL, txtPaysClientSQL;
    @FXML
    private Button ajouterClientSQL, rechercherClientSQL, modifierClientSQL, supprimerClientSQL, retourChoixSQL;
    @FXML
    private TableView<MClient> tableClientSQL;
    @FXML
    private TableColumn<MClient, String> listeNomClientSQL, listePrenomClientSQL, listeVilleClientSQL, listeVoieAdrClientSQL, listeIdClientSQL, listeMdpClientSQL, listePaysClientSQL;
    @FXML
    private TableColumn<MClient, Integer> listeCodePostalClientSQL, listeNoAdrClientSQL;

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
    //si un champ n'est pas complété dans la page ClientSQL
    public boolean errorClientSQL() {
        String erreur = "";
        if (txtNomClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le nom \n";
        }
        if (txtPrenomClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le prenom \n";
        }
        if (txtIdentifiantClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le identifiant \n";
        }
        if (txtMdpClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le mot de passe \n";
        }
        if (txtNoAdrClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le Numéro de l'adresse \n";
        }
        if (txtVoieAdrClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir la Voie d'adresse \n";
        }
        if (txtCodePostalClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le Code Postal \n";
        }
        if (txtVilleClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le ville \n";
        }
        if (txtPaysClientSQL.getText().trim().isEmpty()) {
            erreur += " Veuillez saisir le pays \n";
        }
        if (!erreur.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, erreur, ButtonType.OK);
            alert.showAndWait();
            return true;
        }
        return false;
    }
    //****************************************** Creation en cliquant sur le bouton ******************************************
    //Création d'un client SQL
    @FXML
    void onClickAjouterClientSQL() throws Exception {
        creerClientSQL();
        createClientViewSQL(txtNomClientSQL, txtPrenomClientSQL, txtIdentifiantClientSQL, txtMdpClientSQL, txtNoAdrClientSQL, txtVoieAdrClientSQL, txtCodePostalClientSQL, txtVilleClientSQL, txtPaysClientSQL);
    }
    //****************************************** VIDAGE DES CHAMPS ******************************************
    //vidage des champs du client
    public void createClientViewSQL(javafx.scene.control.TextField txtNomClientSQL, TextField txtPrenomClientSQL, TextField txtIdentifiantClientSQL, TextField txtMdpClientSQL, TextField txtNoAdrClientSQL, TextField txtVoieAdrClientSQL, TextField txtCodePostalClientSQL, TextField txtVilleClientSQL, TextField txtPaysClientSQL) {
        txtNomClientSQL.setText("");
        txtPrenomClientSQL.setText("");
        txtIdentifiantClientSQL.setText("");
        txtMdpClientSQL.setText("");
        txtNoAdrClientSQL.setText("");
        txtVoieAdrClientSQL.setText("");
        txtCodePostalClientSQL.setText("");
        txtVilleClientSQL.setText("");
        txtPaysClientSQL.setText("");
    }
    //****************************************** Méthodes de creation en SQL ******************************************
    public void creerClientSQL() throws Exception {
        if (!errorClientSQL()) {
            DAOFactory daos = DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

            String nom = txtNomClientSQL.getText();
            String prenom = txtPrenomClientSQL.getText();
            String identifiant = txtIdentifiantClientSQL.getText();
            String mdp = txtMdpClientSQL.getText();
            int adrNumero = Integer.parseInt(txtNoAdrClientSQL.getText());
            String adrVoie = txtNoAdrClientSQL.getText();
            int adrPostale = Integer.parseInt(txtCodePostalClientSQL.getText());
            String adrVille = txtVilleClientSQL.getText();
            String adrPays = txtPaysClientSQL.getText();

            MClient c = new MClient(nom, prenom, identifiant, mdp, adrNumero, adrVoie, adrPostale, adrVille, adrPays);
            daos.getClientDAO().create(c);

        }
    }
    //****************************************** Affichage dans les colonnes des tables  ******************************************
    //Afficher Client SQL
    private ObservableList<MClient> MCl;
    DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);


    //****************************************** Affichage dans les colonnes des tables  ******************************************
    // Affichage ClientSQL
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MCl= FXCollections.observableArrayList(daos.getClientDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MClient> filteredData= new FilteredList<>(MCl, p->true);

        SortedList<MClient> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableClientSQL.comparatorProperty());
        this.tableClientSQL.setItems(sortedData);

        listeNomClientSQL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        listePrenomClientSQL.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        listeIdClientSQL.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        listeMdpClientSQL.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        listeNoAdrClientSQL.setCellValueFactory(new PropertyValueFactory<>("adrNumero"));
        listeVoieAdrClientSQL.setCellValueFactory(new PropertyValueFactory<>("adrVoie"));
        listeCodePostalClientSQL.setCellValueFactory(new PropertyValueFactory<>("adrPostale"));
        listeVilleClientSQL.setCellValueFactory(new PropertyValueFactory<>("adrVille"));
        listePaysClientSQL.setCellValueFactory(new PropertyValueFactory<>("adrPays"));
        this.tableClientSQL.getSortOrder().add(listeIdClientSQL);

        ajouterClientSQL.setDisable(true);
        modifierClientSQL.setDisable(true);
        supprimerClientSQL.setDisable(true);
        rechercherClientSQL.setDisable(true);

    }

    //****************************************** Deésactiver les boutons  ******************************************
    public void clickTableClientSQL() {
        ajouterClientSQL.setDisable(true);
        modifierClientSQL.setDisable(false);
        supprimerClientSQL.setDisable(false);
        rechercherClientSQL.setDisable(true);
    }
    public void clickTextClientSQL() {
        ajouterClientSQL.setDisable(false);
        modifierClientSQL.setDisable(true);
        supprimerClientSQL.setDisable(true);
        rechercherClientSQL.setDisable(true);
    }
    public void clickRienClientSQL() {
        ajouterClientSQL.setDisable(true);
        modifierClientSQL.setDisable(true);
        supprimerClientSQL.setDisable(true);
        rechercherClientSQL.setDisable(true);
        this.tableClientSQL.getSelectionModel().clearSelection();
    }
    public void clickRechercheClientSQL() {
        ajouterClientSQL.setDisable(true);
        modifierClientSQL.setDisable(true);
        supprimerClientSQL.setDisable(true);
        rechercherClientSQL.setDisable(false);
    }

    //****************************************** Suppression  ******************************************

    //Supprimer ClientSQL
    public void onClickSupprimerClientSQL() {
        MClient pro= this.tableClientSQL.getSelectionModel().getSelectedItem();
        try {
            MCl.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getClientDAO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    //****************************************** Recherche  ******************************************
    //****************************************** Modification  ******************************************

}