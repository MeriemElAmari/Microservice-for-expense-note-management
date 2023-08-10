package com.novelis.novy.service;

import com.novelis.novy.enums.ApprovalStatus;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpenseReportService {
    List<ExpenseReportListDTO> getAllExpenseReports();

    ExpenseReportResponseDTO createExpenseReport(ExpenseReportRequestDTO requestDTO);
    public ExpenseReportResponseDTO updateExpenseReport(Long id, ExpenseReportRequestDTO requestDTO);
    public void deleteExpenseReport(Long id) ;
    ExpenseReportResponseDTO updateApprovalStatus(Long reportId, ApprovalStatus newStatus);


}
