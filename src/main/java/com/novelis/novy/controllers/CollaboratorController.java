package com.novelis.novy.controllers;

import com.novelis.novy.dto.dtoRequest.CollaboratorRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborators")
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }
    @PostMapping
    public ResponseEntity<CollaboratorResponseDTO> createCollaborator(@RequestBody CollaboratorRequestDTO collaboratorRequestDTO){
        CollaboratorResponseDTO collaboratorResponseDTO = collaboratorService.createCollaborator(collaboratorRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(collaboratorResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<CollaboratorResponseDTO>> getAllCollaborators() {
        // Call collaboratorService to get all collaborators
        List<CollaboratorResponseDTO> collaborators = collaboratorService.getAllCollaborators();
        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/{collaboratorId}")
    public ResponseEntity<CollaboratorResponseDTO> getCollaboratorById(@PathVariable Long collaboratorId) {
        // Call collaboratorService to get a collaborator by ID
        CollaboratorResponseDTO collaborator = collaboratorService.getCollaboratorById(collaboratorId);
        return ResponseEntity.ok(collaborator);
    }

    @PutMapping("/{collaboratorId}")
    public ResponseEntity<CollaboratorResponseDTO> updateCollaborator(
            @PathVariable Long collaboratorId, @RequestBody CollaboratorRequestDTO collaboratorRequestDTO) {
        // Call collaboratorService to update a collaborator
        CollaboratorResponseDTO updatedCollaborator = collaboratorService.updateCollaborator(collaboratorId, collaboratorRequestDTO);
        return ResponseEntity.ok(updatedCollaborator);
    }

    @DeleteMapping("/{collaboratorId}")
    public ResponseEntity<String> deleteCollaborator(@PathVariable Long collaboratorId) {
        // Call collaboratorService to delete a collaborator
        collaboratorService.deleteCollaborator(collaboratorId);
        return ResponseEntity.ok("Collaborator deleted successfully");
    }
}
