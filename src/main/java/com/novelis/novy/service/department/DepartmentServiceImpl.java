package com.novelis.novy.service.department;

import com.novelis.novy.Repository.DepartmentRepository;
import com.novelis.novy.dto.dtoRequest.DepartmentRequestDTO;
import com.novelis.novy.dto.dtoResponse.DepartmentResponseDTO;

import java.util.List;

import com.novelis.novy.dto.mappers.DepartmentMapper;
import com.novelis.novy.model.Department;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = DepartmentMapper.INSTANCE.requestToEntity(departmentRequestDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.INSTANCE.entityToResponse(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return DepartmentMapper.INSTANCE.listToResponseList(departments);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department Not Fond"));

        return DepartmentMapper.INSTANCE.entityToResponse(department);
    }

}

