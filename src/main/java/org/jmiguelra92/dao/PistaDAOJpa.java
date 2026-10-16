package org.jmiguelra92.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Pista;

import java.sql.SQLException;
import java.util.List;

public class PistaDAOJpa implements PistaDAO {

    // Una sola consulta compartida, como SELECT_PISTA en la PG2.
    // Ya no hace falta "mapearPista": Hibernate construye las Pista y su Genero.
    private static final String SELECT_PISTA = "SELECT p FROM Pista p JOIN FETCH p.genero g ";

    @Override
    public List<Pista> findByGenero(int generoId) throws SQLException {
        String jpql = SELECT_PISTA + "WHERE g.id = :generoId ORDER BY p.nombre";
        return consultar(jpql, "generoId", generoId);
    }

    @Override
    public List<Pista> findByAlbum(int albumId) throws SQLException {
        // TODO 1: completa el JPQL. Es SELECT_PISTA + un filtro por álbum + orden por id de pista
        //         Pista guarda su álbum en el atributo "album", y el álbum tiene un "id"
        //         Usa un parámetro con nombre: :albumId
        String jpql = SELECT_PISTA + "WHERE p.album.id = :albumId ORDER BY p.id ";

        // TODO 2: llama a consultar(...) con el JPQL, el nombre del parámetro y el valor
        return consultar(jpql, "albumId", albumId);
    }

    private List<Pista> consultar(String jpql, String nombreParametro, int valor) throws SQLException {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery(jpql, Pista.class)
                    .setParameter(nombreParametro, valor)
                    .getResultList();
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
    }
}