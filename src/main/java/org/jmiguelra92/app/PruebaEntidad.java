package org.jmiguelra92.app;


import jakarta.persistence.EntityManager;
import org.jmiguelra92.conexion.JPAUtil;
import org.jmiguelra92.modelo.Genero;

public class PruebaEntidad {

    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            // find: busca una fila por su clave primaria y la convierte en un objeto
            Genero genero = em.find(Genero.class, 1);
            System.out.println("Género 1: " + genero);

            Genero inexistente = em.find(Genero.class, 9999);
            System.out.println("Género 9999: " + inexistente);
        }
    }
}