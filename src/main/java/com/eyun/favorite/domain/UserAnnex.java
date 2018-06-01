package com.eyun.favorite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserAnnex.
 */
@Entity
@Table(name = "user_annex")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAnnex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "type_string")
    private String typeString;

    @Column(name = "inviter_id")
    private Long inviterId;

    @OneToOne(mappedBy = "userAnnex")
    @JsonIgnore
    private OwnerRelation ownerRelation;

    @OneToMany(mappedBy = "userAnnex")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserStatusHistory> userStatusHistories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserAnnex name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public UserAnnex email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public UserAnnex phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public UserAnnex nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserAnnex avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public UserAnnex status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public UserAnnex createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public UserAnnex updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getType() {
        return type;
    }

    public UserAnnex type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeString() {
        return typeString;
    }

    public UserAnnex typeString(String typeString) {
        this.typeString = typeString;
        return this;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public UserAnnex inviterId(Long inviterId) {
        this.inviterId = inviterId;
        return this;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public OwnerRelation getOwnerRelation() {
        return ownerRelation;
    }

    public UserAnnex ownerRelation(OwnerRelation ownerRelation) {
        this.ownerRelation = ownerRelation;
        return this;
    }

    public void setOwnerRelation(OwnerRelation ownerRelation) {
        this.ownerRelation = ownerRelation;
    }

    public Set<UserStatusHistory> getUserStatusHistories() {
        return userStatusHistories;
    }

    public UserAnnex userStatusHistories(Set<UserStatusHistory> userStatusHistories) {
        this.userStatusHistories = userStatusHistories;
        return this;
    }

    public UserAnnex addUserStatusHistory(UserStatusHistory userStatusHistory) {
        this.userStatusHistories.add(userStatusHistory);
        userStatusHistory.setUserAnnex(this);
        return this;
    }

    public UserAnnex removeUserStatusHistory(UserStatusHistory userStatusHistory) {
        this.userStatusHistories.remove(userStatusHistory);
        userStatusHistory.setUserAnnex(null);
        return this;
    }

    public void setUserStatusHistories(Set<UserStatusHistory> userStatusHistories) {
        this.userStatusHistories = userStatusHistories;
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
        UserAnnex userAnnex = (UserAnnex) o;
        if (userAnnex.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userAnnex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserAnnex{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", status=" + getStatus() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            ", type=" + getType() +
            ", typeString='" + getTypeString() + "'" +
            ", inviterId=" + getInviterId() +
            "}";
    }
}
