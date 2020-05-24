package com.epam.schedule.boundary;

import com.epam.schedule.control.MovieNotFoundException;
import com.epam.schedule.control.MovieRepository;
import com.epam.schedule.control.ShowNotFoundException;
import com.epam.schedule.control.ShowRepository;
import com.epam.schedule.entity.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    @GetMapping
    public List<Show> getScheduleForDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return showRepository.findAllByDate(date);
    }

    @PostMapping("/show")
    public ResponseEntity save(@RequestBody Show show, @RequestParam Long movieId) {
        show.setMovie(movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Could not find movie with id: " + movieId)));
        showRepository.save(show);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public Show getById(@PathVariable Long id) {
        return showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("Could not find movie with id: " + id));
    }
}
