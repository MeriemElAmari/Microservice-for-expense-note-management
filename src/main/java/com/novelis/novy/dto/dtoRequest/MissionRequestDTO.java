package com.novelis.novy.dto.dtoRequest;


import lombok.Data;

import java.util.Date;

@Data
public class MissionRequestDTO {
    private String missionName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
}

