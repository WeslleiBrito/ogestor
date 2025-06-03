package com.example.ogestor.DAO;

import com.example.ogestor.database.Database;
import com.example.ogestor.model.Usuario;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

    public static void salvarUsuario(Usuario usuario) {
        String sql = """
                    INSERT INTO usuario(id, cpf, email, nome, senha, tipoUsuarioId)
                    VALUES (?, ?, ?, ?, ?, ?);
                """;

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNome());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getTipoUsuarioId());

            stmt.executeUpdate();

        }catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public static void editarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, tipoUsuarioId = ? WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTipoUsuarioId());
            stmt.setString(3, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public static Usuario buscarUsuarioPorId(String id) {

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String tipoUsuarioId = rs.getString("tipoUsuarioId");

                return new Usuario(
                        id,
                        cpf,
                        email,
                        nome,
                        senha,
                        tipoUsuarioId
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Usuario> buscarUsuarios () {
        String sql = "SELECT * FROM usuario;";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Database.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String tipoUsuarioId = rs.getString("tipoUsuarioId");

                usuarios.add(new Usuario(
                        id,
                        cpf,
                        email,
                        nome,
                        senha,
                        tipoUsuarioId
                ));
            }

            return usuarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
