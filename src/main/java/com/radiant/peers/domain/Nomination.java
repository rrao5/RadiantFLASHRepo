package com.radiant.peers.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Nomination.
 */
@Entity
@Table(name = "nomination")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Nomination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nomination_dt")
    private ZonedDateTime nominationDt;

    @NotNull
    @Column(name = "nomination_text", nullable = false)
    private String nominationText;

    @OneToOne
    @JoinColumn(unique = true)
    private User nominee;

    @OneToOne
    @JoinColumn(unique = true)
    private User nominatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getNominationDt() {
        return nominationDt;
    }

    public Nomination nominationDt(ZonedDateTime nominationDt) {
        this.nominationDt = nominationDt;
        return this;
    }

    public void setNominationDt(ZonedDateTime nominationDt) {
        this.nominationDt = nominationDt;
    }

    public String getNominationText() {
        return nominationText;
    }

    public Nomination nominationText(String nominationText) {
        this.nominationText = nominationText;
        return this;
    }

    public void setNominationText(String nominationText) {
        this.nominationText = nominationText;
    }

    public User getNominee() {
        return nominee;
    }

    public Nomination nominee(User user) {
        this.nominee = user;
        return this;
    }

    public void setNominee(User user) {
        this.nominee = user;
    }

    public User getNominatedBy() {
        return nominatedBy;
    }

    public Nomination nominatedBy(User user) {
        this.nominatedBy = user;
        return this;
    }

    public void setNominatedBy(User user) {
        this.nominatedBy = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nomination nomination = (Nomination) o;
        if(nomination.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, nomination.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Nomination{" +
            "id=" + id +
            ", nominationDt='" + nominationDt + "'" +
            ", nominationText='" + nominationText + "'" +
            '}';
    }
}
