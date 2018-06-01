package com.eyun.favorite.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A UserStatusHistory.
 */
@Entity
@Table(name = "user_status_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserStatusHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "with_status")
    private Integer withStatus;

    @Column(name = "to_status")
    private Integer toStatus;

    @ManyToOne
    private UserAnnex userAnnex;

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

    public UserStatusHistory modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public UserStatusHistory modifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getUserid() {
        return userid;
    }

    public UserStatusHistory userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getWithStatus() {
        return withStatus;
    }

    public UserStatusHistory withStatus(Integer withStatus) {
        this.withStatus = withStatus;
        return this;
    }

    public void setWithStatus(Integer withStatus) {
        this.withStatus = withStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public UserStatusHistory toStatus(Integer toStatus) {
        this.toStatus = toStatus;
        return this;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public UserAnnex getUserAnnex() {
        return userAnnex;
    }

    public UserStatusHistory userAnnex(UserAnnex userAnnex) {
        this.userAnnex = userAnnex;
        return this;
    }

    public void setUserAnnex(UserAnnex userAnnex) {
        this.userAnnex = userAnnex;
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
        UserStatusHistory userStatusHistory = (UserStatusHistory) o;
        if (userStatusHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userStatusHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserStatusHistory{" +
            "id=" + getId() +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedTime='" + getModifiedTime() + "'" +
            ", userid=" + getUserid() +
            ", withStatus=" + getWithStatus() +
            ", toStatus=" + getToStatus() +
            "}";
    }
}
