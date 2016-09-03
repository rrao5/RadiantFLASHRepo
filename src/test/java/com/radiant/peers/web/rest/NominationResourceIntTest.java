package com.radiant.peers.web.rest;

import com.radiant.peers.PeersApp;
import com.radiant.peers.domain.Nomination;
import com.radiant.peers.repository.NominationRepository;
import com.radiant.peers.service.NominationService;
import com.radiant.peers.service.dto.NominationDTO;
import com.radiant.peers.service.mapper.NominationMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NominationResource REST controller.
 *
 * @see NominationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeersApp.class)
public class NominationResourceIntTest {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final ZonedDateTime DEFAULT_NOMINATION_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_NOMINATION_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_NOMINATION_DT_STR = dateTimeFormatter.format(DEFAULT_NOMINATION_DT);
    private static final String DEFAULT_NOMINATION_TEXT = "AAAAA";
    private static final String UPDATED_NOMINATION_TEXT = "BBBBB";

    @Inject
    private NominationRepository nominationRepository;

    @Inject
    private NominationMapper nominationMapper;

    @Inject
    private NominationService nominationService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restNominationMockMvc;

    private Nomination nomination;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NominationResource nominationResource = new NominationResource();
        ReflectionTestUtils.setField(nominationResource, "nominationService", nominationService);
        this.restNominationMockMvc = MockMvcBuilders.standaloneSetup(nominationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nomination createEntity(EntityManager em) {
        Nomination nomination = new Nomination();
        nomination = new Nomination()
                .nominationDt(DEFAULT_NOMINATION_DT)
                .nominationText(DEFAULT_NOMINATION_TEXT);
        return nomination;
    }

    @Before
    public void initTest() {
        nomination = createEntity(em);
    }

    @Test
    @Transactional
    public void createNomination() throws Exception {
        int databaseSizeBeforeCreate = nominationRepository.findAll().size();

        // Create the Nomination
        NominationDTO nominationDTO = nominationMapper.nominationToNominationDTO(nomination);

        restNominationMockMvc.perform(post("/api/nominations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
                .andExpect(status().isCreated());

        // Validate the Nomination in the database
        List<Nomination> nominations = nominationRepository.findAll();
        assertThat(nominations).hasSize(databaseSizeBeforeCreate + 1);
        Nomination testNomination = nominations.get(nominations.size() - 1);
        assertThat(testNomination.getNominationDt()).isEqualTo(DEFAULT_NOMINATION_DT);
        assertThat(testNomination.getNominationText()).isEqualTo(DEFAULT_NOMINATION_TEXT);
    }

    @Test
    @Transactional
    public void checkNominationTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = nominationRepository.findAll().size();
        // set the field null
        nomination.setNominationText(null);

        // Create the Nomination, which fails.
        NominationDTO nominationDTO = nominationMapper.nominationToNominationDTO(nomination);

        restNominationMockMvc.perform(post("/api/nominations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
                .andExpect(status().isBadRequest());

        List<Nomination> nominations = nominationRepository.findAll();
        assertThat(nominations).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNominations() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        // Get all the nominations
        restNominationMockMvc.perform(get("/api/nominations?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(nomination.getId().intValue())))
                .andExpect(jsonPath("$.[*].nominationDt").value(hasItem(DEFAULT_NOMINATION_DT_STR)))
                .andExpect(jsonPath("$.[*].nominationText").value(hasItem(DEFAULT_NOMINATION_TEXT.toString())));
    }

    @Test
    @Transactional
    public void getNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        // Get the nomination
        restNominationMockMvc.perform(get("/api/nominations/{id}", nomination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nomination.getId().intValue()))
            .andExpect(jsonPath("$.nominationDt").value(DEFAULT_NOMINATION_DT_STR))
            .andExpect(jsonPath("$.nominationText").value(DEFAULT_NOMINATION_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNomination() throws Exception {
        // Get the nomination
        restNominationMockMvc.perform(get("/api/nominations/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);
        int databaseSizeBeforeUpdate = nominationRepository.findAll().size();

        // Update the nomination
        Nomination updatedNomination = nominationRepository.findOne(nomination.getId());
        updatedNomination
                .nominationDt(UPDATED_NOMINATION_DT)
                .nominationText(UPDATED_NOMINATION_TEXT);
        NominationDTO nominationDTO = nominationMapper.nominationToNominationDTO(updatedNomination);

        restNominationMockMvc.perform(put("/api/nominations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
                .andExpect(status().isOk());

        // Validate the Nomination in the database
        List<Nomination> nominations = nominationRepository.findAll();
        assertThat(nominations).hasSize(databaseSizeBeforeUpdate);
        Nomination testNomination = nominations.get(nominations.size() - 1);
        assertThat(testNomination.getNominationDt()).isEqualTo(UPDATED_NOMINATION_DT);
        assertThat(testNomination.getNominationText()).isEqualTo(UPDATED_NOMINATION_TEXT);
    }

    @Test
    @Transactional
    public void deleteNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);
        int databaseSizeBeforeDelete = nominationRepository.findAll().size();

        // Get the nomination
        restNominationMockMvc.perform(delete("/api/nominations/{id}", nomination.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Nomination> nominations = nominationRepository.findAll();
        assertThat(nominations).hasSize(databaseSizeBeforeDelete - 1);
    }
}
