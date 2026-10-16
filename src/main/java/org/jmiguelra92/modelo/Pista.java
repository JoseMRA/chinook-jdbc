package org.jmiguelra92.modelo;


import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "Track")
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrackId")
    private Integer id;

    @Column(name = "Name")
    private String nombre;

    @Column(name = "Composer")
    private String compositor;

    @Column(name = "Milliseconds")
    private Integer milisegundos;

    @Column(name = "UnitPrice")
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenreId")
    private Genero genero;

    // Nuevo en la PG3: con JPA filtramos por p.album.id, así que la pista necesita su álbum
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlbumId")
    private Album album;

    public Pista() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public Integer getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(Integer milisegundos) {
        this.milisegundos = milisegundos;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        int segundos = milisegundos / 1000;
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }

    @Override
    public String toString() {
        return nombre + " | " + genero.getNombre() + " | " + getDuracion() + " | " + precioUnitario + " €";
    }
}