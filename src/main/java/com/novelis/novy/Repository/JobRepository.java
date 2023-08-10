package com.novelis.novy.Repository;

import com.novelis.novy.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
