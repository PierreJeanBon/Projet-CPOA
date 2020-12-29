package metier;

import java.util.Scanner;

public class MProduit {

    public int idProduit;
    public String nom;
    public String description;
    public float tarif;
    public String visuel;
    public int idCategorie;

    public MProduit(Scanner sc) {
        System.out.println("Id du produit ?");
        this.idProduit = sc.nextInt();
        sc.nextLine();
        System.out.println("titre du produit ?");
        this.nom = sc.nextLine();
        System.out.println("description du produit ?");
        this.description = sc.nextLine();
        System.out.println("tarif du produit ?");
        this.tarif =sc.nextInt();
        sc.nextLine();
        System.out.println("nom de l'image du produit ?");
        this.visuel = sc.nextLine();
        System.out.println("Identifiant de la categorie ?");
        this.idCategorie = sc.nextInt();
        sc.nextLine();
        System.out.println("\n");
    }

    public MProduit(Scanner sc, int idProduit) {
        this.idProduit = idProduit;
        System.out.println("Id du produit ?");
        this.idProduit = sc.nextInt();
        sc.nextLine();
        System.out.println("titre du produit ?");
        this.nom = sc.nextLine();
        System.out.println("description du produit ?");
        this.description = sc.nextLine();
        System.out.println("tarif du produit ?");
        this.tarif =sc.nextInt();
        sc.nextLine();
        System.out.println("nom de l'image du produit ?");
        this.visuel = sc.nextLine();
        System.out.println("Identifiant de la categorie ?");
        this.idCategorie = sc.nextInt();
        sc.nextLine();
        System.out.println("\n");
    }

    public MProduit(int idProduit, String nom, String description, float tarif, String visuel, int idCategorie) {
        super();
        this.idProduit = idProduit;
        this.nom = nom;
        this.description = description;
        this.tarif = tarif;
        this.visuel = visuel;
        this.idCategorie = idCategorie;
    }
    public MProduit(String nom, String description, float tarif, String visuel, int idCategorie) {
        super();
        this.setIdProduit(-1);
        this.description = description;
        this.tarif = tarif;
        this.tarif = tarif;
        this.visuel = visuel;
        this.idCategorie = idCategorie;
    }

    public MProduit(String nom, String description, Float tarif, String visuel, MCategorie byTitre) {
    }

    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getTarif() {
        return tarif;
    }
    public void setTarif(float tarif) {
        this.tarif = tarif;
    }
    public String getVisuel() {
        return visuel;
    }
    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;
        MProduit pr = (MProduit) obj;
        return this.getIdProduit() == pr.getIdProduit();

    }

}

