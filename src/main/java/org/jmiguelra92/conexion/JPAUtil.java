package org.jmiguelra92.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;

public final class JPAUtil {

    private static EntityManagerFactory fabrica;

    private JPAUtil() {
    }

    public static synchronized EntityManager getEntityManager() {
        if (fabrica == null) {
            fabrica = Persistence.createEntityManagerFactory("chinook");
            Runtime.getRuntime().addShutdownHook(new Thread(fabrica::close));
        }
        return fabrica.createEntityManager();
    }

    public static SQLException aSQLException(RuntimeException e) {
        Throwable causa = e;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        return new SQLException(causa.getMessage(), e);
    }
}