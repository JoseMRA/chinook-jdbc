package org.jmiguelra92.app;


import org.jmiguelra92.dao.ArtistaDAO;
import org.jmiguelra92.dao.ArtistaDAOJpa;
import org.jmiguelra92.modelo.Artista;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PruebaArtistaDAOJpa {

    public static void main(String[] args) {
        ArtistaDAO artistaDAO = new ArtistaDAOJpa();

        try {
            System.out.println("=== C3 · Artistas que contienen \"zep\" ===");
            List<Artista> artistas = artistaDAO.buscarPorNombre("zep");
            for (Artista artista : artistas) {
                System.out.println(artista);
            }

            System.out.println();
            System.out.println("=== C7 · Los 5 artistas más vendidos ===");
            Map<String, Integer> ventas = artistaDAO.masVendidos(5);
            for (Map.Entry<String, Integer> fila : ventas.entrySet()) {
                System.out.println(fila.getKey() + ": " + fila.getValue() + " unidades");
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}