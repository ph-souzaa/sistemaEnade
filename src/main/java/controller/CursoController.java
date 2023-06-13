package controller;

import model.Curso;
import dao.CursoDAO;
import dao.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class CursoController {
    private final CursoDAO cursoDAO;

    // Constructor
    public CursoController() throws SQLException {
        this.cursoDAO = new CursoDAO(DatabaseConnection.getConnection());
    }

    public void createCurso(String nome) throws SQLException {
        Curso curso = new Curso(nome);
        cursoDAO.addCurso(curso);
    }

    public Curso getCurso(int codigo) throws SQLException {
        return cursoDAO.getCurso(codigo);
    }

    public void updateCurso(Curso curso) throws SQLException {
        cursoDAO.updateCurso(curso);
    }

    public void deleteCurso(int codigo) throws SQLException {
        cursoDAO.deleteCurso(codigo);
    }

    public List<Curso> getAllCursos() throws SQLException {
        return cursoDAO.getAllCursos();
    }

}
