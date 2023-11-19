package com.novelis.novy.service;

import com.novelis.novy.Repository.*;

import com.novelis.novy.dto.dtoRequest.*;
import com.novelis.novy.dto.dtoResponse.*;
import com.novelis.novy.dto.mappers.CollaboratorMapper;

import com.novelis.novy.dto.mappers.ExpenseReportMapper;
import com.novelis.novy.dto.mappers.MissionMapper;
import com.novelis.novy.model.*;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollaboratorServiceImpl implements CollaboratorService {
    CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final AgencyRepository agencyRepository;
    private final MissionRepository missionRepository;
    private final ExpenseReportRepository expenseReportRepository;
    @Autowired
    public CollaboratorServiceImpl(CollaboratorRepository collaboratorRepository, JobRepository jobRepository, DepartmentRepository departmentRepository, AgencyRepository agencyRepository, MissionRepository missionRepository, ExpenseReportRepository expenseReportRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
        this.agencyRepository = agencyRepository;
        this.missionRepository = missionRepository;
        this.expenseReportRepository = expenseReportRepository;
    }

    @Override
    public CollaboratorResponseDTO createCollaborator(CollaboratorRequestDTO collaboratorRequestDTO) {
        Collaborator collaborator = CollaboratorMapper.INSTANCE.collaboratorRequestToCollaboratorEntity(collaboratorRequestDTO);

        // Fetch related entities using their IDs
        Job job = jobRepository.findById(collaboratorRequestDTO.getJobId()).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Department department = departmentRepository.findById(collaboratorRequestDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department not found"));
        Agency agency = agencyRepository.findById(collaboratorRequestDTO.getAgencyId()).orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        // Set the fetched related entities to the collaborator
        collaborator.setJob(job);
        collaborator.setDepartment(department);
        collaborator.setAgency(agency);

        Collaborator savedCollaborator = collaboratorRepository.save(collaborator);
        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(savedCollaborator);
    }


    @Override
    public List<CollaboratorResponseDTO> getAllCollaborators() {
        List<Collaborator> collaborators = collaboratorRepository.findAll();
        return CollaboratorMapper.INSTANCE.collaboratorListToCollaboratorResponseList(collaborators);
    }

    @Override
    public CollaboratorResponseDTO getCollaboratorById(Long id) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found with id: " + id));

        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(collaborator);
    }

    @Override
    public CollaboratorResponseDTO updateCollaborator(Long id, CollaboratorRequestDTO collaboratorRequestDTO) {
        Collaborator existingCollaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found with id: " + id));

        // Update collaborator details from the request DTO
        existingCollaborator.setCode(collaboratorRequestDTO.getCode());
        existingCollaborator.setFirstname(collaboratorRequestDTO.getFirstname());
        existingCollaborator.setLastname(collaboratorRequestDTO.getLastname());
        // ... Update other fields as needed

        // Fetch related entities using their IDs
        Job job = jobRepository.findById(collaboratorRequestDTO.getJobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Department department = departmentRepository.findById(collaboratorRequestDTO.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        Agency agency = agencyRepository.findById(collaboratorRequestDTO.getAgencyId())
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        // Set the fetched related entities to the collaborator
        existingCollaborator.setJob(job);
        existingCollaborator.setDepartment(department);
        existingCollaborator.setAgency(agency);

        Collaborator updatedCollaborator = collaboratorRepository.save(existingCollaborator);
        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(updatedCollaborator);
    }


    @Override
    public void deleteCollaborator(Long id) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found with id: " + id));

        collaboratorRepository.delete(collaborator);
    }

    @Override
    public CollaboratorResponseDTO addMissionToCollaborator(Long collaboratorId, Long missionId) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        collaborator.getMissions().add(mission);
        Collaborator savedCollaborator = collaboratorRepository.save(collaborator);
        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(savedCollaborator);
    }


    @Override
    public CollaboratorResponseDTO removeMissionFromCollaborator(Long collaboratorId, Long missionId) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        collaborator.getMissions().remove(mission);
        Collaborator savedCollaborator = collaboratorRepository.save(collaborator);
        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(savedCollaborator);
    }

    public List<MissionResponseDTO> getCollaboratorMissions(Long collaboratorId) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        List<Mission> missions = collaborator.getMissions();
        return MissionMapper.INSTANCE.missionListToMissionResponseList(missions);
    }

    @Override
    public MissionResponseDTO updateMissionForCollaborator(Long collaboratorId, Long missionId, MissionRequestDTO missionRequestDTO) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        Mission missionToUpdate = collaborator.getMissions()
                .stream()
                .filter(mission -> mission.getId().equals(missionId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Mission not found for the collaborator"));

        // Update mission details based on missionRequestDTO
        missionToUpdate.setMissionName(missionRequestDTO.getMissionName());
        missionToUpdate.setDescription(missionRequestDTO.getDescription());
        missionToUpdate.setStartDate(missionRequestDTO.getStartDate());
        missionToUpdate.setEndDate(missionRequestDTO.getEndDate());
        missionToUpdate.setStatus(missionRequestDTO.getStatus());

        Mission updatedMission = missionRepository.save(missionToUpdate);
        return MissionMapper.INSTANCE.missionEntitytoMissionResponseDTO(updatedMission);    }

    @Override
    public CollaboratorResponseDTO addExpenseReportToCollaborator(Long collaboratorId, ExpenseReportRequestDTO expenseReportRequestDTO) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        ExpenseReport expenseReport = ExpenseReportMapper.INSTANCE.expenseReportRequestToExpenseReportEntity(expenseReportRequestDTO);
        collaborator.getExpenseReports().add(expenseReport);
        expenseReport.setCollaborator(collaborator);

        ExpenseReport savedExpenseReport = expenseReportRepository.save(expenseReport);
        return CollaboratorMapper.INSTANCE.collaboratorEntityToCollaboratorResponseDTO(collaborator);
    }

    @Override
    public List<ExpenseReportListDTO> getExpenseReportsForCollaborator(Long collaboratorId) {
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        List<ExpenseReport> expenseReports = collaborator.getExpenseReports();
        return ExpenseReportMapper.INSTANCE.expenseReportListToExpenseReportResponseList(expenseReports);
    }

}