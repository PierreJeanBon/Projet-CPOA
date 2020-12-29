package metier;


public class MCategorie  {
    public int id;
    public String titre;
    public String visuel;

    public MCategorie(int id, String titre, String visuel) {
        super();
        this.setId(id);
        this.setTitre(titre);
        this.setVisuel(visuel);

    }
    public MCategorie(String titre, String visuel) {

        this.setId(-1);
        this.setTitre(titre);
        this.setVisuel(visuel);
    }
    public MCategorie(int id2) {
        this.setId(id);
    }

    public MCategorie(String nom, String description, Float tarif, String visuel, MCategorie byTitre) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MCategorie other = (MCategorie) obj;
        if (id != other.id)
            return false;
        if (titre == null) {
            if (other.titre != null)
                return false;
        } else if (!titre.equals(other.titre))
            return false;
        if (visuel == null) {
            if (other.visuel != null)
                return false;
        } else if (!visuel.equals(other.visuel))
            return false;
        return true;
    }
}



