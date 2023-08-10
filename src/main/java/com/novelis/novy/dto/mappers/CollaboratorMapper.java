package com.novelis.novy.dto.mappers;

import com.novelis.novy.dto.dtoRequest.CollaboratorRequestDTO;
import com.novelis.novy.dto.dtoResponse.CollaboratorResponseDTO;
import com.novelis.novy.model.Collaborator;
import com.novelis.novy.model.Mission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CollaboratorMapper {

    @Named("mapMissionIdsToMissions")
    default List<Mission> mapMissionIdsToMissions(List<Long> missionIds) {
        if (missionIds == null) {
            return null;
        }
        return missionIds.stream()
                .map(missionId -> {
                    Mission mission = new Mission();
                    mission.setId(missionId);
                    return mission;
                })
                .collect(Collectors.toList());
    }
    CollaboratorMapper INSTANCE = Mappers.getMapper(CollaboratorMapper.class);
    @Mapping(source = "code", target = "code")

    CollaboratorResponseDTO collaboratorEntityToCollaboratorResponseDTO(Collaborator collaborator);
    @Mapping(target = "missions", source = "missions", qualifiedByName = "mapMissionIdsToMissions")
    Collaborator collaboratorRequestToCollaboratorEntity(CollaboratorRequestDTO collaboratorRequestDTO);
    List<CollaboratorResponseDTO> collaboratorListToCollaboratorResponseList(List<Collaborator> collaborators);


}
