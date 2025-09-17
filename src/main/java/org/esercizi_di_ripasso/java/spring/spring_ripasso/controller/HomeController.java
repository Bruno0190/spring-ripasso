package org.esercizi_di_ripasso.java.spring.spring_ripasso.controller;

import java.util.ArrayList;
import java.util.List;

import org.esercizi_di_ripasso.java.spring.spring_ripasso.model.Movie;
import org.esercizi_di_ripasso.java.spring.spring_ripasso.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getBestOfYear(Model model) {

        model.addAttribute("nome", "Bruno");

        return "index";
    }
    
    //VERSIONE DIVERSA IN CUI AL LINK http://localhost:8080/ SI PUO METTERE COME PARAMETRO IL NOME ARBITRARIAMENTE E SE NON VIENE DATO DI DEFAULT COMPARIRA "Nessuno". PERO RICORDA CHE PER SCRIVERE TU IL NOME DOPO L'URL DEVI SCRIVERE SUBITO ?nome = ...
    // @GetMapping("/")
    // public String getBestOfYear(Model model, @RequestParam(name = "nome", defaultValue = "Nessuno") String name) {

    //     model.addAttribute("nome", name);

    //     return "index";
    // }
    
    private ArrayList<Movie> getBestMovies(){

        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Il signore degli anelli", 0));
        movies.add(new Movie("Avatar", 1));
        movies.add(new Movie("I soliti ignoti", 2));
        movies.add(new Movie("Deadpool & Wolverine", 3));

        return movies;

    }

    private ArrayList<Song> getBestSongs(){

        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Felicità", 0));
        songs.add(new Song("All time high",1));
        songs.add(new Song("Bellissima",2));

        return songs;

    }

    //PRIMA soluzione per il metodo all'url /movies. Il metodo è molto manuale e prevede la creazione di un ulteriore ArrayList dalla quale estrarre i titoli. La chiave nel Model movieList viene riportata nella view
    // @GetMapping("/movies")
    // public String listMovies(Model model) {

    //     ArrayList<String> films = new ArrayList<>();

    //     for(Movie movie : getBestMovies()){

    //         films.add(movie.getTitolo());

    //     }

    //     model.addAttribute("movieList", String.join(", ", films));

    //     return "/movies/index";
    // }

    //SECONDA soluzione per il metodo all'url /movies. Il metodo decisamente più compatto del precedente non recupera i titoli dei film e non crea una stringa perchè nel corrispettivo blocco della view usiamo un th:each dal quale elenchiamo tutti i titoli. Quindi usiamo getTitolo() direttamente nell'HTML
    // @GetMapping("/movies")
    // public String listMovies(Model model) {
        
    //     List<Movie> films = getBestMovies();  

    //     model.addAttribute("movieList", films);
    //     return "movies";
    // }

    //TERZA soluzione ipercompatta che utilizza il .stream() per creare un flusso di dati che possono essere manipolati, filtrati e infine restituiti in una Lista
    @GetMapping("/movies")
    public String listMovies(Model model) {

        List<String> films = getBestMovies()
        .stream()
        .map(Movie::getTitolo)
        //.map(movie -> movie.getTitolo()) --Metodo alternativo per indicare che se vogliamo il titolo da ogni Movie nella lista getBestMovies() usiamo una variabile che rappresenta ogni elemento dello stream. La freccia sarebbe la lambda, tutto il rigo significa: per ogni variabile movie ovvero titolo del movie in Movie.
        .toList();

        model.addAttribute("movieList", films);

        return "/movies/index";
        
    }

    @GetMapping("/songs")
    public String listSongs(Model model){

        List<String> songs = getBestSongs()
        .stream()
        .map(song -> song.getTitolo())
        .toList();

        model.addAttribute("canzoni", songs);

        return "/songs/index";
    }

    //PRIMA soluzione per il metodo all'url /movies/id. Il metodo è molto manuale e per quanto funzionante non utilizza il metodo getId() il che, non garantisce efficienza in un progetto vero.
    // @GetMapping("/movies/{id}")
    // public String movieShow(@PathVariable("id") int movieId, Model model){

        
    //     Movie movie = getBestMovies().get(movieId);
    //     String titolo = movie.getTitolo();

    //     model.addAttribute("titoloMovie", titolo);

    //     return "/movies/show";

    // }

    //SECONDA soluzione per il metodo all'url /movies/id. Il metodo prevede un confronto tra la variabile movieId passata nell'url e l'id dell'elemento m di tipo Movie dalla lista getBestMovies(). Trovata corrispondenza si interrompre il ciclo e si passa al model quel movie di quell'id che così sarà restituito alla view.
    // @GetMapping("/movies/{id}")
    // public String movieShow(@PathVariable("id") int movieId, Model model) {

    //     Inizializzo la variabile movie sennò non funziona
    //     Movie movie = null;
    //     for (Movie m : getBestMovies()) {
    //         if (m.getId() == movieId) {
    //             movie = m;
    //             break;
    //         }
    //     }

    //     model.addAttribute("movie", movie);

    //     return "movies/show";
    // }

    //TERZA soluzione per il metodo all'url /songs/id. Il metodo utilizza il metodo stream() che ci consente tramite una serie di metodi intermedi di filtrare e raggiungere l'oggetto song che desideriamo. Al model passeremo solo il titolo.
    @GetMapping("/movies/{id}")
    public String movieShow(@PathVariable("id") int movieId, Model model) {
        
        Movie movie = getBestMovies()
        .stream()
        .filter(m -> m.getId() == movieId)
        .findFirst() //Occhio che findFirst mi dà un Optional<Type> per cui sarei dovuto partire da Optional<Movie> movies = getBestMovies(). Con get() io vado a recuperare l'elemento (o gli elementi) filtrati dalla lista Optional ottenuta con findFirst(). Al posto di get() è raccomandabile terminare con orElse(null) perchè nel caso l'Optional<> fosse vuoto non mi salterebbe il codice
        .get();
        
        model.addAttribute("titoloMovie", movie.getTitolo());

        return "movies/show";
    }
    

    @GetMapping("/songs/{id}")
    public String songShow(@PathVariable("id") int songId, Model model) {

        Song song = getBestSongs()
        .stream()
        .filter(s -> s.getId() == songId)
        .findAny()
        .orElse(null);

        model.addAttribute("titoloSong", song.getTitolo());

        return "songs/show";
    }
    

    

}
