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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/flats")
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allFlats(@RequestParam(name = "p", defaultValue = "1") int pageNumber, Map<String, Object> map) {

        long lastPage;
        if(flatRepository.count() % 20>0){
            lastPage=flatRepository.count()/20+1;
        } else lastPage=flatRepository.count()/20;

        map.put("flats", flatService.getPage(pageNumber));
        map.put("cits", flatRepository.findAllCity());
        map.put("allNumFlats", flatRepository.count());
        map.put("city", "");
        map.put("page", pageNumber);
        map.put("lastPage", lastPage);

        return "indexFlats2";
    }

    //One FLat
    @RequestMapping(value = "/3,{idflat}", method = RequestMethod.GET)
    public String allFlatsOne(@PathVariable("idflat") Long idflat, Map<String, Object> map) {

        map.put("flat", flatRepository.findOne(idflat));
        map.put("cits", flatRepository.findAllCity());
        return "pageFlat";
    }


    //All Flats For City
    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public String getFlatsByCity(@PathVariable("city") String city, @RequestParam(name = "p", defaultValue = "1") int pageNumber, Map<String, Object> map) {

        long lastPage;
        if(flatRepository.countByCity(city) % 20>0){
            lastPage=flatRepository.countByCity(city)/20+1;
        } else lastPage=flatRepository.countByCity(city)/20;

        map.put("flats", flatService.getPageforCity(pageNumber, city));
        map.put("cits", flatRepository.findAllCity());
        map.put("allNumFlats", flatRepository.countByCity(city));
        map.put("city", "/"+city);
        map.put("page", pageNumber);
        map.put("lastPage", lastPage);

        return "indexFlats2";
    }


    @RequestMapping(value = "/edit/3,{idflat}", method = RequestMethod.GET)
    public String editFlat(@RequestParam(name = "p", defaultValue = "1") int pageNumber, Model model) {
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



    @GetMapping("/new")
    public ModelAndView order() {

        Map< String, String > country1 = new LinkedHashMap<String, String>();
        for (String countr : flatRepository.findAllCity()) {
            try {
                if (countr.length()>3) {
                    country1.put(countr, countr);
                }
            }
            catch (NullPointerException xx){
                System.out.println("nullpointer");
            }
        }
        //country1.keySet();


        //model.addAttribute("cits", flatRepository.findAllCity());
        //model.addAttribute("cits_lhm", country1);

        //model.addAttribute("order", new Order());
        return new ModelAndView("newOrder", "command", new Order());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newOrderFlats(Model model, @Valid @ModelAttribute Order order, BindingResult result) {

        if (result.hasErrors()) {
            return "error";
        }

        Map< String, String > country1 = new LinkedHashMap<String, String>();
        for (String countr : flatRepository.findAllCity()) {
            try {
                if (countr.length()>3) {
                    country1.put(countr, countr);
                }
            }
            catch (NullPointerException xx){
                System.out.println("nullpointer");
            }
        }
        //country1.keySet();

        model.addAttribute("email", order.getEmail());
        model.addAttribute("price_from", order.getPrice_from());
        model.addAttribute("price_to", order.getPrice_to());
        model.addAttribute("location", order.getLocation());
        model.addAttribute("district", order.getDistrict());
        model.addAttribute("numrooms", order.getNumrooms());

        model.addAttribute("cits", flatRepository.findAllCity());
        model.addAttribute("cits_lhm", country1);
        return "newOrder";
    }

}
