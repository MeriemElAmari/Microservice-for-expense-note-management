package com.novelis.novy.service.department;

import com.novelis.novy.dto.dtoRequest.DepartmentRequestDTO;
import com.novelis.novy.dto.dtoResponse.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO getDepartmentById(Long id);
}
