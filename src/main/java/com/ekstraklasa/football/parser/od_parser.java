package com.ekstraklasa.football.parser;


import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.FlatDetail;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.service.FlatService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class od_parser {

    @Autowired
    FlatService flatService;

    // otodom.pl
    public Flat Parser_od(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Flat flat = new Flat();
        FlatDetail flatd = new FlatDetail();

        try{
            System.out.println("Jestem na - "+url);



            //FlatDetail dlatdetail = new FlatDetail();
            Elements newsHeadlines;// = doc.select("#parameters > ul:nth-child(1)");

            flat.setUrl(url);

            //title;+
            newsHeadlines = doc.select("body > div.article-offer > section:nth-child(1) > div > div > header > h1");
            flat.setTitle(newsHeadlines.text());

            //description;+
            newsHeadlines = doc.select("body > div.article-offer > section:nth-child(6) > div > div > div > div > div > div:nth-child(2) > p");
            flatd.setDescription(newsHeadlines.text());

            //floor;+
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.main-list > li.param_floor_no > span > strong");
            flatd.setFloor(newsHeadlines.text());

            //num_floor;+
            String varparam1="okna:";
            String tmp1;
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.main-list > li.param_floor_no > span");
            tmp1 = newsHeadlines.text();
            if (newsHeadlines.text().length()==flatd.getFloor().length()){}
            else {
                flatd.setNum_floors(newsHeadlines.text().substring(flatd.getFloor().length()+4, tmp1.length()-1));
            }

            //price;+
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.main-list > li.param_price > span > strong");
            flat.setPrice(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));

            //area;+
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.main-list > li.param_m > span > strong");
            flat.setArea(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));

            //num_rooms;+
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.main-list > li:nth-child(3) > span > strong");
            flat.setNum_rooms(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));


            //0-właściciel //1-pośrednik
            //type_advertiser;
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-aside.container > aside > div.offer-aside-primary > div.offer-aside-group > div.box-contact-info.box-contact-info-private.text-center.hidden-print > h6");
            if(newsHeadlines.text().contains("prywatna")){flat.setType_advertiser(0);}
            else flat.setType_advertiser(1);


            //String place;+
            newsHeadlines = doc.select("body > div.article-offer > section:nth-child(1) > div > div > header > address > p.address-links > a:nth-child(2)");
            flat.setPlace(newsHeadlines.text());

            //String district;+
            newsHeadlines = doc.select("body > div.article-offer > section:nth-child(1) > div > div > header > address > p.address-links > a:nth-child(3)");
            flat.setDistrict(newsHeadlines.text());

            //String street;+
            newsHeadlines = doc.select("body > div.article-offer > section.section-offer-map.hidden-print > div > div > div > div > div.row > div:nth-child(2) > div.ad-map-location-holder > h4");
            flat.setStreet(newsHeadlines.text());

            //Data utworzenia
            flat.setDatecreate(Calendar.getInstance().getTime());

            //FlatDetail flatDetail;
            newsHeadlines = doc.select("body > div.article-offer > section:nth-child(1) > div > div > header > h1");
            flat.setTitle(newsHeadlines.text());

            //kaucja rodzaj zabudowy ... metoda if
            String label_param_floor_strong = "";
            String label_param_floor ="";
            String varparam;
            for (int i = 0; i < 11; i++) {
                label_param_floor_strong = "body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.sub-list > li:nth-child("+i+") > strong";
                label_param_floor ="body > div.article-offer > section.section-offer-params > div > div > div > ul > li:nth-child(1) > ul.sub-list > li:nth-child("+i+")";
                newsHeadlines = doc.select(label_param_floor_strong);

                if(newsHeadlines.text().contains("kaucja:")){
                    newsHeadlines = doc.select(label_param_floor);
                    flat.setBail(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));
                }
                varparam="materiał budynku:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setBuilding_material(newsHeadlines.text().substring(varparam.length()+1));
                }
                if(newsHeadlines.text().contains("czynsz - dodatkowo:")){
                    newsHeadlines = doc.select(label_param_floor);
                    flat.setExtra_rent(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));
                }
                varparam="rodzaj zabudowy:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setType_of_building(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="okna:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setWindows(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="piętro:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setNum_floors(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="liczba pięter:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setTrim_level(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="ogrzewanie:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setWarming(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="rok budowy:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setYear_construction(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="stan wykończenia:";// TODO: 27.07.2017
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    //flatd.set(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="dostępne od:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setAvailable_from(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="stan wykończenia:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setTrim_level(newsHeadlines.text().substring(varparam.length()+1));
                }
                varparam="wynajmę również studentom:";
                if(newsHeadlines.text().contains(varparam)){
                    newsHeadlines = doc.select(label_param_floor);
                    flatd.setFor_students(1);
                }
                else flatd.setFor_students(0);
            }
            flat.setFlatDetail(flatd);
            flat.flatToString();
        }
        catch (NumberFormatException a){
            System.out.println("NumberFormatException");
        }
        catch (Exception s) {
            System.out.println("bład");
        }


        //timeout
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flat;
    }
    public ArrayList<String> getUrls_od(Integer pageStart, Integer pageEnd) throws IOException {
        ArrayList<String> allUrls = new ArrayList<String>();

        for (int i = pageStart; i < pageEnd+1 ; i++) {
            Document doc = Jsoup.connect("https://www.otodom.pl/wynajem/mieszkanie/?page="+i).get();
            Elements links = doc.getElementsByClass("offer-item-header");
            for (Element link : links) {
                Elements cols = link.select("a[href]");
                for (Element col : cols) {
                    String item = col.getElementsByTag("a").attr("href");
                        if (!allUrls.contains(item.substring(0, item.indexOf('#')))) {
                            allUrls.add(item.substring(0, item.indexOf('#')));
                        }
                }
            }
        }
        return allUrls;
    }





    // olx.pl
    public ArrayList<String> getUrls_olx(Integer pageStart, Integer pageEnd) throws IOException {
        ArrayList<String> allUrls = new ArrayList<String>();

        for (int i = pageStart; i < pageEnd+1 ; i++) {
            Document doc = Jsoup.connect("https://www.olx.pl/nieruchomosci/mieszkania/wynajem/?page="+i).get();
            Elements links = doc.getElementsByClass("offer");
            for (Element link : links) {
                Elements cols = link.select("a[href]");
                for (Element col : cols) {
                    String item = col.getElementsByTag("a").attr("href");
                    if (item.length()>20 && item.contains("olx.pl/")) {
                        if (!allUrls.contains(item.substring(0, item.indexOf('#')))) {
                            System.out.println("kk45=" + item.substring(0, item.indexOf('#')));
                            allUrls.add(item.substring(0, item.indexOf('#')));
                        }
                    }
                }
            }
        }
        //#offers_table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a
        //#offers_table > tbody > tr:nth-child(6) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a

        return allUrls;
    }
    public Flat Parser_olx(String url) throws IOException {

        System.out.println("Jestem na - "+url);



//
        //url="https://www.olx.pl/oferta/wynajme-mieszkanie-CID3-IDmlVj2.html";
        Document doc = Jsoup.connect(url).get();
        Flat flat = new Flat();
        FlatDetail flatd = new FlatDetail();

        //FlatDetail dlatdetail = new FlatDetail();
        Elements newsHeadlines;// = doc.select("#parameters > ul:nth-child(1)");

        flat.setUrl(url);

        //title;+-
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        //description;+-
        newsHeadlines = doc.select("#textContent > p");
        flatd.setDescription(newsHeadlines.text());


        //price;+
        newsHeadlines = doc.select("#offeractions > div.price-label > strong");
        flat.setPrice(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));

        //0-właściciel //1-pośrednik
        //type_advertiser;
        newsHeadlines = doc.select("body > div.article-offer > section.section-offer-aside.container > aside > div.offer-aside-primary > div.offer-aside-group > div.box-contact-info.box-contact-info-private.text-center.hidden-print > h6");
        if(newsHeadlines.text().contains("prywatna")){flat.setType_advertiser(0);}
        else flat.setType_advertiser(1);

        //String place;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setPlace(newsHeadlines.text().substring(0,newsHeadlines.text().indexOf(",")));

        //String district;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setDistrict(newsHeadlines.text().substring(newsHeadlines.text().lastIndexOf(",")+2));

        //String street;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        //flat.setStreet(newsHeadlines.text());

        //Data utworzenia
        flat.setDatecreate(Calendar.getInstance().getTime());

        //FlatDetail flatDetail;
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        String label_param_floor_strong1 = "";
        String label_param_floor1 ="";
        String label_param_floor_a1="";
        String varparam;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3 ; j++) {

                label_param_floor_strong1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > th";
                label_param_floor1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td.col > table > tbody > tr > td > strong";
                label_param_floor_a1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > td > strong > a";

                newsHeadlines = doc.select(label_param_floor_strong1);

                if (newsHeadlines.text().contains("Oferta od")) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if(newsHeadlines.text().contains("Osoby prywatnej")){flat.setType_advertiser(0);}
                    else flat.setType_advertiser(1);
                }
                varparam = "Umeblowane";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    //flatd.setBuilding_material(newsHeadlines.text());
                    //todo
                }
                if (newsHeadlines.text().contains("Powierzchnia")) {
                    newsHeadlines = doc.select(label_param_floor1);
                    flat.setArea(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
                long kp1=0;
                varparam = "Czynsz (dodatkowo)";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor1);
                    kp1 = Long.parseLong(newsHeadlines.text().replaceAll("[^\\d]", ""));
                    flat.setExtra_rent(Math.toIntExact(kp1));
                }
                varparam = "Poziom";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Parter")){
                        flatd.setFloor("0");
                    }
                    else flatd.setFloor(newsHeadlines.text());
                }
                varparam = "Rodzaj zabudowy";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    flatd.setType_of_building(newsHeadlines.text());
                }
                varparam = "Liczba pokoi";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Kawalerka")){
                        flat.setNum_rooms(1);
                    }
                    else flat.setNum_rooms(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
            }
        }
        flat.setFlatDetail(flatd);
        flat.flatToString();

        //timeout
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flat;
    }

    public ArrayList<String> getUrls_dp(Integer pageStart, Integer pageEnd) throws IOException {
        ArrayList<String> allUrls = new ArrayList<String>();

        for (int i = pageStart; i < pageEnd+1 ; i++) {
            Document doc = Jsoup.connect("https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/page-"+i+"/v1c9008p"+i).get();
            Elements links = doc.getElementsByClass("offer");
            for (Element link : links) {
                Elements cols = link.select("a[href]");
                for (Element col : cols) {
                    String item = col.getElementsByTag("a").attr("href");
                    if (item.length()>20 && item.contains("olx.pl/")) {
                        if (!allUrls.contains(item.substring(0, item.indexOf('#')))) {
                            System.out.println("kk45=" + item.substring(0, item.indexOf('#')));
                            allUrls.add(item.substring(0, item.indexOf('#')));
                        }
                    }
                }
            }
        }
        //#offers_table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a
        //#offers_table > tbody > tr:nth-child(6) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a

        return allUrls;
    }
    public Flat Parser_dp(String url) throws IOException {

        System.out.println("Jestem na - "+url);


        //url="https://www.olx.pl/oferta/wynajme-mieszkanie-CID3-IDmlVj2.html";
        Document doc = Jsoup.connect(url).get();
        Flat flat = new Flat();
        FlatDetail flatd = new FlatDetail();

        //FlatDetail dlatdetail = new FlatDetail();
        Elements newsHeadlines;// = doc.select("#parameters > ul:nth-child(1)");

        flat.setUrl(url);

        //title;+-
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        //description;+-
        newsHeadlines = doc.select("#textContent > p");
        flatd.setDescription(newsHeadlines.text());


        //price;+
        newsHeadlines = doc.select("#offeractions > div.price-label > strong");
        flat.setPrice(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));

        //0-właściciel //1-pośrednik
        //type_advertiser;
        newsHeadlines = doc.select("body > div.article-offer > section.section-offer-aside.container > aside > div.offer-aside-primary > div.offer-aside-group > div.box-contact-info.box-contact-info-private.text-center.hidden-print > h6");
        if(newsHeadlines.text().contains("prywatna")){flat.setType_advertiser(0);}
        else flat.setType_advertiser(1);

        //String place;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setPlace(newsHeadlines.text().substring(0,newsHeadlines.text().indexOf(",")));

        //String district;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setDistrict(newsHeadlines.text().substring(newsHeadlines.text().lastIndexOf(",")+2));

        //String street;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        //flat.setStreet(newsHeadlines.text());

        //Data utworzenia
        flat.setDatecreate(Calendar.getInstance().getTime());

        //FlatDetail flatDetail;
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        String label_param_floor_strong1 = "";
        String label_param_floor1 ="";
        String label_param_floor_a1="";
        String varparam;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3 ; j++) {

                label_param_floor_strong1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > th";
                label_param_floor1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td.col > table > tbody > tr > td > strong";
                label_param_floor_a1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > td > strong > a";

                newsHeadlines = doc.select(label_param_floor_strong1);

                if (newsHeadlines.text().contains("Oferta od")) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if(newsHeadlines.text().contains("Osoby prywatnej")){flat.setType_advertiser(0);}
                    else flat.setType_advertiser(1);
                }
                varparam = "Umeblowane";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    //flatd.setBuilding_material(newsHeadlines.text());
                    //todo
                }
                if (newsHeadlines.text().contains("Powierzchnia")) {
                    newsHeadlines = doc.select(label_param_floor1);
                    flat.setArea(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
                varparam = "Czynsz (dodatkowo)";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor1);
                    flat.setExtra_rent(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
                varparam = "Poziom";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Parter")){
                        flatd.setFloor("0");
                    }
                    else flatd.setFloor(newsHeadlines.text());
                }
                varparam = "Rodzaj zabudowy";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    flatd.setType_of_building(newsHeadlines.text());
                }
                varparam = "Liczba pokoi";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Kawalerka")){
                        flat.setNum_rooms(1);
                    }
                    else flat.setNum_rooms(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
            }
        }
        flat.setFlatDetail(flatd);
        flat.flatToString();

        //timeout
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flat;
    }




    //todo
    public ArrayList<String> getUrls_gt(Integer pageStart, Integer pageEnd) throws IOException {
        ArrayList<String> allUrls = new ArrayList<String>();

        for (int i = pageStart; i < pageEnd+1 ; i++) {
            Document doc = Jsoup.connect("https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/page-"+i+"/v1c9008p"+i).get();
            Elements links = doc.getElementsByClass("offer");
            for (Element link : links) {
                Elements cols = link.select("a[href]");
                for (Element col : cols) {
                    String item = col.getElementsByTag("a").attr("href");
                    if (item.length()>20 && item.contains("olx.pl/")) {
                        if (!allUrls.contains(item.substring(0, item.indexOf('#')))) {
                            System.out.println("kk45=" + item.substring(0, item.indexOf('#')));
                            allUrls.add(item.substring(0, item.indexOf('#')));
                        }
                    }
                }
            }
        }
        //#offers_table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a
        //#offers_table > tbody > tr:nth-child(6) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > h3 > a

        return allUrls;
    }
    public Flat Parser_gt(String url) throws IOException {

        System.out.println("Jestem na - "+url);


        //url="https://www.olx.pl/oferta/wynajme-mieszkanie-CID3-IDmlVj2.html";
        Document doc = Jsoup.connect(url).get();
        Flat flat = new Flat();
        FlatDetail flatd = new FlatDetail();

        //FlatDetail dlatdetail = new FlatDetail();
        Elements newsHeadlines;// = doc.select("#parameters > ul:nth-child(1)");

        flat.setUrl(url);

        //title;+-
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        //description;+-
        newsHeadlines = doc.select("#textContent > p");
        flatd.setDescription(newsHeadlines.text());


        //price;+
        newsHeadlines = doc.select("#offeractions > div.price-label > strong");
        flat.setPrice(Integer.parseInt(newsHeadlines.text().replaceAll( "[^\\d]", "" )));

        //0-właściciel //1-pośrednik
        //type_advertiser;
        newsHeadlines = doc.select("body > div.article-offer > section.section-offer-aside.container > aside > div.offer-aside-primary > div.offer-aside-group > div.box-contact-info.box-contact-info-private.text-center.hidden-print > h6");
        if(newsHeadlines.text().contains("prywatna")){flat.setType_advertiser(0);}
        else flat.setType_advertiser(1);

        //String place;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setPlace(newsHeadlines.text().substring(0,newsHeadlines.text().indexOf(",")));

        //String district;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        flat.setDistrict(newsHeadlines.text().substring(newsHeadlines.text().lastIndexOf(",")+2));

        //String street;+
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > div.offer-titlebox__details > a > strong");
        //flat.setStreet(newsHeadlines.text());

        //Data utworzenia
        flat.setDatecreate(Calendar.getInstance().getTime());

        //FlatDetail flatDetail;
        newsHeadlines = doc.select("#offerdescription > div.offer-titlebox > h1");
        flat.setTitle(newsHeadlines.text());

        String label_param_floor_strong1 = "";
        String label_param_floor1 ="";
        String label_param_floor_a1="";
        String varparam;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3 ; j++) {

                label_param_floor_strong1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > th";
                label_param_floor1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td.col > table > tbody > tr > td > strong";
                label_param_floor_a1 = "#offerdescription > div.clr.descriptioncontent.marginbott20 > table > tbody > tr:nth-child("+i+") > td:nth-child("+j+") > table > tbody > tr > td > strong > a";

                newsHeadlines = doc.select(label_param_floor_strong1);

                if (newsHeadlines.text().contains("Oferta od")) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if(newsHeadlines.text().contains("Osoby prywatnej")){flat.setType_advertiser(0);}
                    else flat.setType_advertiser(1);
                }
                varparam = "Umeblowane";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    //flatd.setBuilding_material(newsHeadlines.text());
                    //todo
                }
                if (newsHeadlines.text().contains("Powierzchnia")) {
                    newsHeadlines = doc.select(label_param_floor1);
                    flat.setArea(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
                varparam = "Czynsz (dodatkowo)";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor1);
                    flat.setExtra_rent(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
                varparam = "Poziom";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Parter")){
                        flatd.setFloor("0");
                    }
                    else flatd.setFloor(newsHeadlines.text());
                }
                varparam = "Rodzaj zabudowy";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    flatd.setType_of_building(newsHeadlines.text());
                }
                varparam = "Liczba pokoi";
                if (newsHeadlines.text().contains(varparam)) {
                    newsHeadlines = doc.select(label_param_floor_a1);
                    if (newsHeadlines.text().contains("Kawalerka")){
                        flat.setNum_rooms(1);
                    }
                    else flat.setNum_rooms(Integer.parseInt(newsHeadlines.text().replaceAll("[^\\d]", "")));
                }
            }
        }
        flat.setFlatDetail(flatd);
        flat.flatToString();

        //timeout
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flat;
    }



}
