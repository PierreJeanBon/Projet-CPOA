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
import metier.MCommande;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ControleurCommandeLM implements Initializable {

    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;

    //page GestionCommandeLM
    @FXML private TextField txtIdCommmandeLM, txtIdProduitLM, txtIdClientLM, txtDateCommmandeLM, txtQuantiteCommmandeLM, txtTarifUnitaireCommmandeLM;
    @FXML private Button ajouterCommandeLM, modifierCommandeLM, supprimerCommandeLM;
    @FXML private ChoiceBox listeCommandeLM;
    @FXML private TableView<MCommande> tableCommandeLM;
    @FXML private TableColumn <MCommande, String>  listeIdClientCommandeLM, listeDateCommandeLM, listeTarifUnitaireCommandeLM;
    @FXML private TableColumn <MCommande, Integer>listeIdCommandeLM, listeIdProduitLM, listeQuantiteCommandeLM;


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


    //Si un Champ n'est pas complété dans la page CommandeLM
    public boolean errorCommandeLM() {
        String erreur="";
        if(txtIdCommmandeLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id de la commande \n";
        }
        if(txtIdProduitLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id du produit \n";
        }
        if(txtIdClientLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id du client \n";
        }
        if(txtDateCommmandeLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la date de la commande \n";
        }
        if(txtQuantiteCommmandeLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la quantité \n";
        }
        if(txtTarifUnitaireCommmandeLM.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir le prix unitaire \n";
        }
        if(!erreur.equals("")) {
            Alert alert=new Alert(Alert.AlertType.ERROR,erreur,ButtonType.OK);
            alert.showAndWait();
            return true;
        }
        return false;
    }


    //****************************************** Creation en cliquant sur le bouton ******************************************


    //Creation d'une Commande
    @FXML void onClickAjouterCommandeLM() throws Exception {
        creerCommandeLM();
        createCommandeViewLM(txtIdCommmandeLM, txtIdProduitLM, txtIdClientLM, txtDateCommmandeLM, txtQuantiteCommmandeLM, txtTarifUnitaireCommmandeLM);
    }


    //****************************************** VIDAGE DES CHAMPS ******************************************

    //Vidage champ commande ListeMemoire
    public void createCommandeViewLM(javafx.scene.control.TextField txtIdCommmandeLM, TextField txtIdProduitLM, TextField txtIdClientLM, TextField txtDateCommandeLM, TextField txtQuantiteCommandeLM, TextField txtTarifUnitaireCommandeLM){
        txtIdCommmandeLM.setText("");
        txtIdProduitLM.setText("");
        txtIdClientLM.setText("");
        txtDateCommandeLM.setText("");
        txtQuantiteCommandeLM.setText("");
        txtTarifUnitaireCommandeLM.setText("");
    }

    //****************************************** Méthodes de creation en SQL et LM ******************************************

    //Création d'une Commande Liste Memoire
    public void creerCommandeLM() throws Exception{
        if(!errorCommandeLM()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

            int  idCommande = Integer.parseInt(txtIdCommmandeLM.getText());
            int  idProduit = Integer.parseInt(txtIdProduitLM.getText());
            int  idClient = Integer.parseInt(txtIdClientLM.getText());
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date dateCommande = parser.parse(txtDateCommmandeLM.getText());
            int  quantite = Integer.parseInt(txtQuantiteCommmandeLM.getText());
            Float  tarifUnitaire = Float.parseFloat(txtTarifUnitaireCommmandeLM.getText());

            MCommande c = new MCommande(idCommande, idProduit, idClient, (java.sql.Date) dateCommande, quantite, tarifUnitaire);
            daos.getCommandeDAOO().create(c);

        }
    }


    //****************************************** Désactiver les boutons  ******************************************
    public void clickTableCommandeLM() {
        ajouterCommandeLM.setDisable(true);
        modifierCommandeLM.setDisable(false);
        supprimerCommandeLM.setDisable(false);
    }
    public void clickTextCommandeLM() {
        ajouterCommandeLM.setDisable(false);
        modifierCommandeLM.setDisable(true);
        supprimerCommandeLM.setDisable(true);
    }
    public void clickRienCommandeLM() {
        ajouterCommandeLM.setDisable(true);
        modifierCommandeLM.setDisable(true);
        supprimerCommandeLM.setDisable(true);
        this.tableCommandeLM.getSelectionModel().clearSelection();
    }

    //****************************************** Suppression  ******************************************

    //Supprimer CommandeListeMemoire
    public void onClickSupprimerCommandeLM() {
        MCommande pro= this.tableCommandeLM.getSelectionModel().getSelectedItem();
        try {
            MCo.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCommandeDAOO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    //****************************************** Affichage dans les colonnes des tables et la choixebox  ******************************************

    private ObservableList<MCommande> MCo;
    DAOFactory daof =DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Afficher CommandeListeMemoire
            try {
                for(int i=0;i<DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCommandeDAOO().findAll().size();i++) {
                    listeCommandeLM.getItems().add(DAOFactory.getDAOFactory(DAOFactory.Persistance.ListeMemoire).getCommandeDAOO().findAll().get(i).getId());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                MCo= FXCollections.observableArrayList(daof.getCommandeDAOO().findAll());
            } catch (Exception e) {
                e.printStackTrace();
            }
            FilteredList<MCommande> filteredData= new FilteredList<>(MCo, p->true);

            SortedList<MCommande> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableCommandeLM.comparatorProperty());
            this.tableCommandeLM.setItems(sortedData);

            listeIdCommandeLM.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            listeIdProduitLM.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
            listeIdClientCommandeLM.setCellValueFactory(new PropertyValueFactory<>("idClient"));
            listeDateCommandeLM.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            listeQuantiteCommandeLM.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            listeTarifUnitaireCommandeLM.setCellValueFactory(new PropertyValueFactory<>("tarifUnitaire"));
            this.tableCommandeLM.getSortOrder().add(listeIdCommandeLM);

            ajouterCommandeLM.setDisable(true);
            modifierCommandeLM.setDisable(true);
            supprimerCommandeLM.setDisable(true);

        }


    //****************************************** Recherche  ******************************************

    //****************************************** Modification  *****************************************
}
