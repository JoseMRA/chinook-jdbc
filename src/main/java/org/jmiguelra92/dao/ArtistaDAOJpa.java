package org.jmiguelra92.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Artista;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArtistaDAOJpa implements ArtistaDAO {

    @Override
    public List<Artista> buscarPorNombre(String texto) throws SQLException {
        // Igual que en la PG2: LIKE con parámetro, y los % van en el dato
        String jpql = "SELECT a FROM Artista a WHERE a.nombre LIKE :texto ORDER BY a.nombre";

        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery(jpql, Artista.class)
                    .setParameter("texto", "%" + texto + "%")
                    .getResultList();
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
    }

    @Override
    public Map<String, Integer> masVendidos(int n) throws SQLException {
        // Aquí usamos SQL nativo: la misma consulta de la PG2. JPQL podría hacerlo,
        // pero necesitaría una entidad InvoiceLine que no usamos en nada más.
        String sql = "SELECT ar.Name, SUM(il.Quantity) AS Unidades "
                + "FROM InvoiceLine il "
                + "JOIN Track t ON t.TrackId = il.TrackId "
                + "JOIN Album al ON al.AlbumId = t.AlbumId "
                + "JOIN Artist ar ON ar.ArtistId = al.ArtistId "
                + "GROUP BY ar.ArtistId, ar.Name "
                + "ORDER BY Unidades DESC, ar.Name "
                + "LIMIT ?1";
        Map<String, Integer> ventas = new LinkedHashMap<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {
            Query consulta = em.createNativeQuery(sql);
            consulta.setParameter(1, n);

            List<?> filas = consulta.getResultList();
            for (Object fila : filas) {
                Object[] columnas = (Object[]) fila;
                ventas.put((String) columnas[0], ((Number) columnas[1]).intValue());
            }
        } catch (PersistenceException e) {
            throw JPAUtil.aSQLException(e);
        }
        return ventas;
    }
}