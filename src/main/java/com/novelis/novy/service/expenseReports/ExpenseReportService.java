package com.novelis.novy.service.expenseReports;

import com.novelis.novy.enums.ApprovalStatus;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;

import java.util.List;

public interface ExpenseReportService {
    List<ExpenseReportListDTO> getAllExpenseReports();

    ExpenseReportResponseDTO createExpenseReportWithoutMission(ExpenseReportRequestDTO expenseReportRequestDTO);
    ExpenseReportResponseDTO createExpenseReport(ExpenseReportRequestDTO expenseReportRequestDTO);

    public ExpenseReportResponseDTO updateExpenseReport(Long id, ExpenseReportRequestDTO requestDTO);
    public void deleteExpenseReport(Long id) ;
    ExpenseReportResponseDTO updateApprovalStatus(Long reportId, ApprovalStatus newStatus);
    public List<ExpenseReportListDTO> getExpenseReportsByMission(Long missionId) ;

    ExpenseReportResponseDTO approveExpenseReport(Long expenseReportId);
    ExpenseReportResponseDTO rejectExpenseReport(Long expenseReportId);
    ExpenseReportResponseDTO cancelExpenseReport(Long expenseReportId);
    ExpenseReportResponseDTO treatExpenseReport(Long expenseReportId);
}
