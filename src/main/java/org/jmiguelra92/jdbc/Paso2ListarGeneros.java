package org.jmiguelra92.jdbc;

import org.jmiguelra92.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Paso2ListarGeneros {
    public static void main(String[] args) {
        String sql = "SELECT GenreId, Name FROM Genre ORDER BY Name";
        try (Connection conexion = ConexionBD.getConnection();
             Statement sentencia = conexion.createStatement()) {
            boolean hayResultado = sentencia.execute(sql);
            System.out.println("¿execute() devuelve un ResultSet? " + hayResultado);
            if (hayResultado) {
                ResultSet resultado = sentencia.getResultSet();
                while (resultado.next()) {
                    System.out.println(resultado.getInt("GenreId") + " - " + resultado.getString("Name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}