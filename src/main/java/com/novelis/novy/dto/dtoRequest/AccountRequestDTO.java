package com.novelis.novy.dto.dtoRequest;


import lombok.Data;

@Data
public class AccountRequestDTO {
    // Mandatory fields for account creation
    private Long collaboratorId;
    private String username;
    private String password;

    // Optional fields for account update
    private String accountType;
}

