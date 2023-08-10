package com.novelis.novy.service.agency;

import com.novelis.novy.dto.dtoRequest.AgencyRequestDTO;
import com.novelis.novy.dto.dtoResponse.AgencyResponseDTO;
import com.novelis.novy.dto.mappers.AgencyMapper;
import com.novelis.novy.model.Agency;
import com.novelis.novy.Repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public AgencyResponseDTO createAgency(AgencyRequestDTO agencyRequestDTO) {
        Agency agency = AgencyMapper.INSTANCE.requestToEntity(agencyRequestDTO);
        Agency savedAgency = agencyRepository.save(agency);
        return AgencyMapper.INSTANCE.entityToResponse(savedAgency);
    }

    @Override
    public List<AgencyResponseDTO> getAllAgencies() {
        List<Agency> agencies = agencyRepository.findAll();
        return agencies.stream()
                .map(AgencyMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }
}
