package com.novelis.novy.service.expenseReports;

import com.novelis.novy.enums.ApprovalStatus;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpenseReportService {
    List<ExpenseReportListDTO> getAllExpenseReports();

    ExpenseReportResponseDTO createExpenseReportWithoutMission(ExpenseReportRequestDTO expenseReportRequestDTO);
    ExpenseReportResponseDTO createExpenseReport(ExpenseReportRequestDTO expenseReportRequestDTO);

    public ExpenseReportResponseDTO updateExpenseReport(Long id, ExpenseReportRequestDTO requestDTO);
    public void deleteExpenseReport(Long id) ;
    ExpenseReportResponseDTO updateApprovalStatus(Long reportId, ApprovalStatus newStatus);
    public List<ExpenseReportListDTO> getExpenseReportsByMission(Long missionId) ;

    void approveExpenseReport(Long expenseReportId);
    void rejectExpenseReport(Long expenseReportId);
    void cancelExpenseReport(Long expenseReportId);
    void treatExpenseReport(Long expenseReportId);
}
