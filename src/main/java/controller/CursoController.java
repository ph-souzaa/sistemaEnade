package controller;

import model.Curso;
import dao.CursoDAO;
import java.sql.SQLException;

public class CursoController {
    private final CursoDAO cursoDAO;

    public CursoController(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
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
}
