package com.novelis.novy.service;

import com.novelis.novy.dto.dtoRequest.*;
import com.novelis.novy.dto.dtoResponse.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CollaboratorService {
    public CollaboratorResponseDTO createCollaborator(CollaboratorRequestDTO collaboratorRequestDTO);

    public List<CollaboratorResponseDTO> getAllCollaborators();

    public CollaboratorResponseDTO getCollaboratorById(Long id);

    public CollaboratorResponseDTO updateCollaborator(Long id, CollaboratorRequestDTO collaboratorRequestDTO);

    public void deleteCollaborator(Long id);

    CollaboratorResponseDTO addMissionToCollaborator(Long collaboratorId, Long missionId);

    CollaboratorResponseDTO removeMissionFromCollaborator(Long collaboratorId, Long missionId);

    public List<MissionResponseDTO> getCollaboratorMissions(Long collaboratorId);
    public MissionResponseDTO updateMissionForCollaborator(Long collaboratorId, Long missionId, MissionRequestDTO missionRequestDTO) ;
    public CollaboratorResponseDTO addExpenseReportToCollaborator(Long collaboratorId, ExpenseReportRequestDTO expenseReportRequestDTO) ;
    public List<ExpenseReportListDTO> getExpenseReportsForCollaborator(Long collaboratorId) ;


    }
