package com.ekstraklasa.football.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idorder")
    private Long idorder;

    private Integer price_from;
    private Integer price_to;
    private String location;
    private String district;
    private String email;
    private Integer numrooms;
    private Date lastPush;

    public Order(){}

    public Order(Long idorder, Integer price_from, Integer price_to, String location, String district, String email, Integer numrooms){
        this.idorder=idorder;
        this.price_from=price_from;
        this.price_to=price_to;
        this.location=location;
        this.district=district;
        this.email=email;
        this.numrooms=numrooms;
    }

    public Long getIdorder() {
        return idorder;
    }

    public void setIdorder(Long idorder) {
        this.idorder = idorder;
    }

    public Integer getPrice_from() {
        return price_from;
    }

    public void setPrice_from(Integer price_from) {
        this.price_from = price_from;
    }

    public Integer getPrice_to() {
        return price_to;
    }

    public void setPrice_to(Integer price_to) {
        this.price_to = price_to;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumrooms() {
        return numrooms;
    }

    public void setNumrooms(Integer numrooms) {
        this.numrooms = numrooms;
    }

    public Date getLastPush() {
        return lastPush;
    }

    public void setLastPush(Date lastPush) {
        this.lastPush = lastPush;
    }
}
