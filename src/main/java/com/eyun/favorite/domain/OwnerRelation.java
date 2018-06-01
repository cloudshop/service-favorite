package com.eyun.favorite.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OwnerRelation.
 */
@Entity
@Table(name = "owner_relation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OwnerRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @OneToOne
    @JoinColumn(unique = true)
    private UserAnnex userAnnex;

    @OneToOne
    @JoinColumn(unique = true)
    private Mercury mercury;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public OwnerRelation roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public OwnerRelation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public OwnerRelation createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public OwnerRelation updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public UserAnnex getUserAnnex() {
        return userAnnex;
    }

    public OwnerRelation userAnnex(UserAnnex userAnnex) {
        this.userAnnex = userAnnex;
        return this;
    }

    public void setUserAnnex(UserAnnex userAnnex) {
        this.userAnnex = userAnnex;
    }

    public Mercury getMercury() {
        return mercury;
    }

    public OwnerRelation mercury(Mercury mercury) {
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
        OwnerRelation ownerRelation = (OwnerRelation) o;
        if (ownerRelation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ownerRelation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OwnerRelation{" +
            "id=" + getId() +
            ", roleName='" + getRoleName() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            "}";
    }
}
