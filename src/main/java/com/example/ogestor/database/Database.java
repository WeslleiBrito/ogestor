package com.example.ogestor.database;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:ogestor.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    protected static boolean tabelaEstaVazia(String nomeTabela) {
        String sql = "SELECT 1 FROM " + nomeTabela + " LIMIT 1";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            return !rs.next();
        }catch (SQLException e) {
            return true;
        }
    }

    public static void inicializar() {
        String sqlTipoUsuario = """
                    CREATE TABLE IF NOT EXISTS tipo_usuario (
                        id TEXT PRIMARY KEY,
                        nome TEXT NOT NULL
                    );
                """;

        String sqlInsertTipoUsuario = """
    			INSERT INTO tipo_usuario (id, nome) VALUES 
				('938ac6a4-9f52-4591-9cb6-1d2780a66051', 'Administrador'),
				('56a60e1f-486e-41fb-bab6-ecb72acbbc71', 'Padrao');
		""";

        String sqlUsuario = """
                    CREATE TABLE IF NOT EXISTS usuario (
                        id TEXT PRIMARY KEY,
                        cpf TEXT NOT NULL UNIQUE,
                        email TEXT NOT NULL UNIQUE,
                        nome TEXT NOT NULL,
                        senha TEXT NOT NULL,
                        tipoUsuarioId TEXT NOT NULL,
                        FOREIGN KEY (tipoUsuarioId) REFERENCES tipo_usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
                    )
                """;

        try(Connection conn = conectar();
            Statement stmt = conn.createStatement()){
            stmt.execute("PRAGMA foreign_keys = ON");
            stmt.execute(sqlTipoUsuario);
            stmt.execute(sqlUsuario);

            if(tabelaEstaVazia("tipo_usuario")){
                stmt.execute(sqlInsertTipoUsuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
