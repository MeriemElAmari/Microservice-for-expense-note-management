package com.novelis.novy.dto.dtoResponse;

import lombok.Data;

import java.util.Date;

@Data
public class MissionResponseDTO {
    private Long id;
    private Long collaboratorId;
    private String missionName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
}

