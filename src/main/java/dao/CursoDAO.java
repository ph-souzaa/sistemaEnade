package dao;

import model.Curso;
import java.sql.*;

public class CursoDAO {
    private final Connection conn;

    public CursoDAO(Connection conn) {
        this.conn = conn;
    }

    // Create
    public void addCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Curso (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setCodigo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating course failed, no ID obtained.");
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
                    throw new SQLException("Course with code " + codigo + " not found.");
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
