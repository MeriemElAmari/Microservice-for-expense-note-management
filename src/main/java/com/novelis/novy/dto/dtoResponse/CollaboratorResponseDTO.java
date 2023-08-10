package com.novelis.novy.dto.dtoResponse;


import lombok.Data;

import java.util.Date;

@Data
public class CollaboratorResponseDTO {
    private Long idCollaborator;
    private String code;
    private String firstname;
    private String lastname;
    private String title;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String image;
    // Exclude createdAt, updatedAt, and deletedAt => database information
    // Exclude jobId, departmentId, and agencyId => unnecessary relationships
}

