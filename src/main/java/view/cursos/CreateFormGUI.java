package view.cursos;

import javax.swing.*;

import controller.CursoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateFormGUI {
  private JFrame frame;

  public CreateFormGUI(final CursoController cursoController) {
    frame = new JFrame("Formulário de Criação");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Define o tamanho da janela
    int largura = 400;
    int altura = 200;
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

    frame.setLayout(new GridLayout(0, 1));

    JLabel labelNome = new JLabel("Nome:");
    frame.getContentPane().add(labelNome);

    final JTextField textFieldNome = new JTextField();
    frame.getContentPane().add(textFieldNome);

    JButton buttonCriar = new JButton("Criar");
    buttonCriar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String nome = textFieldNome.getText();
        if (!nome.isEmpty()) {
          try {
            cursoController.createCurso(nome);
            JOptionPane.showMessageDialog(frame, "Curso criado com sucesso!");
            frame.dispose(); // Feche o formulário após a criação do curso
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao criar o curso: " + ex.getMessage(), "Erro",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          JOptionPane.showMessageDialog(frame, "Nome do curso não pode estar vazio.", "Erro",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    frame.getContentPane().add(buttonCriar);

    frame.setVisible(true);
  }
}
