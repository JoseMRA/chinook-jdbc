package org.jmiguelra92.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private Integer id;

    @Column(name = "Title")
    private String titulo;

    // En la tabla es un número (ArtistId); en Java es un objeto Artista.
    // LAZY: el artista no se carga hasta que alguien lo pide.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ArtistId")
    private Artista artista;

    public Album() {
    }

    public Album(Integer id, String titulo, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + artista.getNombre() + ")";
    }
}