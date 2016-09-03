package com.radiant.peers.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Nomination entity.
 */
public class NominationDTO implements Serializable {

    private Long id;

    private ZonedDateTime nominationDt;

    @NotNull
    private String nominationText;


    private Long nomineeId;
    
    private Long nominatedById;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getNominationDt() {
        return nominationDt;
    }

    public void setNominationDt(ZonedDateTime nominationDt) {
        this.nominationDt = nominationDt;
    }
    public String getNominationText() {
        return nominationText;
    }

    public void setNominationText(String nominationText) {
        this.nominationText = nominationText;
    }

    public Long getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(Long userId) {
        this.nomineeId = userId;
    }

    public Long getNominatedById() {
        return nominatedById;
    }

    public void setNominatedById(Long userId) {
        this.nominatedById = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NominationDTO nominationDTO = (NominationDTO) o;

        if ( ! Objects.equals(id, nominationDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NominationDTO{" +
            "id=" + id +
            ", nominationDt='" + nominationDt + "'" +
            ", nominationText='" + nominationText + "'" +
            '}';
    }
}
