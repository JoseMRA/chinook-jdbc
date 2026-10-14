package org.jmiguelra92.app;

import jakarta.persistence.EntityManager;
import org.jmiguelra92.conexion.JPAUtil;

public class PruebaConexionJPA {

    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            System.out.println("EntityManager abierto: " + em.isOpen());
        }
    }
}