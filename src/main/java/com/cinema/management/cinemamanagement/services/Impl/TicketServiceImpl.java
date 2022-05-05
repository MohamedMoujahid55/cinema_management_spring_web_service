package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.TicketDto;
import com.cinema.management.cinemamanagement.entities.Ticket;
import com.cinema.management.cinemamanagement.repositories.TicketRepository;
import com.cinema.management.cinemamanagement.services.ITicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {
    private TicketRepository ticketRepository;

    @Override
    public TicketDto addNewTicket(TicketDto ticketDto) {
        ModelMapper modelMapper = new ModelMapper();
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        Ticket newTicket = ticketRepository.save(ticket);
        TicketDto newTicketDto = modelMapper.map(newTicket, TicketDto.class);
        return newTicketDto;
    }

    @Override
    public TicketDto getTicketbyId(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        return new ModelMapper().map(ticket, TicketDto.class);
    }

    @Override
    public List<TicketDto> getTickets(int page, int size) {

        Page<Ticket> ticketPage = ticketRepository.findAll(PageRequest.of(page,size));
        List<Ticket> ticketList = ticketPage.getContent();
        List<TicketDto> ticketDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        ticketList.forEach(ticket -> {
            ticketDtos.add(modelMapper.map(ticket, TicketDto.class));
        });

        return ticketDtos;
    }

    @Override
    public TicketDto updateNewCategory(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        ticket.setPrix(ticketDto.getPrix());
        ticket.setReservee(ticketDto.isReservee());
        ticket.setCodePayement(ticketDto.getCodePayement());
        ticket.setNomClient(ticketDto.getNomClient());

        if (ticketDto.getPlace().getId() != null){
            ticket.setPlace(ticketDto.getPlace());
        }
        if(ticketDto.getProjectionFilm().getId() != null){
            ticket.setProjectionFilm(ticketDto.getProjectionFilm());
        }

        Ticket newTicket = ticketRepository.save(ticket);
        TicketDto ticketDtoNew = new ModelMapper().map(newTicket, TicketDto.class);

        return ticketDtoNew;
    }

    @Override
    public void deteleTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
