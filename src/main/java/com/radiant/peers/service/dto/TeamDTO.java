package com.radiant.peers.service.dto;

import java.util.stream.Collectors;

import com.radiant.peers.domain.Authority;
import com.radiant.peers.domain.Team;
import com.radiant.peers.domain.User;

/**
 * A DTO representing a user, with his authorities.
 */
public class TeamDTO {

	long id;
	String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public TeamDTO(Team team) {
    	this.id=team.getId();
    	this.name=team.getName();
    }

}
