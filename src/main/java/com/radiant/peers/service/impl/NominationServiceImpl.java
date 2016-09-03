package com.radiant.peers.service.impl;

import com.radiant.peers.service.NominationService;
import com.radiant.peers.domain.Nomination;
import com.radiant.peers.repository.NominationRepository;
import com.radiant.peers.service.dto.NominationDTO;
import com.radiant.peers.service.mapper.NominationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Nomination.
 */
@Service
@Transactional
public class NominationServiceImpl implements NominationService{

    private final Logger log = LoggerFactory.getLogger(NominationServiceImpl.class);
    
    @Inject
    private NominationRepository nominationRepository;

    @Inject
    private NominationMapper nominationMapper;

    /**
     * Save a nomination.
     *
     * @param nominationDTO the entity to save
     * @return the persisted entity
     */
    public NominationDTO save(NominationDTO nominationDTO) {
        log.debug("Request to save Nomination : {}", nominationDTO);
        Nomination nomination = nominationMapper.nominationDTOToNomination(nominationDTO);
        nomination = nominationRepository.save(nomination);
        NominationDTO result = nominationMapper.nominationToNominationDTO(nomination);
        return result;
    }

    /**
     *  Get all the nominations.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<NominationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nominations");
        Page<Nomination> result = nominationRepository.findAll(pageable);
        return result.map(nomination -> nominationMapper.nominationToNominationDTO(nomination));
    }

    /**
     *  Get one nomination by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public NominationDTO findOne(Long id) {
        log.debug("Request to get Nomination : {}", id);
        Nomination nomination = nominationRepository.findOne(id);
        NominationDTO nominationDTO = nominationMapper.nominationToNominationDTO(nomination);
        return nominationDTO;
    }

    /**
     *  Delete the  nomination by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Nomination : {}", id);
        nominationRepository.delete(id);
    }
}
