package com.novelis.novy.dto.mappers;
import com.novelis.novy.dto.dtoRequest.AgencyRequestDTO;
import com.novelis.novy.dto.dtoRequest.JobRequestDTO;
import com.novelis.novy.dto.dtoResponse.AgencyResponseDTO;
import com.novelis.novy.dto.dtoResponse.JobResponseDTO;
import com.novelis.novy.model.Agency;
import com.novelis.novy.model.Job;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AgencyMapper extends ApplicationMapper<AgencyRequestDTO, AgencyResponseDTO, Agency>{
    AgencyMapper INSTANCE = Mappers.getMapper(AgencyMapper.class);
}