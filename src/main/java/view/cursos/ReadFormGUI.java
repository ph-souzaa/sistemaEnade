package view.cursos;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import controller.CursoController;
import model.Curso;

public class ReadFormGUI {
  private JFrame frame;
  private CursoController cursoController;
  private JTextArea textAreaCursos;

  public ReadFormGUI(CursoController cursoController) {
    this.cursoController = cursoController;
    frame = new JFrame("Lista de Cursos");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    int largura = 400;
    int altura = 300;

    frame.setSize(largura, altura); // Definir tamanho desejado da janela

    // Obtém as dimensões da tela
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    // Calcula a posição centralizada da janela
    int posX = (screenWidth - largura) / 2;
    int posY = (screenHeight - altura) / 2;

    // Define a posição da janela
    frame.setLocation(posX, posY);

    textAreaCursos = new JTextArea();
    textAreaCursos.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textAreaCursos);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    try {
      List<Curso> cursos = cursoController.getAllCursos();
      StringBuilder sb = new StringBuilder();
      for (Curso curso : cursos) {
        sb.append("ID: ").append(curso.getCodigo()).append(", Nome: ").append(curso.getNome()).append("\n");
      }
      textAreaCursos.setText(sb.toString());
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(frame, "Erro ao obter a lista de cursos: " + ex.getMessage(), "Erro",
          JOptionPane.ERROR_MESSAGE);
    }

    frame.setVisible(true);
  }
}
