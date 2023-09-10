package com.novelis.novy.Repository;

import com.novelis.novy.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator,Long> {
    Optional<Collaborator> findByEmail(String email);
}
