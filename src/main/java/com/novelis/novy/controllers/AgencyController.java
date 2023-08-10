package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.AgencyRequestDTO;
import com.novelis.novy.dto.dtoResponse.AgencyResponseDTO;
import com.novelis.novy.service.agency.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping
    public ResponseEntity<AgencyResponseDTO> createAgency(@RequestBody AgencyRequestDTO agencyRequestDTO) {
        AgencyResponseDTO createdAgency = agencyService.createAgency(agencyRequestDTO);
        return new ResponseEntity<>(createdAgency, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgencyResponseDTO>> getAllAgencies() {
        List<AgencyResponseDTO> agencies = agencyService.getAllAgencies();
        return new ResponseEntity<>(agencies, HttpStatus.OK);
    }
}
