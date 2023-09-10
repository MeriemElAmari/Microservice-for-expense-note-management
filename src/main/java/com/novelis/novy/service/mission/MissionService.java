package com.novelis.novy.service.mission;

import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;

import java.util.List;

public interface MissionService {
    MissionResponseDTO createMission(MissionRequestDTO missionRequestDTO, List<Long> collaboratorIds);
     List<MissionResponseDTO> getAllMissions();
     MissionResponseDTO getMissionById(Long id);

      MissionResponseDTO updateMission(Long id , MissionRequestDTO missionRequestDTO);
      void deleteMission (Long id);
     void addExpenseReportToMission(Long missionId,Long expenseReportId);
     List<ExpenseReportListDTO> getMissionExpenseReports(Long missionId);
     void updateExpenseReportInMission(Long missionId, Long expenseReportId , ExpenseReportRequestDTO expenseReportRequestDTO);
     void removeExpenseReportFromMission(Long missionId, Long expenseReportId );
     MissionResponseDTO addCollaboratorsToMission(Long missionId, List<Long> collaboratorIds) ;
     MissionResponseDTO removeCollaboratorsFromMission(Long missionId, List<Long> collaboratorIds) ;
     public List<CollaboratorResponseDTO> getCollaboratorsForMission(Long missionId) ;

 }
