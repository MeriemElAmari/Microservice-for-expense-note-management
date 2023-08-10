package com.novelis.novy.service;

import com.novelis.novy.Repository.JobRepository;
import com.novelis.novy.dto.dtoRequest.JobRequestDTO;
import com.novelis.novy.dto.dtoResponse.JobResponseDTO;
import com.novelis.novy.dto.mappers.JobMapper;
import com.novelis.novy.model.Job;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        Job job = JobMapper.INSTANCE.requestToEntity(jobRequestDTO);
        Job savedJob = jobRepository.save(job);
        return JobMapper.INSTANCE.entityToResponse(savedJob);
    }

    @Override
    public List<JobResponseDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        return JobMapper.INSTANCE.entityToResponse(job);
    }

    @Override
    public JobResponseDTO updateJob(Long id, JobRequestDTO jobRequestDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        job.setJobName(jobRequestDTO.getJobName());
        job.setDescription(jobRequestDTO.getDescription());

        Job updatedJob = jobRepository.save(job);
        return JobMapper.INSTANCE.entityToResponse(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found");
        }
        jobRepository.deleteById(id);
    }
}
