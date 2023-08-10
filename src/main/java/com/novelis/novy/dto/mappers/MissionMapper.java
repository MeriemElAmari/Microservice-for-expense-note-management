package com.novelis.novy.dto.mappers;


import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.dto.dtoResponse.MissionResponseDTO;
import com.novelis.novy.dto.dtoRequest.MissionRequestDTO;
import com.novelis.novy.model.Collaborator;
import com.novelis.novy.model.Mission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    MissionResponseDTO missionEntitytoMissionResponseDTO(Mission mission);

    Mission missionRequestToMissionEntity(MissionRequestDTO missionRequestDTO);
    List<MissionResponseDTO> missionListToMissionResponseList(List<Mission> missions);

}
