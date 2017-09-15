package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/testorder")
    public String flatsforOrder(Model model) {

        Order ord = orderRepository.findOne((long) 1);
        System.out.println("test81="+ord.getLocation());

        List<Flat> flats = orderService.getFlatsPerOrder(orderRepository.findOne((long) 1));
        System.out.println("test80");

        model.addAttribute("flats", flats);
        return "indexFlats2";
    }



    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getAddNewOrderForm(Model model) {

        Map< String, String > country12 = new LinkedHashMap<String, String>();
        for (String countr : flatRepository.findAllCity()) {
            try {
                if (countr.length()>3) {
                    country12.put(countr, countr);
                }
            }
            catch (NullPointerException xx){
                System.out.println("nullpointer");
            }
        }
        Order newOrder = new Order();
        model.addAttribute("cits", flatRepository.findAllCity());
        model.addAttribute("cits_lhm", country12);
        model.addAttribute("newOrder", newOrder);
        return "newOrder";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newOrder(@ModelAttribute Order newOrder, BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
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
        orderRepository.save(newOrder);
        return "redirect:/flats/new";
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String getAllOrders(@ModelAttribute Order newOrder, BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
        if (result.hasErrors()) {
            return "error";
        }

        orderRepository.save(newOrder);
        return "redirect:/order/new";
    }

}
