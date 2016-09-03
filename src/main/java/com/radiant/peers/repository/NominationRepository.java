package com.radiant.peers.repository;

import com.radiant.peers.domain.Nomination;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Nomination entity.
 */
@SuppressWarnings("unused")
public interface NominationRepository extends JpaRepository<Nomination,Long> {

}
