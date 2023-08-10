package com.novelis.novy.dto.dtoResponse;

import lombok.Data;

@Data
public class AgencyResponseDTO {
    private Long id;
    private String agencyName;
    private String address;
    private String city;
    private String postalCode;
}
