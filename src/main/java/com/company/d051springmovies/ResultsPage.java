package com.company.d051springmovies;

import java.util.List;

public class ResultsPage {

    List<Movie> results;

    public ResultsPage() {
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultsPage{" +
                "results=" + results +
                '}';
    }
}
