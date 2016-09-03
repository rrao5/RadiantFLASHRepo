package com.radiant.peers.service.mapper;

import com.radiant.peers.domain.*;
import com.radiant.peers.service.dto.NominationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Nomination and its DTO NominationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapper.class, })
public interface NominationMapper {

    @Mapping(source = "nominee.id", target = "nomineeId")
    @Mapping(source = "nominatedBy.id", target = "nominatedById")
    NominationDTO nominationToNominationDTO(Nomination nomination);

    List<NominationDTO> nominationsToNominationDTOs(List<Nomination> nominations);

    @Mapping(source = "nomineeId", target = "nominee")
    @Mapping(source = "nominatedById", target = "nominatedBy")
    Nomination nominationDTOToNomination(NominationDTO nominationDTO);

    List<Nomination> nominationDTOsToNominations(List<NominationDTO> nominationDTOs);
}
