package com.novelis.novy.service.mission;

import com.novelis.novy.Repository.CollaboratorRepository;
import com.novelis.novy.Repository.ExpenseReportRepository;
import com.novelis.novy.Repository.MissionRepository;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.dto.mappers.ExpenseReportMapper;
import com.novelis.novy.dto.mappers.MissionMapper;
import com.novelis.novy.model.Collaborator;
import com.novelis.novy.model.ExpenseReport;
import com.novelis.novy.model.Mission;
import com.novelis.novy.service.mission.MissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final ExpenseReportRepository expenseReportRepository;

    public MissionServiceImpl(MissionRepository missionRepository, CollaboratorRepository collaboratorRepository, ExpenseReportRepository expenseReportRepository) {
        this.missionRepository = missionRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.expenseReportRepository = expenseReportRepository;
    }

    @Override
    public MissionResponseDTO createMission(MissionRequestDTO missionRequestDTO, List<Long> collaboratorIds) {
        Mission mission = MissionMapper.INSTANCE.missionRequestToMissionEntity(missionRequestDTO);
        Mission savedMission = missionRepository.save(mission);

        if (collaboratorIds != null && !collaboratorIds.isEmpty()) {
            List<Collaborator> collaborators = collaboratorRepository.findAllById(collaboratorIds);
            savedMission.setCollaborators(collaborators);
        }

        MissionResponseDTO missionResponseDTO = MissionMapper.INSTANCE.missionEntitytoMissionResponseDTO(savedMission);
        return missionResponseDTO;
    }

    @Override
    public List<MissionResponseDTO> getAllMissions() {
        List<Mission> missions = missionRepository.findAll();
        List<MissionResponseDTO> missionResponseDTOs = MissionMapper.INSTANCE.missionListToMissionResponseList(missions);
        return missionResponseDTOs;    }

    @Override
    public MissionResponseDTO getMissionById(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Mission Not found"));

        MissionResponseDTO missionResponseDTO = MissionMapper.INSTANCE.missionEntitytoMissionResponseDTO(mission);
        return missionResponseDTO;
    }

    @Override
    public MissionResponseDTO updateMission(Long id, MissionRequestDTO missionRequestDTO) {
        Mission mission = missionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(("mission with id : "+id+" does not exists")));

        mission.setMissionName(missionRequestDTO.getMissionName());
        mission.setDescription(missionRequestDTO.getDescription());
        mission.setStatus(missionRequestDTO.getStatus());
        mission.setStartDate(missionRequestDTO.getStartDate());
        mission.setEndDate(missionRequestDTO.getEndDate());

        MissionResponseDTO missionResponseDTO = MissionMapper.INSTANCE.missionEntitytoMissionResponseDTO(mission);
        return missionResponseDTO;
    }

    @Override
    public void deleteMission(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Mission not found"));
        missionRepository.delete(mission);
    }

    @Override
    public void addExpenseReportToMission(Long missionId, Long expenseReportId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(()-> new EntityNotFoundException("Mission not found"));
        ExpenseReport expenseReport = expenseReportRepository.findById(expenseReportId).orElseThrow(()-> new EntityNotFoundException("Expense Report Not Found"));

        mission.getExpenseReports().add(expenseReport);
        expenseReport.setMission(mission);

        missionRepository.save(mission);
        expenseReportRepository.save(expenseReport);
    }

    @Override
    public List<ExpenseReportListDTO> getMissionExpenseReports(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(()-> new EntityNotFoundException("Mission not found"));
        List<ExpenseReport> expenseReports =mission.getExpenseReports();
        return ExpenseReportMapper.INSTANCE.expenseReportListToExpenseReportResponseList(expenseReports);
    }

    @Override
    public void updateExpenseReportInMission(Long missionId, Long expenseReportId, ExpenseReportRequestDTO expenseReportRequestDTO) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(()-> new EntityNotFoundException("Mission not found"));
        ExpenseReport expenseReport = mission.getExpenseReports().stream().filter(er -> er.getReportID().equals(expenseReportId)).findFirst().
                        orElseThrow(()->new EntityNotFoundException("Expense Report Not Found"));

        expenseReport.setDescription(expenseReportRequestDTO.getDescription());
        expenseReport.setApprovalStatus(expenseReportRequestDTO.getStatus());
        expenseReport.setAmount(expenseReportRequestDTO.getAmount());
        expenseReport.setReceipt(expenseReportRequestDTO.getReceipt());

        expenseReportRepository.save(expenseReport);
    }

    @Override
    public void deleteExpenseReportFromMission(Long missionId, Long expenseReportId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(()-> new EntityNotFoundException("Mission not found"));
        ExpenseReport expenseReport = mission.getExpenseReports().stream().filter(er -> er.getReportID().equals(expenseReportId)).findFirst().
                orElseThrow(()->new EntityNotFoundException("Expense Report Not Found"));

        mission.getExpenseReports().remove(expenseReport);
        missionRepository.save(mission);

        expenseReportRepository.delete(expenseReport);

    }

    @Override
    public MissionResponseDTO addCollaboratorsToMission(Long missionId, List<Long> collaboratorIds) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        List<Collaborator> collaborators = collaboratorRepository.findAllById(collaboratorIds);

        mission.getCollaborators().addAll(collaborators);
        missionRepository.save(mission);

        collaborators.forEach(collaborator -> collaborator.getMissions().add(mission));
        collaboratorRepository.saveAll(collaborators);
        return MissionMapper.INSTANCE.missionEntitytoMissionResponseDTO(mission);
    }

    @Override
    public void removeCollaboratorsFromMission(Long missionId, List<Long> collaboratorIds) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        List<Collaborator> collaboratorsToRemove = collaboratorRepository.findAllById(collaboratorIds);

        mission.getCollaborators().removeAll(collaboratorsToRemove);
        missionRepository.save(mission);

        collaboratorsToRemove.forEach(collaborator -> collaborator.getMissions().remove(mission));
        collaboratorRepository.saveAll(collaboratorsToRemove);
    }

}
