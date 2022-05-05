package com.cinema.management.cinemamanagement.services;

import com.cinema.management.cinemamanagement.dtos.TicketDto;
import com.cinema.management.cinemamanagement.entities.Ticket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITicketService {
    TicketDto addNewTicket(TicketDto ticketDto);
    TicketDto getTicketbyId(Long id);
    List<TicketDto> getTickets(int page, int size);
    TicketDto updateNewCategory(Long id, TicketDto ticketDto);
    void deteleTicket(Long id);
}
