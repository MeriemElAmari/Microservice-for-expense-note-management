package com.novelis.novy.dto.dtoResponse;


import lombok.Data;

import java.util.Date;

@Data
public class MissionListDTO {
    private Long id;
    private String missionName;
    private String status;
}

