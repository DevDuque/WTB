package br.team.wtb.Utils.API;

import java.util.List;
import br.team.wtb.Model.Movie;

public class MovieResponse {
    private List<Movie> results; // Make sure this matches the "results" field in the API response.

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
