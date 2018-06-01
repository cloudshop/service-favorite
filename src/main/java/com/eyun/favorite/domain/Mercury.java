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
 * A Mercury.
 */
@Entity
@Table(name = "mercury")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mercury implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_license")
    private String imgLicense;

    @Column(name = "img_idcard_front")
    private String imgIdcardFront;

    @Column(name = "img_idcard_back")
    private String imgIdcardBack;

    @Column(name = "img_idcard_hold")
    private String imgIdcardHold;

    @Column(name = "langitude")
    private Double langitude;

    @Column(name = "lantitude")
    private Double lantitude;

    @Column(name = "provice")
    private String provice;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "img_facade")
    private String imgFacade;

    @Column(name = "img_introduces")
    private String imgIntroduces;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @OneToMany(mappedBy = "mercury")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MercuryStatusHistory> mercuryStatusHistories = new HashSet<>();

    @OneToOne(mappedBy = "mercury")
    @JsonIgnore
    private OwnerRelation ownerRelation;

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

    public Mercury name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgLicense() {
        return imgLicense;
    }

    public Mercury imgLicense(String imgLicense) {
        this.imgLicense = imgLicense;
        return this;
    }

    public void setImgLicense(String imgLicense) {
        this.imgLicense = imgLicense;
    }

    public String getImgIdcardFront() {
        return imgIdcardFront;
    }

    public Mercury imgIdcardFront(String imgIdcardFront) {
        this.imgIdcardFront = imgIdcardFront;
        return this;
    }

    public void setImgIdcardFront(String imgIdcardFront) {
        this.imgIdcardFront = imgIdcardFront;
    }

    public String getImgIdcardBack() {
        return imgIdcardBack;
    }

    public Mercury imgIdcardBack(String imgIdcardBack) {
        this.imgIdcardBack = imgIdcardBack;
        return this;
    }

    public void setImgIdcardBack(String imgIdcardBack) {
        this.imgIdcardBack = imgIdcardBack;
    }

    public String getImgIdcardHold() {
        return imgIdcardHold;
    }

    public Mercury imgIdcardHold(String imgIdcardHold) {
        this.imgIdcardHold = imgIdcardHold;
        return this;
    }

    public void setImgIdcardHold(String imgIdcardHold) {
        this.imgIdcardHold = imgIdcardHold;
    }

    public Double getLangitude() {
        return langitude;
    }

    public Mercury langitude(Double langitude) {
        this.langitude = langitude;
        return this;
    }

    public void setLangitude(Double langitude) {
        this.langitude = langitude;
    }

    public Double getLantitude() {
        return lantitude;
    }

    public Mercury lantitude(Double lantitude) {
        this.lantitude = lantitude;
        return this;
    }

    public void setLantitude(Double lantitude) {
        this.lantitude = lantitude;
    }

    public String getProvice() {
        return provice;
    }

    public Mercury provice(String provice) {
        this.provice = provice;
        return this;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public Mercury city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public Mercury street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImgFacade() {
        return imgFacade;
    }

    public Mercury imgFacade(String imgFacade) {
        this.imgFacade = imgFacade;
        return this;
    }

    public void setImgFacade(String imgFacade) {
        this.imgFacade = imgFacade;
    }

    public String getImgIntroduces() {
        return imgIntroduces;
    }

    public Mercury imgIntroduces(String imgIntroduces) {
        this.imgIntroduces = imgIntroduces;
        return this;
    }

    public void setImgIntroduces(String imgIntroduces) {
        this.imgIntroduces = imgIntroduces;
    }

    public Integer getStatus() {
        return status;
    }

    public Mercury status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Mercury createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public Mercury updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Set<MercuryStatusHistory> getMercuryStatusHistories() {
        return mercuryStatusHistories;
    }

    public Mercury mercuryStatusHistories(Set<MercuryStatusHistory> mercuryStatusHistories) {
        this.mercuryStatusHistories = mercuryStatusHistories;
        return this;
    }

    public Mercury addMercuryStatusHistory(MercuryStatusHistory mercuryStatusHistory) {
        this.mercuryStatusHistories.add(mercuryStatusHistory);
        mercuryStatusHistory.setMercury(this);
        return this;
    }

    public Mercury removeMercuryStatusHistory(MercuryStatusHistory mercuryStatusHistory) {
        this.mercuryStatusHistories.remove(mercuryStatusHistory);
        mercuryStatusHistory.setMercury(null);
        return this;
    }

    public void setMercuryStatusHistories(Set<MercuryStatusHistory> mercuryStatusHistories) {
        this.mercuryStatusHistories = mercuryStatusHistories;
    }

    public OwnerRelation getOwnerRelation() {
        return ownerRelation;
    }

    public Mercury ownerRelation(OwnerRelation ownerRelation) {
        this.ownerRelation = ownerRelation;
        return this;
    }

    public void setOwnerRelation(OwnerRelation ownerRelation) {
        this.ownerRelation = ownerRelation;
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
        Mercury mercury = (Mercury) o;
        if (mercury.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mercury.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mercury{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imgLicense='" + getImgLicense() + "'" +
            ", imgIdcardFront='" + getImgIdcardFront() + "'" +
            ", imgIdcardBack='" + getImgIdcardBack() + "'" +
            ", imgIdcardHold='" + getImgIdcardHold() + "'" +
            ", langitude=" + getLangitude() +
            ", lantitude=" + getLantitude() +
            ", provice='" + getProvice() + "'" +
            ", city='" + getCity() + "'" +
            ", street='" + getStreet() + "'" +
            ", imgFacade='" + getImgFacade() + "'" +
            ", imgIntroduces='" + getImgIntroduces() + "'" +
            ", status=" + getStatus() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            "}";
    }
}
