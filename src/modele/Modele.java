/*package modele;

import application.Client;
import application.Commande;
import application.Produit;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Modele {

    ArrayList<Client> tableClient;
    ArrayList<Commande> tableCommande;
    ArrayList<Produit> tableProduit;

    public Modele() {
        tableClient = new ArrayList<>();
        tableCommande = new ArrayList<>();
        tableProduit = new ArrayList<>();

        public Client creerClient(String nom, String prenom, String ville){
            Client newClient = new Client(nom, prenom, ville);
            this.tableClient.add(newClient);
            return newClient;
        }

        //Création d'un client
        public Produit creerProduit(String nom, String description, Float tarif, String categ){
            Produit newProduit = new Produit(name, surname);
            this.tableProduit.add(newProduit);
            return newProduit;

        }
            //vidage du texte juste après l'ajout d'un client pour confirmer que cela a bien été fait
            public void creerProduitVue (javafx.scene.control.TextField nomProduit, TextField
            descriptionProduit, TextField tarifProduit, ChoiceBox categorieProduit){
                txtNomProduit.setText("");
                txtDescriptionProduit.setText("");
                txtTarifProduit.setText("");

            }


    }
}
*/