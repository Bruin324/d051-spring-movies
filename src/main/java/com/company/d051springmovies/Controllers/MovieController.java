package com.company.d051springmovies.Controllers;

import com.company.d051springmovies.Movie;
import com.company.d051springmovies.ResultsPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    private static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/now-playing")
    public String nowPlaying(Model model) {
        model.addAttribute("movies", getMovies(API_URL));
        return "now-playing";
    }

    @RequestMapping("/medium-popular-long-name")
    public String mediumPopularLongName(Model model) {
        List<Movie> movies = getMovies(API_URL)
                .stream()
                .filter(movie -> movie.getPopularity() >= 30 && movie.getPopularity() <= 80)
                .filter(movie -> movie.getTitle().length() >= 10)
                .collect(Collectors.toList());
        model.addAttribute( "movies", movies);
        return "medium-popular-long-name";
    }

    @RequestMapping("/overview-mashup")
    public String overviewMashup() {
        return "overview-mashup";
    }

    public static List<Movie> getMovies (String route) {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage response = restTemplate.getForObject(route, ResultsPage.class);
//        System.out.println(response);
        return response.getResults();
    }
}
