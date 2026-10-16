package org.jmiguelra92.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Album;

import java.sql.SQLException;
import java.util.List;

public class AlbumDAOJpa implements AlbumDAO {

    @Override
    public List<Album> findByArtista(int artistaId) throws SQLException {
        String jpql = "SELECT al FROM Album al JOIN FETCH al.artista ar "
                + "WHERE ar.id = :artistaId ORDER BY al.titulo";

        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery(jpql, Album.class)
                    .setParameter("artistaId", artistaId)
                    .getResultList();
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
    }

    // Los tres métodos de escritura se hacen en el Paso 8.
    // Mientras tanto, para que la clase compile:
    @Override
    public boolean insert(Album album) throws SQLException {
        throw new UnsupportedOperationException("Paso 8");
    }

    @Override
    public boolean update(Album album) throws SQLException {
        throw new UnsupportedOperationException("Paso 8");
    }

    @Override
    public boolean delete(int albumId) throws SQLException {
        throw new UnsupportedOperationException("Paso 8");
    }
}