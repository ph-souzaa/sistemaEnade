package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.CursoController;
import view.cursos.CursoHomeGUI;

public class HomeGUI {
  private JFrame frame;
  private CursoController cursoController;

  public HomeGUI(CursoController cursoController) {
    this.cursoController = cursoController;
    frame = new JFrame("Home");
    createAndShowGUI();
  }

  private void createAndShowGUI() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Define o tamanho da janela
    int largura = 800;
    int altura = 600;
    frame.setSize(largura, altura);

    // Obtém as dimensões da tela
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    // Calcula a posição centralizada da janela
    int posX = (screenWidth - largura) / 2;
    int posY = (screenHeight - altura) / 2;

    // Define a posição da janela
    frame.setLocation(posX, posY);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    JButton buttonGoToCursos = new JButton("Cursos");
    buttonGoToCursos.setPreferredSize(new Dimension(200, 100));
    buttonGoToCursos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        openCursoHomeGUI();
      }
    });

    panel.add(buttonGoToCursos);

    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  private void openCursoHomeGUI() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        CursoHomeGUI cursoHomeGUI = new CursoHomeGUI(cursoController);
        cursoHomeGUI.showGUI(); // Método "showGUI()" para exibir a interface CursoHomeGUI
      }
    });
  }
}
