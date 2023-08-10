package com.novelis.novy.service;

import com.novelis.novy.dto.dtoRequest.JobRequestDTO;
import com.novelis.novy.dto.dtoResponse.JobResponseDTO;

import java.util.List;

public interface JobService {
    JobResponseDTO createJob(JobRequestDTO jobRequestDTO);
    List<JobResponseDTO> getAllJobs();
    JobResponseDTO getJobById(Long id);
    JobResponseDTO updateJob(Long id, JobRequestDTO jobRequestDTO);
    void deleteJob(Long id);
}
