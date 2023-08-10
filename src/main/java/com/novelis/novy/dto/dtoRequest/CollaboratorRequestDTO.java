package com.novelis.novy.dto.dtoRequest;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CollaboratorRequestDTO {
    private String code;
    private String firstname;
    private String lastname;
    private String title;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private Date startDate;
    private String image;
    private Long jobId;
    private Long departmentId;
    private Long agencyId;
    private List<Long> missions = new ArrayList<>();

}
