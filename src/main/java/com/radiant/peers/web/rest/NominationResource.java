package com.radiant.peers.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.radiant.peers.service.NominationService;
import com.radiant.peers.web.rest.util.HeaderUtil;
import com.radiant.peers.web.rest.util.PaginationUtil;
import com.radiant.peers.service.dto.NominationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Nomination.
 */
@RestController
@RequestMapping("/api")
public class NominationResource {

    private final Logger log = LoggerFactory.getLogger(NominationResource.class);
        
    @Inject
    private NominationService nominationService;

    /**
     * POST  /nominations : Create a new nomination.
     *
     * @param nominationDTO the nominationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nominationDTO, or with status 400 (Bad Request) if the nomination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/nominations",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NominationDTO> createNomination(@Valid @RequestBody NominationDTO nominationDTO) throws URISyntaxException {
        log.debug("REST request to save Nomination : {}", nominationDTO);
        if (nominationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("nomination", "idexists", "A new nomination cannot already have an ID")).body(null);
        }
        NominationDTO result = nominationService.save(nominationDTO);
        return ResponseEntity.created(new URI("/api/nominations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("nomination", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nominations : Updates an existing nomination.
     *
     * @param nominationDTO the nominationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nominationDTO,
     * or with status 400 (Bad Request) if the nominationDTO is not valid,
     * or with status 500 (Internal Server Error) if the nominationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/nominations",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NominationDTO> updateNomination(@Valid @RequestBody NominationDTO nominationDTO) throws URISyntaxException {
        log.debug("REST request to update Nomination : {}", nominationDTO);
        if (nominationDTO.getId() == null) {
            return createNomination(nominationDTO);
        }
        NominationDTO result = nominationService.save(nominationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("nomination", nominationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nominations : get all the nominations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nominations in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/nominations",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<NominationDTO>> getAllNominations(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Nominations");
        Page<NominationDTO> page = nominationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nominations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nominations/:id : get the "id" nomination.
     *
     * @param id the id of the nominationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nominationDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/nominations/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NominationDTO> getNomination(@PathVariable Long id) {
        log.debug("REST request to get Nomination : {}", id);
        NominationDTO nominationDTO = nominationService.findOne(id);
        return Optional.ofNullable(nominationDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /nominations/:id : delete the "id" nomination.
     *
     * @param id the id of the nominationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/nominations/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteNomination(@PathVariable Long id) {
        log.debug("REST request to delete Nomination : {}", id);
        nominationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("nomination", id.toString())).build();
    }

}
