package com.epam.schedule.boundary;

import com.epam.schedule.control.UpdateNumberOfTicketsLeftService;
import com.epam.schedule.entity.OrderFinishedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "order-finished-schedule")
@RequiredArgsConstructor
public class OrderFinishedEventHandler {
    private final UpdateNumberOfTicketsLeftService updateNumberOfTicketsLeftService;
    private final ObjectMapper mapper;

    @RabbitHandler
    public void receive(String in) {
        try {
            val event = mapper.readValue(in, OrderFinishedEvent.class);
            updateNumberOfTicketsLeftService.updateNumberOfTickets(event.getShowId(), event.getNumberOfTickets());
        } catch (JsonProcessingException e) {
            log.error("Unable to parse line: " + in);
        }
    }
}
