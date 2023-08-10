package com.novelis.novy.service.agency;


import com.novelis.novy.dto.dtoRequest.AgencyRequestDTO;
import com.novelis.novy.dto.dtoResponse.AgencyResponseDTO;

import java.util.List;

public interface AgencyService {

    AgencyResponseDTO createAgency(AgencyRequestDTO agencyRequestDTO);

    List<AgencyResponseDTO> getAllAgencies();
}