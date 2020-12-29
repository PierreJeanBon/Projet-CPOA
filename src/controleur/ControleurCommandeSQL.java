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
import java.util.ResourceBundle;

public class ControleurCommandeSQL implements Initializable {


    //page ChoixLM
    @FXML private  Button versGestionClientLM, versGestionProduitLM, versGestionCommandeLM, versGestionCategorieLM, versSQLouListe, quitter;


    //page ChoixSQL
    @FXML private  Button versGestionClientSQL, versGestionProduitSQL, versGestionCommandeSQL, versGestionCategorieSQL;


    //page SqlOuListe
    @FXML private Button btnListeMemoire, btnSQL;


    //page GestionCommandeSQL
    @FXML private TextField txtIdCommmandeSQL, txtIdProduitSQL, txtIdClientSQL, txtDateCommmandeSQL, txtQuantiteCommmandeSQL, txtTarifUnitaireCommmandeSQL;
    @FXML private Button ajouterCommandeSQL, modifierCommandeSQL, supprimerCommandeSQL;
    @FXML private ChoiceBox<Integer> listeCommandeSQL;
    @FXML private TableView<MCommande> tableCommandeSQL;
    @FXML private TableColumn <MCommande, String> listeIdClientCommandeSQL, listeDateCommandeSQL, listeTarifUnitaireCommandeSQL;
    @FXML private TableColumn <MCommande, Integer> listeIdCommandeSQL, listeIdProduitSQL, listeQuantiteCommandeSQL;


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

    //Si un Champ n'est pas complété dans la page CommandeSQL
    public boolean errorCommandeSQL() {
        String erreur="";
        if(txtIdCommmandeSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id de la commande \n";
        }
        if(txtIdProduitSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id du produit \n";
        }
        if(txtIdClientSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir l'id du client \n";
        }
        if(txtDateCommmandeSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la date de la commande \n";
        }
        if(txtQuantiteCommmandeSQL.getText().trim().isEmpty()) {
            erreur+=" Veuillez saisir la quantité \n";
        }
        if(txtTarifUnitaireCommmandeSQL.getText().trim().isEmpty()) {
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
    @FXML void onClickAjouterCommandeSQL() throws Exception {
        creerCommandeSQL();
        createCommandeViewSQL(txtIdCommmandeSQL, txtIdProduitSQL, txtIdClientSQL, txtDateCommmandeSQL, txtQuantiteCommmandeSQL, txtTarifUnitaireCommmandeSQL);
    }

    //****************************************** VIDAGE DES CHAMPS ******************************************

    //Vidage champ Gestion Commande SQL
    public void createCommandeViewSQL(javafx.scene.control.TextField txtIdCommandeSQL, TextField txtIdProduitSQL, TextField txtIdClientSQL, TextField txtDateCommandeSQL, TextField txtQuantiteCommandeSQL, TextField txtTarifUnitaireCommandeSQL){
        txtIdCommandeSQL.setText("");
        txtIdProduitSQL.setText("");
        txtIdClientSQL.setText("");
        txtDateCommandeSQL.setText("");
        txtQuantiteCommandeSQL.setText("");
        txtTarifUnitaireCommandeSQL.setText("");
    }


    //****************************************** Méthodes de creation en SQL et LM ******************************************

    //Création d'une CommandeSQL
    public void creerCommandeSQL() throws Exception{
        if(!errorCommandeSQL()) {
            DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

            int  id_commande = Integer.parseInt(txtIdCommmandeSQL.getText());
            int  id_produit = Integer.parseInt(txtIdProduitSQL.getText());
            int  id_client = Integer.parseInt(txtIdClientSQL.getText());
            String date_commande = txtDateCommmandeSQL.getText();
            int  quantite = Integer.parseInt(txtQuantiteCommmandeSQL.getText());
            Float  tarif_unitaire = Float.parseFloat(txtTarifUnitaireCommmandeSQL.getText());

            MCommande c = new MCommande(id_commande, id_produit, id_client, date_commande, quantite, tarif_unitaire);
            daos.getCommandeDAOO().create(c);

        }
    }

    //****************************************** Affichage dans les colonnes des tables et la choixebox  ******************************************

    private ObservableList<MCommande> MCo;
    DAOFactory daos =DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL);

    //Afficher Commande SQL
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(int i=0;i<DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCommandeDAOO().findAll().size();i++) {
                listeCommandeSQL.getItems().add(DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCommandeDAOO().findAll().get(i).getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MCo= FXCollections.observableArrayList(daos.getCommandeDAOO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilteredList<MCommande> filteredData= new FilteredList<>(MCo, p->true);

        SortedList<MCommande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCommandeSQL.comparatorProperty());
        this.tableCommandeSQL.setItems(sortedData);

        listeIdCommandeSQL.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        listeIdProduitSQL.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        listeIdClientCommandeSQL.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        listeDateCommandeSQL.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        listeQuantiteCommandeSQL.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        listeTarifUnitaireCommandeSQL.setCellValueFactory(new PropertyValueFactory<>("tarifUnitaire"));
        this.tableCommandeSQL.getSortOrder().add(listeIdCommandeSQL);

        ajouterCommandeSQL.setDisable(true);
        modifierCommandeSQL.setDisable(true);
        supprimerCommandeSQL.setDisable(true);

    }


    //****************************************** Désactiver les boutons  ******************************************
    public void clickTableCommandeSQL() {
        ajouterCommandeSQL.setDisable(true);
        modifierCommandeSQL.setDisable(false);
        supprimerCommandeSQL.setDisable(false);
    }
    public void clickTextCommandeSQL() {
        ajouterCommandeSQL.setDisable(false);
        modifierCommandeSQL.setDisable(true);
        supprimerCommandeSQL.setDisable(true);
    }
    public void clickRienCommandeSQL() {
        ajouterCommandeSQL.setDisable(true);
        modifierCommandeSQL.setDisable(true);
        supprimerCommandeSQL.setDisable(true);
        this.tableCommandeSQL.getSelectionModel().clearSelection();
    }

    //****************************************** Suppression  ******************************************

    //Supprimer CommandeSQL
    public void onClickSupprimerCommandeSQL() {
        MCommande pro= this.tableCommandeSQL.getSelectionModel().getSelectedItem();
        try {
            MCo.remove(pro);
            DAOFactory.getDAOFactory(DAOFactory.Persistance.MYSQL).getCommandeDAOO().delete(pro);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }



    //****************************************** Recherche  ******************************************

    //****************************************** Modification  ******************************************
}
