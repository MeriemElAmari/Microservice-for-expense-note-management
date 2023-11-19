package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.service.CollaboratorService;
import com.novelis.novy.service.expenseReports.ExpenseReportService;
import com.novelis.novy.service.mission.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final MissionService missionService;
    private final CollaboratorService collaboratorService;
    private final ExpenseReportService expenseReportService;

    public UserController(MissionService missionService, CollaboratorService collaboratorService, ExpenseReportService expenseReportService) {
        this.missionService = missionService;
        this.collaboratorService = collaboratorService;
        this.expenseReportService = expenseReportService;
    }

    @GetMapping("/missions/{collaboratorId}")
    public ResponseEntity<List<MissionResponseDTO>> viewMissions(@PathVariable Long collaboratorId) {
        List<MissionResponseDTO> missions = collaboratorService.getCollaboratorMissions(collaboratorId);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/expense-reports")
    public ResponseEntity<List<ExpenseReportListDTO>> viewExpenseReports(@PathVariable Long collaboratorId) {
        List<ExpenseReportListDTO> expenseReports = collaboratorService.getExpenseReportsForCollaborator(collaboratorId);
        return ResponseEntity.ok(expenseReports);
    }


    @GetMapping("/add-mission")
    public ResponseEntity<MissionResponseDTO> addMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        MissionResponseDTO createdMission = missionService.createMission(missionRequestDTO,null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseReportResponseDTO> createExpenseReport(@RequestBody ExpenseReportRequestDTO requestDTO) {
        ExpenseReportResponseDTO createdExpenseReport = expenseReportService.createExpenseReport(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpenseReport);
    }
}
