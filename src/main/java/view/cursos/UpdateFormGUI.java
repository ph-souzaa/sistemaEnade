package view.cursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controller.CursoController;
import model.Curso;

public class UpdateFormGUI {
  private JFrame frame;
  private CursoController cursoController;
  private JTextField textFieldCodigo;
  private JTextField textFieldNome;

  public UpdateFormGUI(final CursoController cursoController) {
    this.cursoController = cursoController;
    frame = new JFrame("Atualizar Curso");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new GridLayout(3, 1));

    int largura = 400;
    int altura = 200;

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
    frame.setSize(largura, altura); // Definir tamanho desejado da janela

    JLabel labelCodigo = new JLabel("Código do Curso:");
    frame.getContentPane().add(labelCodigo);

    textFieldCodigo = new JTextField();
    frame.getContentPane().add(textFieldCodigo);

    JLabel labelNome = new JLabel("Novo Nome do Curso:");
    frame.getContentPane().add(labelNome);

    textFieldNome = new JTextField();
    frame.getContentPane().add(textFieldNome);

    JButton buttonUpdate = new JButton("Atualizar");
    buttonUpdate.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String codigoString = textFieldCodigo.getText();
        String novoNome = textFieldNome.getText();
        try {
          int codigo = Integer.parseInt(codigoString);
          Curso curso = cursoController.getCurso(codigo);
          if (curso != null) {
            curso.setNome(novoNome);
            cursoController.updateCurso(curso);
            JOptionPane.showMessageDialog(frame, "Curso atualizado com sucesso!");
            frame.dispose();
          } else {
            JOptionPane.showMessageDialog(frame, "Curso com código " + codigo + " não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(frame, "Código do curso inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(frame, "Erro ao atualizar o curso: " + ex.getMessage(), "Erro",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    frame.getContentPane().add(buttonUpdate);

    frame.setVisible(true);
  }
}
