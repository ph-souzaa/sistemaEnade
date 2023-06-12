import controller.CursoController;
import dao.CursoDAO;
import view.CursoView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:postgresql://containers-us-west-103.railway.app:6478/sistemaenade";
        String user = "postgres";
        String password = "3fpFq9V8JdWtbphqLbcS";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Create DAO
            CursoDAO cursoDAO = new CursoDAO(conn);

            // Create controller with DAO
            CursoController cursoController = new CursoController(cursoDAO);

            // Create view with controller
            CursoView cursoView = new CursoView(cursoController);

            // Example of usage
            cursoView.createCurso();
            cursoView.displayCurso();

            // ... More actions here ...

        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.getMessage());
        }
    }
}
