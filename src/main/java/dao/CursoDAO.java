package dao;

import model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private final Connection conn;

    // Constructor
    public CursoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Curso> getAllCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        String sql = "SELECT * FROM Curso";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                Curso curso = new Curso(codigo, nome);
                cursos.add(curso);
            }
        }

        return cursos;
    }

    // Create
    public void addCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Curso (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("A criacao do curso falhou, nenhuma linha foi afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setCodigo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("A criacao do curso falhou, nenhum ID obtido.");
                }
            }
        }
    }

    // Read
    public Curso getCurso(int codigo) throws SQLException {
        String sql = "SELECT * FROM Curso WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Curso(rs.getInt("codigo"), rs.getString("nome"));
                } else {
                    throw new SQLException("O curso com o " + codigo + " nao existe.");
                }
            }
        }
    }

    // Update
    public void updateCurso(Curso curso) throws SQLException {
        String sql = "UPDATE Curso SET nome = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCodigo());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteCurso(int codigo) throws SQLException {
        String sql = "DELETE FROM Curso WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }
}
