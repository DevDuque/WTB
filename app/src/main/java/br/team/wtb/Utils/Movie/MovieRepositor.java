//package br.team.wtb.Utils.Movie;
//
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import br.team.wtb.Model.Movie;
//import br.team.wtb.R;
//import br.team.wtb.Utils.API.MovieResponse;
//import br.team.wtb.Utils.API.TMDBApiService;
//import br.team.wtb.Utils.API.RetrofitClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MovieRepository {
//    private static MovieRepository instance;
//    private List<Movie> movieList;
//
//    // Construtor privado para impedir que a classe seja instanciada diretamente fora da própria classe
//    private MovieRepository() {
//        movieList = new ArrayList<>();
//        // Inicialize com filmes de exemplo ou mantenha vazio se for carregar tudo da API
//    }
//
//    public static synchronized MovieRepository getInstance() {
//        if (instance == null) {
//            instance = new MovieRepository();
//        }
//        return instance;
//    }
//
//    // Método para buscar filmes do TMDB
//    public void fetchMoviesFromTMDB() {
//        TMDBApiService apiService = RetrofitClient.getInstance().create(TMDBApiService.class);
//        Call<MovieResponse> call = apiService.getMoviesByGenre("8de5c348d0f60e0fd536f3039357f163", 10402); // Substitua 28 pelo ID do gênero desejado
//
//        call.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Movie> apiMovies = response.body().getResults();
//                    Log.d("MovieRepository", "Filmes recebidos: " + apiMovies.size());
//
//                    // Limpa a lista de filmes antes de adicionar os novos
//                    movieList.clear();
//
//                    // Adiciona filmes à lista
//                    for (Movie apiMovie : apiMovies) {
//                        String title = apiMovie.getMovieName();
//                        Log.d("MovieRepository", "Filmes retornados da api: " + apiMovies);
//                        String releaseDate = apiMovie.getMovieYear() != null ? apiMovie.getMovieYear().toString() : "Unknown Year";
//
//                        int year = 0;
//                        if (!"Unknown Year".equals(releaseDate)) {
//                            try {
//                                year = Integer.parseInt(releaseDate.substring(0, 4));
//                            } catch (NumberFormatException e) {
//                                Log.e("MovieRepository", "Invalid year format for movie: " + releaseDate);
//                            }
//                        }
//
//                        int rating = apiMovie.getMovieRating() != null ? apiMovie.getMovieRating() : 0;
//
//                        movieList.add(new Movie(
//                                R.drawable.img_poster_whiplash,
//                                title,
//                                year,
//                                rating,
//                                apiMovie.getMovieTrailerLink()
//                        ));
//                    }
//
//                    // Log para verificação
//                    Log.d("MovieRepository", "Filmes carregados com sucesso!");
//
//                } else {
//                    Log.e("MovieRepository", "Error: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.e("MovieRepository", "Chamada da API quebrou: " + t.getMessage());
//            }
//        });
//    }
//
//    // Método para retornar a lista de filmes
//    public List<Movie> getMovies() {
//        List<Movie> sortedMovies = new ArrayList<>(movieList); // Cria uma cópia para preservar a lista original
//        sortedMovies.sort((movie1, movie2) -> {
//            // Ordena favoritos primeiro
//            if (movie1.getFavorite() && !movie2.getFavorite()) {
//                return -1; // Favorito antes de não favorito
//            } else if (!movie1.getFavorite() && movie2.getFavorite()) {
//                return 1; // Não favorito depois
//            }
//            // Ordena alfabeticamente pelo título
//            return movie1.getMovieName().compareToIgnoreCase(movie2.getMovieName());
//        });
//        return sortedMovies;
//    }
//
//    // Método para retornar uma lista de filmes que são favoritos
//    public List<Movie> getFavoriteMovies() {
//        List<Movie> favoriteMovies = new ArrayList<>();
//        for (Movie movie : movieList) {
//            if (movie.getFavorite()) {
//                favoriteMovies.add(movie);
//            }
//        }
//        return favoriteMovies;
//    }
//
//    // Método para adicionar um filme à lista
//    public void addMovie(Movie movie) {
//        movieList.add(movie);
//    }
//}
//
