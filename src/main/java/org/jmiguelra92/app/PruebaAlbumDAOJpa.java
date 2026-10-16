package org.jmiguelra92.app;

import org.jmiguelra92.dao.AlbumDAO;
import org.jmiguelra92.dao.AlbumDAOJpa;
import org.jmiguelra92.modelo.Album;

import java.sql.SQLException;

public class PruebaAlbumDAOJpa {

    public static void main(String[] args) {
        AlbumDAO albumDAO = new AlbumDAOJpa();

        try {
            System.out.println("=== C4 · Álbumes de Iron Maiden (artista 90) ===");
            for (Album album : albumDAO.findByArtista(90)) {
                System.out.println(album);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}