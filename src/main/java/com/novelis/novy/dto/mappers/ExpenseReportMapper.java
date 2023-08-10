package com.novelis.novy.dto.mappers;

import com.novelis.novy.dto.dtoResponse.ExpenseReportListDTO;
import com.novelis.novy.dto.dtoResponse.ExpenseReportResponseDTO;
import com.novelis.novy.dto.dtoRequest.ExpenseReportRequestDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.model.ExpenseReport;
import com.novelis.novy.model.Mission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExpenseReportMapper {
    ExpenseReportMapper INSTANCE = Mappers.getMapper(ExpenseReportMapper.class);

    ExpenseReportResponseDTO expenseReportEntitytoExpenseReportResponseDTO(ExpenseReport expenseReport);

    ExpenseReport expenseReportRequestToExpenseReportEntity(ExpenseReportRequestDTO expenseReportRequestDTO);
    List<ExpenseReportListDTO> expenseReportListToExpenseReportResponseList(List<ExpenseReport> expenseReports);

}
