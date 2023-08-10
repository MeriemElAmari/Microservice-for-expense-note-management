package com.novelis.novy.dto.dtoRequest;


import com.novelis.novy.enums.ApprovalStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseReportRequestDTO {
    private Date reportDate;
    private String description;
    private BigDecimal amount;
    private ApprovalStatus status;
    private String receipt;
    private Long collaboratorId;
    private Long missionId;
}

