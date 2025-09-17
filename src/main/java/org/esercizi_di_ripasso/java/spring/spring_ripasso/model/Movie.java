package org.esercizi_di_ripasso.java.spring.spring_ripasso.model;

public class Movie {

    private String titolo;
    private int id;

    //Costruttore vuoto. Perchè lo usiamo? Perchè in reali progetti SpringBoot per mantenere compatibilità con Hibernate, JPA e Data Transfer Objects, avremo bisogno di poter cambiare nel tempo gli oggetti istanziati dal Model e per quello ci serviremo dei Setter. Non essendo necessario per un mini-esercizio come questo, utilizziamo poco più in basso il costruttore parametrizziamo e così istanziamo facilmente l'oggetto nel Controller

    // public Movie(){

    // }

    public Movie(String titolo, int id){

        this.titolo = titolo;
        this.id = id;

    }

    
    public String getTitolo(){

        return titolo;

    }

    public int getId(){

        return id;

    }

    //I setter sottostanti vanno utilizzati nel momento in cui il nostro costruttore non è parametrizzato. Grazie ai setter possiamo sempre impostare delle condizioni tramite cui definire le caratteristiche dell'oggetto.
    // public void setTitolo(String titolo){

    //     if (titolo == null || titolo.trim().isEmpty() || titolo.trim().length() > 50) {
    //         this.titolo = "SENZA TITOLO";
    //     } else {
    //         this.titolo = titolo.toUpperCase();
    //     }

    // } 

    // public void setId(int id){

    //     if(id > 0 && id <= 1000) {

    //         this.id = id;

    //     } else {

    //         throw new IllegalArgumentException("L'id deve essere maggiore di zero e minore di mille");

    //     }

    // }


}
