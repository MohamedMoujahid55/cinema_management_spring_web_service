package com.cinema.management.cinemamanagement.controllers;

import com.cinema.management.cinemamanagement.dtos.TicketDto;
import com.cinema.management.cinemamanagement.requests.TicketRequest;
import com.cinema.management.cinemamanagement.respenses.TicketResponse;
import com.cinema.management.cinemamanagement.services.ITicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tickets") @AllArgsConstructor
public class TicketController {
    private ITicketService ticketService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id){
        TicketDto ticketDto = ticketService.getTicketbyId(id);
        TicketResponse ticketResponse = new ModelMapper().map(ticketDto, TicketResponse.class);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<TicketResponse> getTickets(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "5") int size){

        List<TicketDto> ticketDtos = ticketService.getTickets(page, size);
        List<TicketResponse> ticketResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        ticketDtos.forEach(ticketDto -> {
            ticketResponses.add(modelMapper.map(ticketDto, TicketResponse.class));
        });

        return ticketResponses;
    }

    @PostMapping
    public  ResponseEntity<TicketResponse> addNewTicket(@RequestBody TicketRequest ticketRequest){

        ModelMapper modelMapper = new ModelMapper();
        TicketDto ticketDto = modelMapper.map(ticketRequest, TicketDto.class);
        TicketDto newTicketDto = ticketService.addNewTicket(ticketDto);
        TicketResponse ticketResponse = modelMapper.map(newTicketDto, TicketResponse.class);

        return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long id,
                                                       @RequestBody TicketRequest ticketRequest){

        ModelMapper modelMapper = new ModelMapper();
        TicketDto ticketDto = modelMapper.map(ticketRequest, TicketDto.class);
        TicketDto newTicketDto = ticketService.updateNewCategory(id, ticketDto);
        TicketResponse ticketResponse = modelMapper.map(newTicketDto, TicketResponse.class);

        return new ResponseEntity<>(ticketResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TicketResponse> deleteTicket(@PathVariable Long id){
        ticketService.deteleTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
