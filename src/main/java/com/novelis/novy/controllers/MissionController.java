package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.service.mission.MissionService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@Tag(name = "Missions")
public class MissionController {

    private final MissionService missionService;
    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }
    @Operation(
            description = "get endpoint for missions"
    )
    @GetMapping
    public ResponseEntity<List<MissionResponseDTO>> getAllMissions() {
        List<MissionResponseDTO> missions = missionService.getAllMissions();
        return ResponseEntity.ok(missions);
    }
    @PostMapping
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        MissionResponseDTO createdMission = missionService.createMission(missionRequestDTO,null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }
    @PostMapping("/{missionId}/add-collaborators")
    public ResponseEntity<MissionResponseDTO> addCollaboratorsToMission(
            @PathVariable Long missionId,
            @RequestBody List<Long> collaboratorIds) {
        MissionResponseDTO updatedMission = missionService.addCollaboratorsToMission(missionId, collaboratorIds);

        return ResponseEntity.ok(updatedMission);
    }
    @PutMapping("/{missionId}/remove-collaborators")
    public ResponseEntity<MissionResponseDTO> removeCollaboratorsFromMission(
            @PathVariable Long missionId,
            @RequestBody List<Long> collaboratorIds
    ) {
        MissionResponseDTO updatedMission = missionService.removeCollaboratorsFromMission(missionId, collaboratorIds);
        return ResponseEntity.ok(updatedMission);
    }
    @GetMapping("/{missionId}/collaborators")
    public ResponseEntity<List<CollaboratorResponseDTO>> getCollaboratorsForMission(@PathVariable Long missionId) {
        List<CollaboratorResponseDTO> collaborators = missionService.getCollaboratorsForMission(missionId);
        return ResponseEntity.ok(collaborators);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{missionId}/expense-reports/{expenseReportId}")
    public ResponseEntity<MissionResponseDTO> addExpenseReportToMission(
            @PathVariable Long missionId, @PathVariable Long expenseReportId) {
        missionService.addExpenseReportToMission(missionId, expenseReportId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{missionId}/expense-reports/{expenseReportId}")
    public ResponseEntity<MissionResponseDTO> removeExpenseReportFromMission(
            @PathVariable Long missionId, @PathVariable Long expenseReportId) {
        missionService.removeExpenseReportFromMission(missionId, expenseReportId);
        return ResponseEntity.ok().build();
    }
    @Hidden
    @GetMapping("/{missionId}/expense-reports")
    public ResponseEntity<List<ExpenseReportListDTO>> getExpenseReportsByMission(
            @PathVariable Long missionId) {
        List<ExpenseReportListDTO> expenseReports = missionService.getMissionExpenseReports(missionId);
        return ResponseEntity.ok(expenseReports);
    }

}
