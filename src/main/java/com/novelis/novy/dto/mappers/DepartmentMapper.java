package com.novelis.novy.dto.mappers;
import com.novelis.novy.dto.dtoRequest.DepartmentRequestDTO;
import com.novelis.novy.dto.dtoRequest.JobRequestDTO;
import com.novelis.novy.dto.dtoResponse.DepartmentResponseDTO;
import com.novelis.novy.dto.dtoResponse.JobResponseDTO;
import com.novelis.novy.model.Department;
import com.novelis.novy.model.Job;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DepartmentMapper extends ApplicationMapper<DepartmentRequestDTO, DepartmentResponseDTO, Department>{
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
}
