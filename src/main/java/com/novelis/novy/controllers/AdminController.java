package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.service.mission.MissionService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final MissionService missionService;
    @Autowired
    public AdminController(MissionService missionService) {
        this.missionService = missionService;
    }
    @GetMapping("/get-all-missions")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<MissionResponseDTO>> getAllMissions() {
        List<MissionResponseDTO> missions = missionService.getAllMissions();
        return ResponseEntity.ok(missions);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        MissionResponseDTO createdMission = missionService.createMission(missionRequestDTO,null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }
    @PostMapping("/{missionId}/add-collaborators")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<MissionResponseDTO> addCollaboratorsToMission(
            @PathVariable Long missionId,
            @RequestBody List<Long> collaboratorIds) {
        MissionResponseDTO updatedMission = missionService.addCollaboratorsToMission(missionId, collaboratorIds);

        return ResponseEntity.ok(updatedMission);
    }
    @PutMapping("/{missionId}/remove-collaborators")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<MissionResponseDTO> removeCollaboratorsFromMission(
            @PathVariable Long missionId,
            @RequestBody List<Long> collaboratorIds
    ) {
        MissionResponseDTO updatedMission = missionService.removeCollaboratorsFromMission(missionId, collaboratorIds);
        return ResponseEntity.ok(updatedMission);
    }
    @GetMapping("/{missionId}/collaborators")
    @PreAuthorize("hasAuthority('admin:read')")

    public ResponseEntity<List<CollaboratorResponseDTO>> getCollaboratorsForMission(@PathVariable Long missionId) {
        List<CollaboratorResponseDTO> collaborators = missionService.getCollaboratorsForMission(missionId);
        return ResponseEntity.ok(collaborators);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")

    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }

    @Hidden
    @GetMapping("/{missionId}/expense-reports")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ExpenseReportListDTO>> getExpenseReportsByMission(
            @PathVariable Long missionId) {
        List<ExpenseReportListDTO> expenseReports = missionService.getMissionExpenseReports(missionId);
        return ResponseEntity.ok(expenseReports);
    }
}
