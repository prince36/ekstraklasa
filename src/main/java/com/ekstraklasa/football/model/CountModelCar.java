package com.ekstraklasa.football.model;

import javax.persistence.criteria.CriteriaBuilder;

public class CountModelCar {
    private Integer id;
    private String brand;
    private String model;
    private String modelversion;
    private Long countmodel;
    private Long countmodelversion;

    public CountModelCar(){};

    public CountModelCar(Integer id, String brand, String model, String modelversion, Long countmodel, Long countmodelversion){
        this.id=id;
        this.brand=brand;
        this.model = model;
        this.modelversion=modelversion;
        this.countmodel=countmodel;
        this.countmodelversion=countmodelversion;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getModelversion() {
        return modelversion;
    }

    public void setModelversion(String modelversion) {
        this.modelversion = modelversion;
    }

    public Long getCountmodel() {
        return countmodel;
    }

    public void setCountmodel(Long countmodel) {
        this.countmodel = countmodel;
    }

    public Long getCountmodelversion() {
        return countmodelversion;
    }

    public void setCountmodelversion(Long countmodelversion) {
        this.countmodelversion = countmodelversion;
    }
}
