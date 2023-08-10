package com.novelis.novy.dto.dtoResponse;



import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long id;
    private Long collaboratorId; // Include the Collaborator ID here
    private String username;
    private String accountType;
}


