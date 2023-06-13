package view.cursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controller.CursoController;

public class DeleteFormGUI {
  private JFrame frame;
  private CursoController cursoController;
  private JTextField textFieldCodigo;

  public DeleteFormGUI(final CursoController cursoController) {
    this.cursoController = cursoController;
    frame = new JFrame("Delete Curso");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Define o tamanho da janela
    int largura = 300;
    int altura = 150;
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

    frame.setLayout(new GridLayout(2, 1));

    JLabel labelCodigo = new JLabel("Código do Curso:");
    frame.getContentPane().add(labelCodigo);

    textFieldCodigo = new JTextField();
    frame.getContentPane().add(textFieldCodigo);

    JButton buttonDelete = new JButton("Excluir");
    buttonDelete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String codigoString = textFieldCodigo.getText();
        try {
          int codigo = Integer.parseInt(codigoString);
          cursoController.deleteCurso(codigo);
          JOptionPane.showMessageDialog(frame, "Curso excluído com sucesso!");
          frame.dispose();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(frame, "Código do curso inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(frame, "Erro ao excluir o curso: " + ex.getMessage(), "Erro",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    frame.getContentPane().add(buttonDelete);

    frame.setVisible(true);
  }
}
