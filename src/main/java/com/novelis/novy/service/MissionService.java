package com.novelis.novy.service;

import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.model.ExpenseReport;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MissionService {
    public MissionResponseDTO createMissionForCollaborator(Long collaboratorId, MissionRequestDTO missionRequestDTO);
    public List<MissionResponseDTO> getAllMissions();
    public MissionResponseDTO getMissionById(Long id);

    public  MissionResponseDTO updateMission(Long id , MissionRequestDTO missionRequestDTO);
    public  void deleteMission (Long id);
    public void addExpenseReportToMission(Long missionId,Long expenseReportId);
    public List<ExpenseReportListDTO> getMissionExpenseReports(Long missionId);
    public void updateExpenseReportInMission(Long missionId, Long expenseReportId , ExpenseReportRequestDTO expenseReportRequestDTO);
    public void deleteExpenseReportFromMission(Long missionId, Long expenseReportId );

    }
