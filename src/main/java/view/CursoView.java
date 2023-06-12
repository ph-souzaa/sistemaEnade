package view;

import controller.CursoController;
import model.Curso;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;

public class CursoView {
    private final CursoController cursoController;
    private final Scanner scanner;

    public CursoView(CursoController cursoController) {
        this.cursoController = cursoController;
        this.scanner = new Scanner(System.in);
    }

    public void createCurso() {
        System.out.println("Enter course name:");
        String nome = scanner.nextLine();
        try {
            cursoController.createCurso(nome);
            System.out.println("Course created successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayCurso() {
        try {
            System.out.println("Enter course code:");
            int codigo = scanner.nextInt();
            Curso curso = cursoController.getCurso(codigo);
            System.out.println("Course Code: " + curso.getCodigo());
            System.out.println("Course Name: " + curso.getNome());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();  // Clear the input
        }
    }

    // Add similar methods for updating and deleting courses
}
