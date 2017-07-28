package com.ekstraklasa.football.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FlatDetail implements Serializable {

    private Long idflatdetail;
    private String description;
    private String type_of_building;
    private String floor;
    private String num_floors;
    private String building_material;
    private String windows;
    private String warming;
    private String year_construction;
    private String trim_level;
    private String available_from;

    //1-yes
    private Integer for_students;

    public FlatDetail(){}

    public FlatDetail(String description, String type_of_building, String floor,String num_floors, String building_material, String windows, String warming, String year_construction, String trim_level, String available_from, Integer for_students){
        this.idflatdetail=idflatdetail;
        this.description=description;
        this.type_of_building=type_of_building;
        this.floor=floor;
        this.num_floors=num_floors;
        this.building_material=building_material;
        this.windows=windows;
        this.warming=warming;
        this.year_construction=year_construction;
        this.trim_level=trim_level;
        this.available_from=available_from;
        this.for_students=for_students;
    }

    public String getAvailable_from() {
        return available_from;
    }

    public void setAvailable_from(String available_from) {
        this.available_from = available_from;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_of_building() {
        return type_of_building;
    }

    public void setType_of_building(String type_of_building) {
        this.type_of_building = type_of_building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNum_floors() {
        return num_floors;
    }

    public void setNum_floors(String num_floors) {
        this.num_floors = num_floors;
    }

    public String getBuilding_material() {
        return building_material;
    }

    public void setBuilding_material(String building_material) {
        this.building_material = building_material;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getWarming() {
        return warming;
    }

    public void setWarming(String warming) {
        this.warming = warming;
    }

    public String getYear_construction() {
        return year_construction;
    }

    public void setYear_construction(String year_construction) {
        this.year_construction = year_construction;
    }

    public String getTrim_level() {
        return trim_level;
    }

    public void setTrim_level(String trim_level) {
        this.trim_level = trim_level;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdflatdetail() {
        return idflatdetail;
    }

    public void setIdflatdetail(Long idflatdetail) {
        this.idflatdetail = idflatdetail;
    }

    public Integer getFor_students() {
        return for_students;
    }

    public void setFor_students(Integer for_students) {
        this.for_students = for_students;
    }
}
