package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.entities.*;
import com.cinema.management.cinemamanagement.repositories.*;
import com.cinema.management.cinemamanagement.services.ICinemaInitService;
import com.cinema.management.cinemamanagement.services.ICinemaService;
import com.cinema.management.cinemamanagement.services.IVilleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CinemaInitServiceImpl implements ICinemaInitService {
    private VilleRepository villeRepository;   private CinemaRepository cinemaRepository;
    private SalleRepository salleRepository;   private PlaceRepository placeRepository;
    private SeanceRepository seanceRepository; private CategorieRepository categorieRepository;
    private FilmRepository filmRepository;     private ProjectionFilmRepository projectionFilmRepository;
    private TicketRepository ticketRepository;
    @Override
    public void initVille() {
        for (int i = 0 ; i < 3 ; i++){
            villeRepository.save(new Ville(null,"ville"+i,36.6,
                                        73.3, 30.5, null));
        }
    }

    @Override
    public void initCinemas() {

        for (int i = 0 ; i < 5 ; i++){
            Ville ville = villeRepository.findById(Math.random()>0.5?new Long(1):new Long(2)).orElse(null);
            cinemaRepository.save(new Cinema(null,"Cinema "+i, 36.6,
                                    73.3, 30.5,10,ville,null));
        }
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            salleRepository.save(new Salle(null, UUID.randomUUID().toString(),Math.random()>0.5?150:250,
                                            cinema,null,null));
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0; i<salle.getNombrePlaces();i++){
                placeRepository.save(new Place(null,i,16.6,
                        70.3, 33.5,salle,null ));
            }
        });
    }

    @Override
    public void initSeance() {
        for (int i = 0; i < 3; i++)
        seanceRepository.save(new Seance(null, new Date(), null));
    }

    @Override
    public void initCategories() {
        for (int i = 0; i < 10; i++)
            categorieRepository.save(new Categorie(null,"Categorie "+i, null));
    }

    @Override
    public void initFilms() {
        categorieRepository.findAll().forEach(categorie -> {
            filmRepository.save(new Film(null, UUID.randomUUID().toString(),Math.random()>0.5?245.1:155.4,
                            UUID.randomUUID().toString(),"Descreption rand",
                    "photo",new Date(),categorie,null));
        });
    }

    @Override
    public void initProjections() {
        seanceRepository.findAll().forEach(seance -> {
            salleRepository.findAll().forEach(salle -> {
                filmRepository.findAll().forEach(film -> {
                    projectionFilmRepository.save(new ProjectionFilm(null,new Date(),
                                                    Math.random()>0.5?125.6:100,seance,null,salle,film));

                });
            });
        });
    }

    @Override
    public void initTickets() {
        placeRepository.findAll().forEach(place -> {
            projectionFilmRepository.findAll().forEach(projectionFilm -> {
                ticketRepository.save(new Ticket(null, "client name", Math.random()>0.5?60:100,
                        Math.random()>0.5?1734178:122011, Math.random()>0.5?true:false,projectionFilm,place));
            });
        });
    }
}
