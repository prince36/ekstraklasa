package com.ekstraklasa.football.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Flat implements Serializable{

    private Long idflat;
    private String title;
    private String url;
    private Integer price;
    private Integer extra_rent;
    private Integer bail;
    private Integer area;
    private Integer num_rooms;

    //0-właściciel //1-pośrednik
    private Integer type_advertiser;

    private String place;//
    private String district;
    private String street;

    private Date datecreate;

    private FlatDetail flatDetail;

    public Flat(){}

    public Flat(String title){
        this.title = title;
    }

    public Flat(Long idflat, String title, String url, Integer price, Integer extra_rent, Integer bail, Integer area, Integer num_rooms, Integer type_advertiser, String place, String district, String street, Date datecreate, FlatDetail flatDetail){

        this.idflat=idflat;
        this.title=title;
        this.url=url;
        this.price=price;
        this.extra_rent=extra_rent;
        this.bail=bail;
        this.area=area;
        this.num_rooms=num_rooms;
        this.type_advertiser=type_advertiser;
        this.place=place;
        this.district=district;
        this.street=street;
        this.datecreate=datecreate;

        this.flatDetail=flatDetail;
    }

    public Flat(String title, FlatDetail flatDetail){
        this.title=title;

        this.flatDetail=flatDetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getExtra_rent() {
        return extra_rent;
    }

    public void setExtra_rent(Integer extra_rent) {
        this.extra_rent = extra_rent;
    }

    public Integer getBail() {
        return bail;
    }

    public void setBail(Integer bail) {
        this.bail = bail;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getNum_rooms() {
        return num_rooms;
    }

    public void setNum_rooms(Integer num_rooms) {
        this.num_rooms = num_rooms;
    }

    public Integer getType_advertiser() {
        return type_advertiser;
    }

    public void setType_advertiser(Integer type_advertiser) {
        this.type_advertiser = type_advertiser;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idflatdetail")
    public FlatDetail getFlatDetail() {
        return flatDetail;
    }

    public void setFlatDetail(FlatDetail flatDetail) {
        this.flatDetail = flatDetail;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdflat() {
        return idflat;
    }

    public void setIdflat(Long idflat) {
        this.idflat = idflat;
    }

    public void flatToString(){
        System.out.println(
                "Flat:[ tytuł: "+
                getTitle()+", cena wynajmu: "+
                        getPrice()+", czynsz dodatkowo: : "+
                        getExtra_rent()+", url: "+
                        getUrl()+", kaucja: "+
                        getBail()+", powierzchnia: "+
                        getArea()+", liczba pokoi: "+
                        getNum_rooms()+", od kogo: "+
                        getType_advertiser()+", miasto: "+
                        getPlace()+", dzielnica: "+
                        getDistrict()+", data utworzenia: "+
                        getDatecreate()+", ulica: "+
                        getStreet()+" Materiał: "+
                        flatDetail.getBuilding_material()+"]"
        );
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public String getAllUrlsInOneString(List<Flat> list){
        StringBuilder sb = new StringBuilder();
        for (Flat flat: list) {
            sb.append(flat.getUrl());
            sb.append(" ");
        }
        return String.valueOf(sb);
    }
}
