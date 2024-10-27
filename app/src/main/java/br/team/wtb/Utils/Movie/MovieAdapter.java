package br.team.wtb.Utils.Movie;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.team.wtb.Model.Developer;
import br.team.wtb.Model.Movie;
import br.team.wtb.R;

// Classe adaptadora para gerenciar a exibição da lista de filmes em um RecyclerView
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    // Lista de filmes & Inicializador da lista
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Infla o layout do item de filme e cria um ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_details, parent, false);
        return new MovieViewHolder(view);
    }

    // Método chamado para preencher os dados de um item de visualização
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        // Obtém o filme da lista com base na posição
        Movie movie = movieList.get(position);

        holder.lblTitle.setText(movie.getMovieName());

        holder.btnFav.setImageResource(movie.getFavorite() ? R.drawable.ic_active_bookmark : R.drawable.ic_inactive_bookmark);

        holder.imgMovie.setImageResource(movie.getPosterImgResId());

        // Atualiza as estrelas de avaliação do filme
        updateStarRatings(holder, movie.getMovieRating());

        // Configura o clique na imagem do filme para abrir o trailer
        holder.imgMovie.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getMovieTrailerLink()));
            v.getContext().startActivity(intent);
        });

        // Configura o clique no botão de favorito para alternar o estado
        holder.btnFav.setOnClickListener(v -> {

            movie.setFavorite(!movie.getFavorite());
            holder.btnFav.setImageResource(movie.getFavorite() ? R.drawable.ic_active_bookmark : R.drawable.ic_inactive_bookmark);
        });

        // Configura o clique no botão de compartilhar
        holder.btnShare.setOnClickListener(v -> {

            shareMovieInfo(v, movie);
        });
    }

    // Método para compartilhar os dados do Filme
    private void shareMovieInfo(View view, Movie movie) {

        String shareText = "WTB: " + "github.com/devduque/WTB" + movie.toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        view.getContext().startActivity(Intent.createChooser(shareIntent, "Share Movie via"));
    }

    // Método para atualizar as estrelas de avaliação com base na classificação do filme
    private void updateStarRatings(MovieViewHolder holder, int rating) {

        for (int i = 0; i < holder.stars.length; i++) {
            // Define a imagem da estrela como ativa ou inativa com base na classificação
            if (i < rating) {
                holder.stars[i].setImageResource(R.drawable.ic_star_active);
            } else {
                holder.stars[i].setImageResource(R.drawable.ic_star_inactive);
            }
        }
    }

    // Retorna o número total de itens na lista de filmes
    @Override
    public int getItemCount() {

        return movieList.size();
    }

    // Classe interna ViewHolder para manter referências aos componentes de visualização
    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMovie;
        TextView lblTitle;

        ImageView btnFav;
        ImageView btnShare;
        ImageView[] stars = new ImageView[5];

        // Construtor do ViewHolder
        public MovieViewHolder(@NonNull View itemView) {

            super(itemView);

            // Inicializa os componentes de visualização
            imgMovie = itemView.findViewById(R.id.img_movie);
            lblTitle = itemView.findViewById(R.id.lblTitle);

            btnFav = itemView.findViewById(R.id.btn_fav);
            btnShare= itemView.findViewById(R.id.btn_share);

            // Inicializa as estrelas
            stars[0] = itemView.findViewById(R.id.star1);
            stars[1] = itemView.findViewById(R.id.star2);
            stars[2] = itemView.findViewById(R.id.star3);
            stars[3] = itemView.findViewById(R.id.star4);
            stars[4] = itemView.findViewById(R.id.star5);
        }
    }
}
