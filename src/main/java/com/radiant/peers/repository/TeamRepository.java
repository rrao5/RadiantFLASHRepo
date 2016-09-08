package com.radiant.peers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radiant.peers.domain.Team;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
