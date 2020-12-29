package metier;

import java.sql.Date;


public class MCommande {
    public int idCommande;
    public int idClient;
    public int idProduit;
    public Date dateCommande;
    public int quantite;
    public Float tarifUnitaire;

    public MCommande(int idCommande, int idClient, int idProduit, Date dateCommande, int quantite, Float tarifUnitaire) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idProduit = idProduit;
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.tarifUnitaire = tarifUnitaire;
    }





    public MCommande(int idCommande,Date dateCommande,int idClient) {
        this.setId(idCommande);
        this.setDateCommande1(dateCommande);
        this.setIdClient(idClient);
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

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Float getTarifUnitaire() {
        return tarifUnitaire;
    }

    public void setTarifUnitaire(Float tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    public MCommande(int id) {
        this.setId(id);
    }



    public MCommande(int idCommande, int idProduit, int idClient, String dateCommande, int quantite, Float tarifUnitaire) {
    }

    public MCommande(int idCommande, String s, int idClient) {
    }


    public int getId() {
        return idCommande;
    }

    public void setId(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDateCommande() {
        return dateCommande;
    }


    public void setDateCommande1(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    public void setDateCommande2(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    public void setDateCommande3(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MCommande other = (MCommande) obj;
        if (dateCommande == null) {
            if (other.dateCommande != null)
                return false;
        } else if (!dateCommande.equals(other.dateCommande))
            return false;
        if (idClient != other.idClient)
            return false;
        if (idCommande != other.idCommande)
            return false;
        return true;
    }


}

