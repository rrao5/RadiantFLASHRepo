package com.radiant.peers.web.rest;


import com.radiant.peers.domain.Team;
import com.radiant.peers.service.TeamService;
import com.radiant.peers.service.dto.SaleDTO;
import com.radiant.peers.service.dto.TeamDTO;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.*;


@RestController
@RequestMapping("/teamRest")
public class TeamRestController {
 
    @Inject
    private TeamService teamService;

    @RequestMapping(value = "/teamNames",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<TeamDTO>> getTeamNames() {
    	
    	List<Team> teamNames = teamService.getTeamNames();
    	
    	HttpHeaders textPlainHeaders = new HttpHeaders();
            return new ResponseEntity<>(getTestData(), textPlainHeaders, HttpStatus.OK); 
        }
    
    private List<TeamDTO> getTestData() {
    	List<TeamDTO> list = new ArrayList<TeamDTO>();
    	//TeamDTO team1 = new TeamDTO();
    	//list.add(team1);
    	
    	list.add(getTeamDTO());
    	return list;
    }
    
    @RequestMapping(value = "/teamById{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
 
        public ResponseEntity<TeamDTO> getTeamById(@PathVariable int id) {
    	
    		Team team = teamService.getTeamById(id);	
    		//TeamDTO teamDTO = new TeamDTO (team);
    		
    		TeamDTO teamDTO = getTeamDTO();
    		
    		return Optional.ofNullable(teamDTO).map(result -> new ResponseEntity<>(result,HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    		
    	 }
    
    
    private TeamDTO getTeamDTO() {;
    	TeamDTO teamDTO = new TeamDTO();
    	teamDTO.setId(1);
    	teamDTO.setName("SREEDHAR CHANAMOLU");
    	return teamDTO;
    }
    
    
    @RequestMapping(value = "/grandtotals",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
 
        public ResponseEntity<SaleDTO> grandtotals() {
    	
    		
    		SaleDTO saleDTO = getSaleDTO();
    		
    		return Optional.ofNullable(saleDTO).map(result -> new ResponseEntity<>(result,HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    		
    	 }
    
    
    private SaleDTO getSaleDTO() {;
    SaleDTO saleDTO = new SaleDTO();
	saleDTO.setNoCalls(25);
	saleDTO.setSaleAmount(123);
	saleDTO.setUpsalecalls(30);
    
	return saleDTO;
}
    
    
}
