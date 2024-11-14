package kodjo.winner.controller;

import kodjo.winner.model.Movie;
import kodjo.winner.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    
    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

   
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);  
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);  
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.getMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with id " + id));
        return ResponseEntity.ok(movie);  
    }

    
    @PutMapping("/edit/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        movie.setId(id);
        Movie updatedMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok(updatedMovie);  
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();  
    }
}
