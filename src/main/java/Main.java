import javax.swing.SwingUtilities;
import controller.CursoController;
import view.HomeGUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {

            final CursoController cursoController = new CursoController();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new HomeGUI(cursoController);
                }
            });
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
