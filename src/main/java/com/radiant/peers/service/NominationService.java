package com.radiant.peers.service;

import com.radiant.peers.service.dto.NominationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Nomination.
 */
public interface NominationService {

    /**
     * Save a nomination.
     *
     * @param nominationDTO the entity to save
     * @return the persisted entity
     */
    NominationDTO save(NominationDTO nominationDTO);

    /**
     *  Get all the nominations.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<NominationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" nomination.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    NominationDTO findOne(Long id);

    /**
     *  Delete the "id" nomination.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
