package com.novelis.novy.dto.dtoResponse;


import com.novelis.novy.enums.ApprovalStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseReportListDTO {
    private Long reportID;
    private Date reportDate;
    private String description;
    private ApprovalStatus status;
    private Long collaboratorId;

}

