package com.cinema.management.cinemamanagement.services.Impl;

import com.cinema.management.cinemamanagement.dtos.SeanceDto;
import com.cinema.management.cinemamanagement.entities.Seance;
import com.cinema.management.cinemamanagement.repositories.SeanceRepository;
import com.cinema.management.cinemamanagement.services.ISeanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SeanceServiceImpl implements ISeanceService {
    private SeanceRepository seanceRepository;
    @Override
    public SeanceDto addNewSeance(SeanceDto seanceDto) {
        ModelMapper modelMapper = new ModelMapper();
        Seance seance = modelMapper.map(seanceDto, Seance.class);
        Seance newSeance = seanceRepository.save(seance);
        SeanceDto seanceDtoNew = modelMapper.map(newSeance, SeanceDto.class);

        return seanceDtoNew;
    }

    @Override
    public SeanceDto getSeanceByDate(SeanceDto seanceDto) {
        Seance seance = seanceRepository.findByHeureDebut(seanceDto.getHeureDebut());

        return new ModelMapper().map(seance, SeanceDto.class);
    }

    @Override
    public SeanceDto getSeanceById(Long id) {
        Seance seance = seanceRepository.findById(id).orElse(null);
        return new ModelMapper().map(seance, SeanceDto.class);
    }

    @Override
    public List<SeanceDto> getSeances(int page, int size) {
        Page<Seance> seancePage = seanceRepository.findAll(PageRequest.of(page, size));
        List<Seance> seanceList = seancePage.getContent();
        ModelMapper modelMapper = new ModelMapper();
        List<SeanceDto> seanceDtoList = new ArrayList<>();

        seanceList.forEach(seance -> {
            seanceDtoList.add(modelMapper.map(seance, SeanceDto.class));
        });


        return seanceDtoList;
    }

    @Override
    public SeanceDto updateNewSeance(Long id, SeanceDto seanceDto) {
        Seance seance = seanceRepository.findById(id).orElse(null);
        seance.setHeureDebut(seanceDto.getHeureDebut());
        SeanceDto seanceDtoUpdated = new ModelMapper().map(seance, SeanceDto.class);
        return seanceDtoUpdated;
    }

    @Override
    public void deteleSeance(Long id) {
        seanceRepository.deleteById(id);
    }
}
