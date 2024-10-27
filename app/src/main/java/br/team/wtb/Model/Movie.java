package br.team.wtb.Model;

import java.io.Serializable;

public class Movie implements Serializable {

    private Integer posterImgResId;

    private String movieName;

    private Integer movieYear;

    private Integer movieRating;

    private Boolean isFavorite;

    private String movieTrailerLink;

    public Movie(Integer posterImgResId, String movieName, Integer movieYear, Integer movieRating, Boolean isFavorite, String movieTrailerLink) {

        this.posterImgResId = posterImgResId;

        this.movieName = movieName;

        this.movieYear = movieYear;

        this.movieRating = movieRating;

        this.isFavorite = isFavorite;

        this.movieTrailerLink = movieTrailerLink;
    }

    @Override
    public String toString() {
        return
                "movieName='" + movieName + '\'' +
                ", movieYear=" + movieYear +
                ", movieRating=" + movieRating +
                ", movieTrailerLink='" + movieTrailerLink + '\'';
    }

    public Integer getPosterImgResId() {

        return posterImgResId;
    }

    public void setPosterImgResId(Integer posterImgResId) {

        this.posterImgResId = posterImgResId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public Integer getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Integer movieRating) {
        this.movieRating = movieRating;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getMovieTrailerLink() {
        return movieTrailerLink;
    }

    public void setMovieTrailerLink(String movieTrailerLink) {
        this.movieTrailerLink = movieTrailerLink;
    }

}
