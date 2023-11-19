package com.novelis.novy.service.expenseReports;

import com.novelis.novy.enums.ApprovalStatus;
import com.novelis.novy.Repository.ExpenseReportRepository;
import com.novelis.novy.Repository.MissionRepository;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import com.novelis.novy.dto.mappers.ExpenseReportMapper;
import com.novelis.novy.model.ExpenseReport;
import com.novelis.novy.model.Mission;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ExpenseReportServiceImpl implements ExpenseReportService {
    private final ExpenseReportRepository expenseReportRepository;
    private final MissionRepository missionRepository;

    public ExpenseReportServiceImpl(ExpenseReportRepository expenseReportRepository, MissionRepository missionRepository) {
        this.expenseReportRepository = expenseReportRepository;
        this.missionRepository = missionRepository;
    }

    @Override
    public List<ExpenseReportListDTO> getAllExpenseReports() {
        List<ExpenseReport> expenseReports = expenseReportRepository.findAll();
        return ExpenseReportMapper.INSTANCE.expenseReportListToExpenseReportResponseList(expenseReports);    }

    @Override
    public ExpenseReportResponseDTO createExpenseReportWithoutMission(ExpenseReportRequestDTO expenseReportRequestDTO) {
        return null;
    }

    @Override
    public ExpenseReportResponseDTO createExpenseReport(ExpenseReportRequestDTO requestDTO) {
        Mission mission = missionRepository.findById(requestDTO.getMissionId())
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.setReportDate(requestDTO.getReportDate());
        expenseReport.setDescription(requestDTO.getDescription());
        expenseReport.setAmount(requestDTO.getAmount());
        expenseReport.setApprovalStatus(requestDTO.getStatus());
        expenseReport.setReceipt(requestDTO.getReceipt());
        expenseReport.setMission(mission);

        ExpenseReport savedExpenseReport = expenseReportRepository.save(expenseReport);

        return ExpenseReportMapper.INSTANCE.expenseReportEntitytoExpenseReportResponseDTO(savedExpenseReport);
    }

    @Override
    public ExpenseReportResponseDTO updateExpenseReport(Long id, ExpenseReportRequestDTO requestDTO) {
        ExpenseReport expenseReport = expenseReportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        // Update fields with data from the request DTO
        expenseReport.setReportDate(requestDTO.getReportDate());
        expenseReport.setDescription(requestDTO.getDescription());
        expenseReport.setAmount(requestDTO.getAmount());
        expenseReport.setApprovalStatus(requestDTO.getStatus());
        expenseReport.setReceipt(requestDTO.getReceipt());

        ExpenseReport updatedExpenseReport = expenseReportRepository.save(expenseReport);
        return ExpenseReportMapper.INSTANCE.expenseReportEntitytoExpenseReportResponseDTO(updatedExpenseReport);
    }

    @Override
    public void deleteExpenseReport(Long id) {
        ExpenseReport expenseReport = expenseReportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        expenseReportRepository.delete(expenseReport);
    }

    @Override
    public ExpenseReportResponseDTO updateApprovalStatus(Long reportId, ApprovalStatus newStatus) {
        ExpenseReport expenseReport = expenseReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Expense report not found"));

        expenseReport.setApprovalStatus(newStatus);
        ExpenseReport updatedExpenseReport = expenseReportRepository.save(expenseReport);

        return ExpenseReportMapper.INSTANCE.expenseReportEntitytoExpenseReportResponseDTO(updatedExpenseReport);
    }

    @Override
    public List<ExpenseReportListDTO> getExpenseReportsByMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        List<ExpenseReport> expenseReports = mission.getExpenseReports();

        // Convert the list of expense reports to a list of DTOs
        return ExpenseReportMapper.INSTANCE.expenseReportListToExpenseReportResponseList(expenseReports);
    }
    @Override
    public ExpenseReportResponseDTO approveExpenseReport(Long expenseReportId) {
        ExpenseReport expenseReport = expenseReportRepository.findById(expenseReportId)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        expenseReport.setApprovalStatus(ApprovalStatus.APPROVED);
        expenseReportRepository.save(expenseReport);
        return null;
    }

    @Override
    public ExpenseReportResponseDTO rejectExpenseReport(Long expenseReportId) {
        ExpenseReport expenseReport = expenseReportRepository.findById(expenseReportId)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        expenseReport.setApprovalStatus(ApprovalStatus.REJECTED);
        expenseReportRepository.save(expenseReport);
        return null;
    }

    @Override
    public ExpenseReportResponseDTO cancelExpenseReport(Long expenseReportId) {
        ExpenseReport expenseReport = expenseReportRepository.findById(expenseReportId)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        expenseReport.setApprovalStatus(ApprovalStatus.CANCELED);
        expenseReportRepository.save(expenseReport);
        return null;
    }

    @Override
    public ExpenseReportResponseDTO treatExpenseReport(Long expenseReportId) {
        ExpenseReport expenseReport = expenseReportRepository.findById(expenseReportId)
                .orElseThrow(() -> new EntityNotFoundException("Expense Report not found"));

        expenseReport.setApprovalStatus(ApprovalStatus.TREATED);
        expenseReportRepository.save(expenseReport);
        return null;
    }

}

