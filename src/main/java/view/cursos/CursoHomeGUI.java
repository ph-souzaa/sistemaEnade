package view.cursos;

import javax.swing.*;

import controller.CursoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CursoHomeGUI {
  private JFrame frame;
  private CursoController cursoController;

  public CursoHomeGUI(CursoController cursoController) {
    this.cursoController = cursoController;
    frame = new JFrame("Cursos Home");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Define o tamanho da janela
    int largura = 400;
    int altura = 300;
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

    frame.setLayout(new GridLayout(4, 1));

    JButton buttonRead = new JButton("LISTAR CURSOS");
    buttonRead.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openReadForm();
      }
    });
    frame.getContentPane().add(buttonRead);

    JButton buttonCreate = new JButton("CRIAR CURSO");
    buttonCreate.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openCreateForm();
      }
    });
    frame.getContentPane().add(buttonCreate);

    JButton buttonUpdate = new JButton("ATUALIZAR CURSO");
    buttonUpdate.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openUpdateForm();
      }
    });
    frame.getContentPane().add(buttonUpdate);

    JButton buttonDelete = new JButton("DELETAR CURSO");
    buttonDelete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openDeleteForm();
      }
    });
    frame.getContentPane().add(buttonDelete);

    frame.setVisible(true);
  }

  private void openReadForm() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ReadFormGUI readFormGUI = new ReadFormGUI(cursoController);
      }
    });
  }

  private void openCreateForm() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        CreateFormGUI createFormGUI = new CreateFormGUI(cursoController);
      }
    });
  }

  private void openUpdateForm() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        UpdateFormGUI updateFormGUI = new UpdateFormGUI(cursoController);
      }
    });
  }

  private void openDeleteForm() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DeleteFormGUI deleteFormGUI = new DeleteFormGUI(cursoController);
      }
    });
  }

  public static void main(String[] args) throws SQLException {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          CursoController cursoController = new CursoController();
          new CursoHomeGUI(cursoController);
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    });
  }

  public void showGUI() {
  }
}
