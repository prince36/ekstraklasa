package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.OrderService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private FlatService flatService;

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
    public String getAddNewOrderForm(@RequestParam(name = "editOrder", defaultValue = "0") Order editOrder, Model model) {

        Order newOrder;
        if (editOrder!=null){
            newOrder = editOrder;
        } else newOrder = new Order();

        model.addAttribute("cits", flatRepository.findAllCity());
        model.addAttribute("cits_lhm", flatService.getAllCity());
        model.addAttribute("newOrder", newOrder);
        return "order/newOrder";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newOrder(@ModelAttribute Order newOrder, BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
        if (result.hasErrors()) {
            return "error";
        }

        orderRepository.save(newOrder);
        return "redirect:/order";
    }

    @RequestMapping(value = {"","/","/all"}, method = RequestMethod.GET)
    public String getAllOrders(Model model
    ) {
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("cits", flatRepository.findAllCity());
        return "order/indexOrder";
    }

    @RequestMapping(value = "/{orderId}/{operation}", method = RequestMethod.GET)
    public String editRemoveEmployee(@PathVariable("operation") String operation,
                                     @PathVariable("orderId") long orderId,
                                     final RedirectAttributes redirectAttributes,
                                     Model model) {
        if(operation.equals("delete")) {
            System.out.println("test215");
            if(orderService.deleteOrder(orderId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
                System.out.println("test216");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if(operation.equals("edit")){
            Order editOrder=orderRepository.findOne(orderId);
            if(editOrder!=null) {
                redirectAttributes.addAttribute("editOrder", editOrder);
                //model.addAttribute("cits_lhm", flatService.getAllCity());
                return "redirect:/order/new";
            } else {
                redirectAttributes.addFlashAttribute("status","notfound");
            }
        }
        return "redirect:/order/all";
    }
}
