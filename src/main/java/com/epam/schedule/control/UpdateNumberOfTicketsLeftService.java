package com.epam.schedule.control;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateNumberOfTicketsLeftService {
    private final ShowRepository showRepository;

    public void updateNumberOfTickets(Long showId, int ticketsBooked) {
        val show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Could not find show with id: " + showId));
        show.bookTickets(ticketsBooked);
        showRepository.save(show);
    }
}
