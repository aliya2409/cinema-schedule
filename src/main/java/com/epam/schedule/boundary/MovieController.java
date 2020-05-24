package com.epam.schedule.boundary;

import com.epam.schedule.control.MovieNotFoundException;
import com.epam.schedule.control.MovieRepository;
import com.epam.schedule.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @PostMapping
    public ResponseEntity save(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Could not find movie with id: " + id));
    }
}
