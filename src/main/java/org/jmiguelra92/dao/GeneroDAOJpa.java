package org.jmiguelra92.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Genero;

import java.sql.SQLException;
import java.util.LinkedHashMap;
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
    @Override
    public Map<String, Integer> contarPistas() throws SQLException {
        String jpql = "SELECT g.nombre, COUNT(p) "
                + "FROM Genero g LEFT JOIN Pista p ON p.genero.id = g.id "
                + "GROUP BY g.id, g.nombre "
                + "ORDER BY COUNT(p) DESC, g.nombre";
        Map<String, Integer> recuento = new LinkedHashMap<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {
            // Cada fila es un Object[]: la posición 0 es el nombre y la 1 el recuento
            List<Object[]> filas = em.createQuery(jpql, Object[].class).getResultList();
            for (Object[] fila : filas) {
                recuento.put((String) fila[0], ((Number) fila[1]).intValue());
            }
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
        return recuento;
    }
    // Mientras tanto, para que la clase compile:
}