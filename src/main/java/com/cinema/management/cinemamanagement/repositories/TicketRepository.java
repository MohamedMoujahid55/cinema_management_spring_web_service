package com.cinema.management.cinemamanagement.repositories;

import com.cinema.management.cinemamanagement.entities.Seance;
import com.cinema.management.cinemamanagement.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
