package SQL;
import java.sql.*;

public class Connexion {

    public static Connection creeConnexion() {
        String url =
                "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/demuru1u_cpoa2020";
        String login = "demuru1u_appli";
        String pwd = "Derepskr123";
        Connection maConnexion = null;

        try {
            maConnexion = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion" + sqle.getMessage());
        }
        return maConnexion;}


    private static Connexion instance;
    private Connexion() {}
    public static Connexion getInstance() {
        if (instance==null) {
            instance = new Connexion();
        }
        return instance;
    }

}

