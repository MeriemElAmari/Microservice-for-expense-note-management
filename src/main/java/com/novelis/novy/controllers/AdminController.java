package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.service.CollaboratorService;
import com.novelis.novy.service.expenseReports.ExpenseReportService;
import com.novelis.novy.service.mission.MissionService;
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
    private final ExpenseReportService expenseReportService;
    private final CollaboratorService collaboratorService;

    @Autowired
    public AdminController(MissionService missionService, ExpenseReportService expenseReportService, CollaboratorService collaboratorService) {
        this.missionService = missionService;
        this.expenseReportService = expenseReportService;
        this.collaboratorService = collaboratorService;
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

    @GetMapping("/{missionId}/expense-reports")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ExpenseReportListDTO>> getExpenseReportsByMission(
            @PathVariable Long missionId) {
        List<ExpenseReportListDTO> expenseReports = missionService.getMissionExpenseReports(missionId);
        return ResponseEntity.ok(expenseReports);
    }
    @GetMapping("/{collaboratorId}/missions")
    public ResponseEntity<List<MissionResponseDTO>> getMissionsByCollaborator(@PathVariable Long collaboratorId) {
        List<MissionResponseDTO> missions = collaboratorService.getCollaboratorMissions(collaboratorId);

        return ResponseEntity.ok(missions);
    }
    @GetMapping("/{collaboratorId}/expense-reports")
    public ResponseEntity<List<ExpenseReportListDTO>> getExpenseReportsByCollaborator(@PathVariable Long collaboratorId) {
        List<ExpenseReportListDTO> expenseReports = collaboratorService.getExpenseReportsForCollaborator(collaboratorId);

        return ResponseEntity.ok(expenseReports);
    }

    @GetMapping("/all-expense-reports")
    public ResponseEntity<List<ExpenseReportListDTO>> getAllExpenseReports() {
        List<ExpenseReportListDTO> expenseReports = expenseReportService.getAllExpenseReports();
        return ResponseEntity.ok(expenseReports);
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseReportResponseDTO> createExpenseReport(@RequestBody ExpenseReportRequestDTO requestDTO) {
        ExpenseReportResponseDTO createdExpenseReport = expenseReportService.createExpenseReport(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpenseReport);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseReportResponseDTO> updateExpenseReport(
            @PathVariable Long id,
            @RequestBody ExpenseReportRequestDTO requestDTO
    ) {
        ExpenseReportResponseDTO updatedExpenseReport = expenseReportService.updateExpenseReport(id, requestDTO);
        return ResponseEntity.ok(updatedExpenseReport);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpenseReport(@PathVariable Long id) {
        expenseReportService.deleteExpenseReport(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/approve/{expenseReportId}")
    public ResponseEntity<ExpenseReportResponseDTO> approveExpenseReport(
            @PathVariable Long expenseReportId
    ) {
        ExpenseReportResponseDTO approvedExpenseReport = expenseReportService.approveExpenseReport(expenseReportId);
        return ResponseEntity.ok(approvedExpenseReport);
    }

    @PutMapping("/reject/{expenseReportId}")
    public ResponseEntity<ExpenseReportResponseDTO> rejectExpenseReport(
            @PathVariable Long expenseReportId
    ) {
        ExpenseReportResponseDTO rejectedExpenseReport = expenseReportService.rejectExpenseReport(expenseReportId);
        return ResponseEntity.ok(rejectedExpenseReport);
    }

    @PutMapping("/cancel/{expenseReportId}")
    public ResponseEntity<ExpenseReportResponseDTO> cancelExpenseReport(
            @PathVariable Long expenseReportId
    ) {
        ExpenseReportResponseDTO canceledExpenseReport = expenseReportService.cancelExpenseReport(expenseReportId);
        return ResponseEntity.ok(canceledExpenseReport);
    }

    @PutMapping("/treat/{expenseReportId}")
    public ResponseEntity<ExpenseReportResponseDTO> treatExpenseReport(
            @PathVariable Long expenseReportId
    ) {
        ExpenseReportResponseDTO treatedExpenseReport = expenseReportService.treatExpenseReport(expenseReportId);
        return ResponseEntity.ok(treatedExpenseReport);
    }


}
