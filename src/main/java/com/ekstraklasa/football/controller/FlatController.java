package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.*;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import com.ekstraklasa.football.service.CarService;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    //Parsowanie OtoDom
    @RequestMapping(value = "/pars01", method = RequestMethod.GET)
    public String homePage(Map<String, Object> map) throws IOException {
        od_parser od1 = new od_parser();

        //pobieramy urle
        ArrayList<String> allUrls = new ArrayList<String>();
        allUrls = od1.getUrls_olx(1,5);

        ArrayList<String> allUrls2 = flatService.removeDuplicate(allUrls);
        System.out.println("Liczba nowych elementów: "+allUrls2.size());
        //parsujemy strony
        for (String url : allUrls2) {
            flatRepository.save(od1.Parser_olx(url));
        }
        return "indexFlats2";
    }

    //ALL FLATS
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allFlats(Map<String, Object> map) {

        map.put("flats", flatRepository.findAll());


        map.put("cits", flatRepository.findAllCity());
        return "indexFlats2";
    }

    //One FLat
    @RequestMapping(value = "/flats/1", method = RequestMethod.GET)
    public String allFlatsOne(Map<String, Object> map) {

        map.put("flats", flatRepository.findByUrl("https://www.otodom.pl/oferta/mieszkanie-34m2-czynsz-1500zl-o-miejskie-ID3k1kO.html"));


        map.put("cits", flatRepository.findAllCity());
        return "indexFlats2";
    }
    //PAGE BRAND

    @RequestMapping(value = "/flats/{city}", method = RequestMethod.GET)
    public String getFlatsByCity(@PathVariable("city") String city, Model model) {

        //String nameCity=city;

        //model.addAttribute("flats", arc);
        //model.addAttribute("city", city);
        model.addAttribute("flats", flatRepository.findByCity(city));
        model.addAttribute("cits", flatRepository.findAllCity());

        return "indexFlats2";
    }


    @RequestMapping(value = "/flats", method = RequestMethod.GET)
    public String viewCustomers(@RequestParam(name = "p", defaultValue = "1") int pageNumber, Model model) {
        String result = "<html>";

        List<Flat> flats = flatService.getPage(pageNumber);
        model.addAttribute("flats", flats);

        return "indexFlats2";
    }

    @RequestMapping(value = "/testorder")
    public String flatsforOrder(Model model) {

        Order ord = orderRepository.findOne((long) 1);
        System.out.println("test81="+ord.getLocation());

        List<Flat> flats = orderService.getFlatsPerOrder(orderRepository.findOne((long) 1));
        System.out.println("test80");

        model.addAttribute("flats", flats);
        return "indexFlats2";
    }

}
