package org.jmiguelra92.app;

import org.jmiguelra92.dao.AlbumDAO;
import org.jmiguelra92.dao.AlbumDAOJdbc;
import org.jmiguelra92.dao.ArtistaDAO;
import org.jmiguelra92.dao.ArtistaDAOJdbc;
import org.jmiguelra92.dao.GeneroDAO;
import org.jmiguelra92.dao.GeneroDAOJdbc;
import org.jmiguelra92.dao.PistaDAO;
import org.jmiguelra92.dao.PistaDAOJdbc;
import org.jmiguelra92.modelo.Album;
import org.jmiguelra92.modelo.Artista;
import org.jmiguelra92.modelo.Genero;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    private static final GeneroDAO generoDAO = new GeneroDAOJdbc();
    private static final ArtistaDAO artistaDAO = new ArtistaDAOJdbc();
    private static final AlbumDAO albumDAO = new AlbumDAOJdbc();
    private static final PistaDAO pistaDAO = new PistaDAOJdbc();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            try {
                switch (opcion) {
                    case 1 -> listarGeneros();
                    case 2 -> contarPistasPorGenero();
                    case 3 -> buscarArtistas();
                    case 4 -> artistasMasVendidos();
                    case 5 -> albumesDeArtista();
                    case 6 -> pistasDeGenero();
                    case 7 -> pistasDeAlbum();
                    case 8 -> insertarAlbum();
                    case 9 -> cambiarTituloAlbum();
                    case 10 -> borrarAlbum();
                    case 0 -> System.out.println("Hasta pronto.");
                    default -> System.out.println("Esa opción no existe.");
                }
            } catch (SQLException e) {
                System.out.println("No se ha podido completar la operación: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========= CHINOOK =========");
        System.out.println(" 1. Listar géneros");
        System.out.println(" 2. Géneros con su número de pistas");
        System.out.println(" 3. Buscar artistas");
        System.out.println(" 4. Artistas más vendidos");
        System.out.println(" 5. Álbumes de un artista");
        System.out.println(" 6. Pistas de un género");
        System.out.println(" 7. Pistas de un álbum");
        System.out.println(" 8. Añadir un álbum");
        System.out.println(" 9. Cambiar el título de un álbum");
        System.out.println("10. Borrar un álbum");
        System.out.println(" 0. Salir");
        System.out.println("===========================");
    }

    // ---------- Ejemplos resueltos ----------

    private static void listarGeneros() throws SQLException {
        for (Genero genero : generoDAO.findAll()) {
            System.out.println(genero);
        }
    }

    private static void buscarArtistas() throws SQLException {
        String texto = leerTexto("Texto a buscar: ");
        List<Artista> artistas = artistaDAO.buscarPorNombre(texto);
        if (artistas.isEmpty()) {
            System.out.println("Ningún artista contiene \"" + texto + "\".");
        }
        for (Artista artista : artistas) {
            System.out.println(artista);
        }
    }

    private static void insertarAlbum() throws SQLException {
        String titulo = leerTexto("Título del álbum: ");
        int artistaId = leerEntero("Id del artista: ");
        Album album = new Album(null, titulo, new Artista(artistaId, null));
        if (albumDAO.insert(album)) {
            System.out.println("Álbum guardado con el id " + album.getId() + ".");
        }
    }

    // ---------- Te toca a ti ----------

    private static void contarPistasPorGenero() throws SQLException {
        // TODO opción 2: recorre generoDAO.contarPistas() e imprime "Género: número"
        for (Genero genero : generoDAO.findAll()) {
            System.out.println(genero);
        }
    }

    private static void artistasMasVendidos() throws SQLException {
        // TODO opción 4: pide cuántos artistas quiere ver y usa artistaDAO.masVendidos(n)
    }

    private static void albumesDeArtista() throws SQLException {
        // TODO opción 5: pide el id del artista y muestra albumDAO.findByArtista(id)
    }

    private static void pistasDeGenero() throws SQLException {
        // TODO opción 6: pide el id del género, muestra cuántas pistas hay y las 20 primeras
    }

    private static void pistasDeAlbum() throws SQLException {
        // TODO opción 7: pide el id del álbum y muestra pistaDAO.findByAlbum(id)
    }

    private static void cambiarTituloAlbum() throws SQLException {
        // TODO opción 9: pide id del álbum, id de su artista y nuevo título; usa albumDAO.update(...)
    }

    private static void borrarAlbum() throws SQLException {
        // TODO opción 10: pide el id del álbum y usa albumDAO.delete(id); avisa si no existía
    }

    // ---------- Lectura de teclado ----------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}