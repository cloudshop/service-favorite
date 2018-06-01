package com.eyun.favorite.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MercuryStatusHistory.
 */
@Entity
@Table(name = "mercury_status_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MercuryStatusHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @Column(name = "with_status")
    private Integer withStatus;

    @Column(name = "to_status")
    private Integer toStatus;

    @ManyToOne
    private Mercury mercury;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public MercuryStatusHistory modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public MercuryStatusHistory modifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getWithStatus() {
        return withStatus;
    }

    public MercuryStatusHistory withStatus(Integer withStatus) {
        this.withStatus = withStatus;
        return this;
    }

    public void setWithStatus(Integer withStatus) {
        this.withStatus = withStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public MercuryStatusHistory toStatus(Integer toStatus) {
        this.toStatus = toStatus;
        return this;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public Mercury getMercury() {
        return mercury;
    }

    public MercuryStatusHistory mercury(Mercury mercury) {
        this.mercury = mercury;
        return this;
    }

    public void setMercury(Mercury mercury) {
        this.mercury = mercury;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MercuryStatusHistory mercuryStatusHistory = (MercuryStatusHistory) o;
        if (mercuryStatusHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mercuryStatusHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MercuryStatusHistory{" +
            "id=" + getId() +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedTime='" + getModifiedTime() + "'" +
            ", withStatus=" + getWithStatus() +
            ", toStatus=" + getToStatus() +
            "}";
    }
}
