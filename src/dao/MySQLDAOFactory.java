package dao;
import SQL.MySQLCategorieDAO;
import SQL.MySQLClientDAO;
import SQL.MySQLCommandeDAO;
import SQL.MySQLLigneCommandeDAO;
import SQL.MySQLProduitDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public CategorieDAO getCategorieDAO() {
        return MySQLCategorieDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return MySQLClientDAO.getInstance();
    }

    @Override
    public CommandeDAO getCommandeDAOO() {
        return MySQLCommandeDAO.getInstance();
    }

    @Override
    public LigneCommandeDAO getLigneCommandeDAO() {
        return MySQLLigneCommandeDAO.getInstance();
    }

    @Override
    public ProduitDAO getProduitDAO() {
        return MySQLProduitDAO.getInstance();
    }


}
