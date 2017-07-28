package com.ekstraklasa.football.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcars")
    private long idcars;


    @Column(name = "offer_from")
    private String offer_from;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "model_version")
    private String model_version;
    @Column(name = "year_production")
    private int year_production;
    @Column(name = "course")
    private int course;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "power")
    private int power;
    @Column(name = "type_c")
    private String type_c;

    @Column(name = "link")
    private String link;
    @Column(name = "price")
    private int price;
    @Column(name = "engine_cap")
    private int engine_cap;
    @Column(name = "vin")
    private String vin;
    @Column(name = "transmission")
    private String transmission;
    @Column(name = "num_seats")
    private int num_seats;
    @Column(name = "num_doors")
    private int num_doors;
    @Column(name = "color")
    private String color;
    @Column(name = "metallic")
    private String metallic;
    @Column(name = "country_of_origin")
    private String country_of_origin;
    @Column(name = "first_registration")
    private String first_registration;
    @Column(name = "registr_in_pl")
    private String registr_in_pl;
    @Column(name = "first_owner")
    private String first_owner;
    @Column(name = "no_accidents")
    private String no_accidents;
    @Column(name = "aso")
    private String aso;
    @Column(name = "condition_c")
    private String condition_c;
    @Column(name = "date_create")
    private String date_create;
    @Column(name = "idom")
    private String idom;

    public String getOffer_from() {
        return offer_from;
    }

    public void setOffer_from(String offer_from) {
        this.offer_from = offer_from;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel_version() {
        return model_version;
    }

    public void setModel_version(String model_version) {
        this.model_version = model_version;
    }

    public int getYear_production() {
        return year_production;
    }

    public void setYear_production(int year_production) {
        this.year_production = year_production;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type_c;
    }

    public void setType(String type) {
        this.type_c = type;
    }

    public void toStringf()
    {
        System.out.println("Samochód: "+getOffer_from()+" "+getBrand()+" "+getCategory()+" "+getFuel()+" "+getModel()+" "+getModel_version());
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEngine_cap() {
        return engine_cap;
    }

    public void setEngine_cap(int engine_cap) {
        this.engine_cap = engine_cap;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getNum_seats() {
        return num_seats;
    }

    public void setNum_seats(int num_seats) {
        this.num_seats = num_seats;
    }

    public int getNum_doors() {
        return num_doors;
    }

    public void setNum_doors(int num_doors) {
        this.num_doors = num_doors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMetallic() {
        return metallic;
    }

    public void setMetallic(String metallic) {
        this.metallic = metallic;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public String getFirst_registration() {
        return first_registration;
    }

    public void setFirst_registration(String first_registration) {
        this.first_registration = first_registration;
    }

    public String getRegistr_in_pl() {
        return registr_in_pl;
    }

    public void setRegistr_in_pl(String registr_in_pl) {
        this.registr_in_pl = registr_in_pl;
    }

    public String getFirst_owner() {
        return first_owner;
    }

    public void setFirst_owner(String first_owner) {
        this.first_owner = first_owner;
    }

    public String getNo_accidents() {
        return no_accidents;
    }

    public void setNo_accidents(String no_accidents) {
        this.no_accidents = no_accidents;
    }

    public String getAso() {
        return aso;
    }

    public void setAso(String aso) {
        this.aso = aso;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getIdom() {
        return idom;
    }

    public void setIdom(String idom) {
        this.idom = idom;
    }


    public long getIdcars() {
        return idcars;
    }

    public void setIdcars(int idcars) {
        this.idcars = idcars;
    }

    public String getCondition_c() {
        return condition_c;
    }

    public void setCondition_c(String condition_c) {
        this.condition_c = condition_c;
    }
}
