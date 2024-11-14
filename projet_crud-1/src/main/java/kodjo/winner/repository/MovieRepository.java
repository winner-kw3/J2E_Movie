package kodjo.winner.repository;

import kodjo.winner.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    
    List<Movie> findByTitle(String title);
}
