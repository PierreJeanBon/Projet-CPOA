package metier;

import java.util.Scanner;

public class MLigneCommande {

    public int idCommande;
    public int idProduit;
    public int quantite;
    public double tarifUnitaire;

    public MLigneCommande(int idCommande, int idProduit, int quantite, double tarifUnitaire) {

        this.setIdCommande(idCommande);
        this.setIdProduit(idProduit);
        this.setQuantite(quantite);
        this.setTarifUnitaire(tarifUnitaire);
    }
    public MLigneCommande(int idProduit, int quantite, double tarifUnitaire) {

        this.setIdCommande(-1);
        this.setIdProduit(idProduit);
        this.setQuantite(quantite);
        this.setTarifUnitaire(tarifUnitaire);
    }

    public MLigneCommande(Scanner sc) {
        System.out.println("Id de la commande ?");
        this.idCommande = sc.nextInt();
        sc.nextLine();
        System.out.println("id du produit ?");
        this.idProduit = sc.nextInt();
        sc.nextLine();
        System.out.println("quantit� du produit ?");
        this.quantite = sc.nextInt();
        sc.nextLine();
        System.out.println("tarif du produit ?");
        this.tarifUnitaire =sc.nextInt();
        sc.nextLine();
        System.out.println("\n");
    }
    public MLigneCommande(Scanner sc, int idCommande) {
        this.idCommande = idCommande;
        System.out.println("Id de la commande ?");
        this.idCommande = sc.nextInt();
        sc.nextLine();
        System.out.println("id du produit ?");
        this.idProduit = sc.nextInt();
        sc.nextLine();
        System.out.println("quantit� du produit ?");
        this.quantite = sc.nextInt();
        sc.nextLine();
        System.out.println("tarif du produit ?");
        this.tarifUnitaire =sc.nextInt();
        sc.nextLine();
        System.out.println("\n");
    }

    public MLigneCommande(int id) {
        this.setIdCommande(id);
    }
    public int getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getTarifUnitaire() {
        return tarifUnitaire;
    }
    public void setTarifUnitaire(double tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;
        MLigneCommande lc = (MLigneCommande) obj;
        return this.getIdCommande() == lc.getIdCommande();

    }
}
