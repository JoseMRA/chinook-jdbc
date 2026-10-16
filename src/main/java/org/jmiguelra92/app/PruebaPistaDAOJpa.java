package org.jmiguelra92.app;

import org.jmiguelra92.dao.PistaDAO;
import org.jmiguelra92.dao.PistaDAOJpa;
import org.jmiguelra92.modelo.Pista;

import java.sql.SQLException;
import java.util.List;

public class PruebaPistaDAOJpa {

    public static void main(String[] args) {
        PistaDAO pistaDAO = new PistaDAOJpa();

        try {
            System.out.println("=== C2 · Pistas del género Metal (id 3), las 5 primeras ===");
            List<Pista> pistas = pistaDAO.findByGenero(3);
            System.out.println(pistas.size() + " pistas");
            for (Pista pista : pistas.subList(0, 5)) {
                System.out.println(pista);
            }

            System.out.println();
            System.out.println("=== C5 · Pistas del álbum 1 ===");
            for (Pista pista : pistaDAO.findByAlbum(1)) {
                System.out.println(pista);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}