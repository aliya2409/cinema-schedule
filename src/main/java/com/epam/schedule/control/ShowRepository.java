package com.epam.schedule.control;

import com.epam.schedule.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long > {
    List<Show> findAllByDate(LocalDate date);
}
