package com.radiant.peers.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radiant.peers.domain.Team;
import com.radiant.peers.repository.TeamRepository;
import com.radiant.peers.service.TeamService;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    TeamRepository teamRepo;
    
	@Override
	public List<Team> getTeamNames() {
		//log.debug(msg);
		return teamRepo.findAll();
		
	}

	@Override
	public Team getTeamById(long id) {
		return teamRepo.findOne(id);
	}

}
