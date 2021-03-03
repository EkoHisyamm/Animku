package com.example.animku.Model;

public class    GenreModel {

    String id, genre;

    public GenreModel(String id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
