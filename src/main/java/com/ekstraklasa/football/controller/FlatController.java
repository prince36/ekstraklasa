package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.model.CountModelCar;
import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.FlatDetail;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.service.CarService;
import com.ekstraklasa.football.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FlatController {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private FlatService flatService;

    //Parsowanie OtoDom
    @RequestMapping(value = "/pars01", method = RequestMethod.GET)
    public String homePage(Map<String, Object> map) throws IOException {
        od_parser od1 = new od_parser();

        //pobieramy urle
        ArrayList<String> allUrls = new ArrayList<String>();
        allUrls = od1.getUrls(1,5);

        ArrayList<String> allUrls2 = flatService.removeDuplicate(allUrls);
        System.out.println("Liczba nowych elementów: "+allUrls2.size());
        //parsujemy strony
        for (String url : allUrls2) {
            flatRepository.save(od1.Parser(url));
        }
        return "dashboard";
    }

    //ALL FLATS
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allFlats(Map<String, Object> map) {

        map.put("flats", flatRepository.findAll());


        map.put("cits", flatRepository.findAllCity());
        return "indexFlats";
    }

    //One FLat
    @RequestMapping(value = "/flats/1", method = RequestMethod.GET)
    public String allFlatsOne(Map<String, Object> map) {

        map.put("flats", flatRepository.findByUrl("https://www.otodom.pl/oferta/mieszkanie-34m2-czynsz-1500zl-o-miejskie-ID3k1kO.html"));


        map.put("cits", flatRepository.findAllCity());
        return "indexFlats";
    }
    //PAGE BRAND

    @RequestMapping(value = "/flats/{city}", method = RequestMethod.GET)
    public String getFlatsByCity(@PathVariable("city") String city, Model model) {

        //String nameCity=city;

        //model.addAttribute("flats", arc);
        //model.addAttribute("city", city);
        model.addAttribute("flats", flatRepository.findByCity(city));
        model.addAttribute("cits", flatRepository.findAllCity());

        return "indexFlats";
    }

}
