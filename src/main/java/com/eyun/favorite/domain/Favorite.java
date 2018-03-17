package com.eyun.favorite.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Favorite.
 */
@Entity
@Table(name = "favorite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "target_type")
    private String target_type;

    @Column(name = "target_id")
    private String target_id;

    @Column(name = "target_extend")
    private String target_extend;

    @Column(name = "create_time")
    private Instant create_time;

    @Column(name = "modify_time")
    private Instant modify_time;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "jhi_desc")
    private String desc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public Favorite userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTarget_type() {
        return target_type;
    }

    public Favorite target_type(String target_type) {
        this.target_type = target_type;
        return this;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getTarget_id() {
        return target_id;
    }

    public Favorite target_id(String target_id) {
        this.target_id = target_id;
        return this;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_extend() {
        return target_extend;
    }

    public Favorite target_extend(String target_extend) {
        this.target_extend = target_extend;
        return this;
    }

    public void setTarget_extend(String target_extend) {
        this.target_extend = target_extend;
    }

    public Instant getCreate_time() {
        return create_time;
    }

    public Favorite create_time(Instant create_time) {
        this.create_time = create_time;
        return this;
    }

    public void setCreate_time(Instant create_time) {
        this.create_time = create_time;
    }

    public Instant getModify_time() {
        return modify_time;
    }

    public Favorite modify_time(Instant modify_time) {
        this.modify_time = modify_time;
        return this;
    }

    public void setModify_time(Instant modify_time) {
        this.modify_time = modify_time;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Favorite deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDesc() {
        return desc;
    }

    public Favorite desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        Favorite favorite = (Favorite) o;
        if (favorite.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), favorite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Favorite{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", target_type='" + getTarget_type() + "'" +
            ", target_id='" + getTarget_id() + "'" +
            ", target_extend='" + getTarget_extend() + "'" +
            ", create_time='" + getCreate_time() + "'" +
            ", modify_time='" + getModify_time() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
