package org.esercizi_di_ripasso.java.spring.spring_ripasso.model;

public class Song {

    private String titolo;
    private int id;

    public Song(String titolo, int id){

        this.titolo = titolo;
        this.id = id;

    }

    public String getTitolo(){

        return titolo;

    }

    public int getId(){

        return id;

    }
}
