package br.team.wtb.Utils.Movie;

import java.util.ArrayList;
import java.util.List;

import br.team.wtb.Model.Movie;
import br.team.wtb.R;

public class MovieRepository {
    private static MovieRepository instance;

    // Lista de filmes
    private List<Movie> movieList;

    // Construtor privado para impedir que a classe seja instanciada diretamente fora da própria classe
    private MovieRepository() {
        movieList = new ArrayList<>();
        // Adiciona filmes de exemplo ou busca de um banco de dados/API
        movieList.add(new Movie(R.drawable.img_poster_whiplash, "Whiplash", 2014, 5, false, "https://www.youtube.com/watch?v=iTgk3WbTErk&pp=ygUQd2hpcGxhc2ggdHJhaWxlcg%3D%3D"));
        movieList.add(new Movie(R.drawable.img_poster_ray, "Ray", 2004, 4, true, "https://www.youtube.com/watch?v=jVHCQfcugdw&pp=ygULcmF5IHRyYWlsZXI%3D"));
        movieList.add(new Movie(R.drawable.img_poster_once, "Once", 2008, 4, false, "https://www.youtube.com/watch?v=K4uFFNl6FQ4&pp=ygUMb25jZSB0cmFpbGVy"));
        movieList.add(new Movie(R.drawable.img_poster_begin, "Begin Again", 2014, 4, true, "https://www.youtube.com/watch?v=uTRCxOE7Xzc&pp=ygUTYmVnaW4gYWdhaW4gdHJhaWxlcg%3D%3D"));
        movieList.add(new Movie(R.drawable.img_poster_bohemian, "Bohemian Rhapsody", 2018, 5, false, "https://www.youtube.com/watch?v=GryRsVhOvxo&pp=ygUQYm9oZW1pYW4gdHJhaWxlcg%3D%3D"));
    }

    public static synchronized MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    // Método para retornar a lista de filmes
    public List<Movie> getMovies() {
        List<Movie> sortedMovies = new ArrayList<>(movieList); // Cria uma cópia para preservar a lista original
        sortedMovies.sort((movie1, movie2) -> {
            // Ordena favoritos primeiro
            if (movie1.getFavorite() && !movie2.getFavorite()) {
                return -1; // Favorito antes de não favorito
            } else if (!movie1.getFavorite() && movie2.getFavorite()) {
                return 1; // Não favorito depois
            }
            // Ordena alfabeticamente pelo título
            return movie1.getMovieName().compareToIgnoreCase(movie2.getMovieName());
        });
        return sortedMovies;
    }

    // Método para retornar uma lista de filmes que são favoritos
    public List<Movie> getFavoriteMovies() {
        List<Movie> favoriteMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getFavorite()) {
                favoriteMovies.add(movie);
            }
        }
        return favoriteMovies;
    }

    // Método para adicionar um filme à lista
    public void addMovie(Movie movie) {
        movieList.add(movie);
    }
}
