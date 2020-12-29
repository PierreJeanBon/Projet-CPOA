package metier;

import java.util.Scanner;

import metier.MClient;

public class MClient {

    public int idClient;
    public String nom;
    public String prenom;
    public String identifiant;
    public String mdp;
    public int adrNumero;
    public String adrVoie;
    public int adrPostale;
    public String adrVille;
    public String adrPays;


    public MClient(int idClient, String nom, String prenom, String identifiant, String mdp, int adrNumero,
                   String adrVoie,int adrPostale, String adrVille, String adrPays) {

        this.idClient = idClient;
        this.adrNumero = adrNumero;
        this.adrPostale = adrPostale;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.adrVoie = adrVoie;
        this.adrVille = adrVille;
        this.adrPays = adrPays;
    }

    public MClient(Scanner sc) {
        System.out.println("Le nom du client ?");
        this.nom = sc.nextLine();
        System.out.println("Le prenom du client ?");
        this.prenom = sc.nextLine();
        System.out.println("l'identifiant du client ?");
        this.identifiant = sc.nextLine();
        System.out.println("Le mot de passe du client ?");
        this.mdp = sc.nextLine();
        System.out.println("L'adresse du client ?");
        this.adrNumero = sc.nextInt();
        sc.nextLine();
        System.out.println("La rue du client ?");
        this.adrVoie = sc.nextLine();
        System.out.println("Le code postal du client ?");
        this.adrPostale = sc.nextInt();
        sc.nextLine();
        System.out.println("La ville du client ?");
        this.adrVille = sc.nextLine();
        System.out.println("Le pays du client ?");
        this.adrPays = sc.nextLine();

    }


    public MClient(int idClient) {
        super();
        this.idClient=idClient;
    }

    public MClient(String nom, String prenom, String identifiant, String mdp, int adrNumero, String adrVoie, int adrPostale, String adrVille, String adrPays) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.adrNumero = adrNumero;
        this.adrVoie = adrVoie;
        this.adrPostale = adrPostale;
        this.adrVille = adrVille;
        this.adrPays = adrPays;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(String identifiant2) {
        this.identifiant = identifiant2;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public int getAdrNumero() {
        return adrNumero;
    }
    public void setAdrNumero(int adrNumero) {
        this.adrNumero = adrNumero;
    }
    public String getAdrVoie() {
        return adrVoie;
    }
    public void setAdrVoie(String adrVoie) {
        this.adrVoie = adrVoie;
    }
    public int getAdrPostale() {
        return adrPostale;
    }
    public void setAdrPostale(int adrPostale) {
        this.adrPostale = adrPostale;
    }
    public String getAdrVille() {
        return adrVille;
    }
    public void setAdrVille(String adrVille) {
        this.adrVille = adrVille;
    }
    public String getAdrPays() {
        return adrPays;
    }
    public void setAdrPays(String adrPays) {
        this.adrPays = adrPays;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MClient other = (MClient) obj;
        if (adrPostale != other.adrPostale)
            return false;
        if (adrNumero != other.adrNumero)
            return false;
        if (adrPays == null) {
            if (other.adrPays != null)
                return false;
        } else if (!adrPays.equals(other.adrPays))
            return false;
        if (adrVille == null) {
            if (other.adrVille != null)
                return false;
        } else if (!adrVille.equals(other.adrVille))
            return false;
        if (adrVoie == null) {
            if (other.adrVoie != null)
                return false;
        } else if (!adrVoie.equals(other.adrVoie))
            return false;
        if (idClient != other.idClient)
            return false;
        if (identifiant == null) {
            if (other.identifiant != null)
                return false;
        } else if (!identifiant.equals(other.identifiant))
            return false;
        if (mdp == null) {
            if (other.mdp != null)
                return false;
        } else if (!mdp.equals(other.mdp))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }



}