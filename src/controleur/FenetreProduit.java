package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import application.Produit;

public class FenetreProduit {

    @FXML
    private ChoiceBox<String> categ;

    @FXML
    private Label affiche;


    @FXML
    private TextField nom;

    @FXML
    private TextField tarif;

    @FXML
    private TextArea description;



    public void initialize() {
        categ.getItems().add("pull");
        categ.getItems().add("casquette");
        categ.getItems().add("jean");
    }

    public void onclickcree()
    {
        String name = nom.getText();
        String descrip = description.getText();
        String catego = categ.selectionModelProperty().getName();
        double tarif=0;
        Produit p;
        try {
            tarif = Double.parseDouble(this.tarif.getText());
            try {
                p = new Produit(name,descrip, tarif, catego);
                affiche.setText(p.toString());
                nom.setText("");
                description.setText("");
            }
            catch (IllegalArgumentException A) {
                affiche.setText(A.getMessage());
            }
        }
        catch (NumberFormatException B){
            affiche.setText("Veuillez saisir un montant");
        }

    }

}

