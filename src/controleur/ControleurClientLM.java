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

public class ControleurClientLM implements Initializable {


    //page GestionClientLM
    @FXML private TextField txtNomClientLM, txtPrenomClientLM, txtVilleClientLM, rechercheClientLM, txtIdentifiantClientLM, txtMdpClientLM, txtNoAdrClientLM, txtVoieAdrClientLM, txtCodePostalClientLM, txtPaysClientLM;
    @FXML private Button ajouterClientLM, rechercherClientLM, modifierClientLM, supprimerClientLM, retourChoixLM;
    @FXML private TableView<MClient> tableClientLM;
    @FXML private TableColumn <MClient, String> listeNomClientLM, listePrenomClientLM, listeVilleClientLM, listeIdClientLM, listeVoieAdrClientLM,  listeMdpClientLM, listePaysClientLM;
    @FXML private TableColumn <MClient, Integer>listeCodePostalClientLM, listeNoAdrClientLM;


    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;
    //****************************************** Chargement de la page retour  ******************************************
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

    //Si un champ n'est pas complété dans la page ClientLM
    public boolean errorClientLM() {
        String erreur="";
        if(txtNomClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le nom \n";
        }
        if(txtPrenomClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le prenom \n";
        }
        if(txtIdentifiantClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le identifiant \n";
        }
        if(txtMdpClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le mot de passe \n";
        }
        if(txtNoAdrClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le Numéro de l'adresse \n";
        }
        if(txtVoieAdrClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la Voie d'adresse \n";
        }
        if(txtCodePostalClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le Code Postal \n";
        }
        if(txtVilleClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le ville \n";
        }
        if(txtPaysClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le pays \n";
        }
        if(!erreur.equals("")) {
            Alert alert=new Alert(Alert.AlertType.ERROR,erreur,ButtonType.OK);
            alert.showAndWait();
            return true;
        }
        return false;
    }


    //****************************************** Creation en cliquant sur le bouton ******************************************


    //Creation d'un client
    @FXML void onClickAjouterClientLM() throws Exception {
        creerClientLM();
        createClientViewLM(txtNomClientLM, txtPrenomClientLM, txtIdentifiantClientLM, txtMdpClientLM, txtNoAdrClientLM, txtVoieAdrClientLM, txtCodePostalClientLM,txtVilleClientLM, txtPaysClientLM);
    }


    //****************************************** VIDAGE DES CHAMPS ******************************************

    //vidage des champs du client
    public void createClientViewLM(javafx.scene.control.TextField txtNomClientLM, TextField txtPrenomClientLM, TextField txtIdentifiantClientLM,TextField txtMdpClientLM, TextField txtNoAdrClientLM, TextField txtVoieAdrClientLM, TextField txtCodePostalClientLM, TextField txtVilleClientLM, TextField txtPaysClientLM) {
        txtNomClientLM.setText("");
        txtPrenomClientLM.setText("");
        txtIdentifiantClientLM.setText("");
        txtMdpClientLM.setText("");
        txtNoAdrClientLM.setText("");
        txtVoieAdrClientLM.setText("");
        txtCodePostalClientLM.setText("");
        txtVilleClientLM.setText("");
        txtPaysClientLM.setText("");

    }
    //****************************************** Méthodes de creation en LM ******************************************

    public void creerClientLM() throws Exception{
        if(!errorClientLM()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

            String nom = txtNomClientLM.getText();
            String prenom = txtPrenomClientLM.getText();
            String identifiant = txtIdentifiantClientLM.getText();
            String mdp = txtMdpClientLM.getText();
            int  adrNumero = Integer.parseInt(txtNoAdrClientLM.getText());
            String adrVoie = txtNoAdrClientLM.getText();
            int adrPostale = Integer.parseInt(txtCodePostalClientLM.getText());
            String adrVille = txtVilleClientLM.getText();
            String adrPays = txtPaysClientLM.getText();

            MClient c = new MClient(nom, prenom, identifiant, mdp, adrNumero, adrVoie, adrPostale, adrVille, adrPays);
            daos.getClientDAO().create(c);

        }
    }

    //****************************************** Affichage dans les colonnes des tables  ******************************************

    private ObservableList<MClient> MCl;
    DAOFactory daof =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

    //****************************************** Suppression  ******************************************

    //Supprimer ClientListeMemoire
    public void onClickSupprimerClientLM() {
        MClient pro= this.tableClientLM.getSelectionModel().getSelectedItem();
        try {
            MCl.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getClientDAO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    //****************************************** Affichage dans les colonnes des tables  ******************************************
    //Affichage Client ListeMemoire
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MCl= FXCollections.observableArrayList(daof.getClientDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MClient> filteredData= new FilteredList<>(MCl, p->true);

        SortedList<MClient> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableClientLM.comparatorProperty());
        this.tableClientLM.setItems(sortedData);

        listeNomClientLM.setCellValueFactory(new PropertyValueFactory<>("nom"));
        listePrenomClientLM.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        listeIdClientLM.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        listeMdpClientLM.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        listeNoAdrClientLM.setCellValueFactory(new PropertyValueFactory<>("adrNumero"));
        listeVoieAdrClientLM.setCellValueFactory(new PropertyValueFactory<>("adrVoie"));
        listeCodePostalClientLM.setCellValueFactory(new PropertyValueFactory<>("adrPostale"));
        listeVilleClientLM.setCellValueFactory(new PropertyValueFactory<>("adrVille"));
        listePaysClientLM.setCellValueFactory(new PropertyValueFactory<>("adrPays"));
        this.tableClientLM.getSortOrder().add(listeIdClientLM);

        ajouterClientLM.setDisable(true);
        modifierClientLM.setDisable(true);
        supprimerClientLM.setDisable(true);
        rechercherClientLM.setDisable(true);

    }
    //****************************************** Deésactiver les boutons  ******************************************
    public void clickTableClientLM() {
        ajouterClientLM.setDisable(true);
        modifierClientLM.setDisable(false);
        supprimerClientLM.setDisable(false);
        rechercherClientLM.setDisable(true);
    }
    public void clickTextClientLM() {
        ajouterClientLM.setDisable(false);
        modifierClientLM.setDisable(true);
        supprimerClientLM.setDisable(true);
        rechercherClientLM.setDisable(true);
    }
    public void clickRienClientLM() {
        ajouterClientLM.setDisable(true);
        modifierClientLM.setDisable(true);
        supprimerClientLM.setDisable(true);
        rechercherClientLM.setDisable(true);
        this.tableClientLM.getSelectionModel().clearSelection();
    }
    public void clickRechercheClientLM() {
        ajouterClientLM.setDisable(true);
        modifierClientLM.setDisable(true);
        supprimerClientLM.setDisable(true);
        rechercherClientLM.setDisable(false);

    }

    //****************************************** Recherche  ******************************************
    //****************************************** Modification  ******************************************

}
