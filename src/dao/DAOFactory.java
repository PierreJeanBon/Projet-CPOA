package dao;


public abstract class DAOFactory {

    public enum Persistance{MYSQL, ListeMemoire}


    public static DAOFactory getDAOFactory(Persistance cible) {
        DAOFactory daoF = null;
        switch (cible) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case  ListeMemoire:
                daoF = new ListeMemoireDAOFactory();
                break;
        }

        return daoF;
    }

    public abstract CategorieDAO getCategorieDAO();
    public abstract ClientDAO getClientDAO();
    public abstract CommandeDAO getCommandeDAOO();
    public abstract LigneCommandeDAO getLigneCommandeDAO();
    public abstract ProduitDAO getProduitDAO();




}
