package com.radiant.peers.service;

import java.util.List;

import com.radiant.peers.domain.Team;

public interface TeamService {
	
	public List<Team> getTeamNames();
	
	public Team getTeamById(long id);

}
