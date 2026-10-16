package org.jmiguelra92.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Genero;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GeneroDAOJpa implements GeneroDAO {

    @Override
    public List<Genero> findAll() throws SQLException {
        // JPQL: se escribe contra las CLASES y sus atributos (Genero, nombre),
        // no contra las tablas y columnas (Genre, Name)
        String jpql = "SELECT g FROM Genero g ORDER BY g.nombre";

        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery(jpql, Genero.class).getResultList();
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
    }

    // contarPistas necesita la entidad Pista, que anotaremos en el Paso 7.
    // Mientras tanto, para que la clase compile:
    @Override
    public Map<String, Integer> contarPistas() throws SQLException {
        throw new UnsupportedOperationException("Paso 7");
    }
}