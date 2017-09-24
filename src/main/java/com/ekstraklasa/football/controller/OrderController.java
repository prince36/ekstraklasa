package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import com.ekstraklasa.football.service.EmailSender;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.OrderService;
import org.hibernate.annotations.Parameter;
import org.omg.CORBA.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private FlatService flatService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/testorder")
    public String flatsforOrder(HttpServletRequest request) {

        System.out.println("test698");
        for (Flat x :
                orderService.getFlatsPerOrderByDate(orderRepository.findOne((long) 80))) {

            System.out.println("test 555"+x.getTitle());
        }
        System.out.println("555 ilość ogłoszeń: "+orderService.getFlatsPerOrderByDate(orderRepository.findOne((long) 80)).size());

        for (Order order :
                orderRepository.findAll()) {
            System.out.println("998: "+order.getLastPush());
            if (orderService.goPush(order).length()>20){
                emailSender.sendEmail2(order.getEmail(), "ANCKLOX", orderService.goPush(order));
                order.setLastPush(Calendar.getInstance().getTime());
                orderRepository.save(order);
            }
        }
        System.out.println("999: " +Calendar.getInstance().getTime());
        return "redirect:/";
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
