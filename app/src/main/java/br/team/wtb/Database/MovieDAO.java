package br.team.wtb.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.team.wtb.Model.Movie;
import br.team.wtb.Utils.Database.MovieDatabaseHelper;

public class MovieDAO {
    private final SQLiteDatabase db;

    public MovieDAO(Context context) {
        MovieDatabaseHelper dbHelper = new MovieDatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Método para inserir um filme
    public boolean insert(Movie movie) {
        ContentValues values = new ContentValues();
        values.put("posterImgResId", movie.getPosterImgResId());
        values.put("movieName", movie.getMovieName());
        values.put("movieYear", movie.getMovieYear());
        values.put("movieRating", movie.getMovieRating());
        values.put("isFavorite", movie.getFavorite() ? 1 : 0);
        values.put("movieTrailerLink", movie.getMovieTrailerLink());

        long result = db.insert("movies", null, values);
        return result != -1;
    }

    // Método para deletar um filme pelo nome
    public boolean delete(String movieName) {
        int result = db.delete("movies", "movieName = ?", new String[]{movieName});
        return result > 0;  // Retorna true se a exclusão foi realizada corretamente
    }

    // Método para buscar um filme pelo nome
    public Movie getMovieByName(String movieName) {
        Cursor cursor = db.query(
                "movies",
                null,
                "movieName = ?",
                new String[]{movieName},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            Integer posterImgResId = cursor.getInt(cursor.getColumnIndexOrThrow("posterImgResId"));
            Integer movieYear = cursor.getInt(cursor.getColumnIndexOrThrow("movieYear"));
            Integer movieRating = cursor.getInt(cursor.getColumnIndexOrThrow("movieRating"));
            Boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow("isFavorite")) == 1;
            String movieTrailerLink = cursor.getString(cursor.getColumnIndexOrThrow("movieTrailerLink"));
            cursor.close();
            return new Movie(posterImgResId, movieName, movieYear, movieRating, isFavorite, movieTrailerLink);
        }

        if (cursor != null) cursor.close();
        return null;
    }
}