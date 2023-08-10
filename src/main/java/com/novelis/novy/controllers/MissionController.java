package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionService missionService;
    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }
    @PostMapping
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        MissionResponseDTO createdMission = missionService.createMissionForCollaborator(missionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }
}
