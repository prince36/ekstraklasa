package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import com.ekstraklasa.football.service.EmailSender;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.FlatServiceImpl;
import com.ekstraklasa.football.service.OrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class ScheduledTask {


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
    //private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTask.class);
    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //co 20minut - 1200000

    @Scheduled(fixedRate = 1200000)
    public void pushAll(){
        for (Order order :
                orderRepository.findAll()) {
            System.out.println("k789: "+orderService.goPush(order));

            if (orderService.goPush(order).length()>20){

                emailSender.sendEmail2(order.getEmail(), "Pojawiły się nowe oferty mieszkaniowe", orderService.goPush(order));
                order.setLastPush(Calendar.getInstance().getTime());
                orderRepository.save(order);
            }
        }
    }

    @Scheduled(fixedRate = 1200000)
    public void executeTask() throws IOException {
        od_parser od1 = new od_parser();

        //pobieramy urle
        ArrayList<String> allUrls1 = new ArrayList<String>();
        allUrls1 = od1.getUrls_od(1,5);

        ArrayList<String> allUrls11 = flatService.removeDuplicate(allUrls1);
        System.out.println("TH: Liczba nowych elementów: "+allUrls11.size());
        //parsujemy strony
        for (String url : allUrls11) {
            flatRepository.save(od1.Parser_od(url));
            //if(url == allUrls11.get(allUrls11.size()-1)) {
            //    System.out.println("TH: Koniec");
            //}
        }

        //pobieramy urle
        ArrayList<String> allUrls2 = new ArrayList<String>();
        allUrls2 = od1.getUrls_olx(1,5);

        ArrayList<String> allUrls22 = flatService.removeDuplicate(allUrls2);
        System.out.println("Liczba nowych elementów: "+allUrls22.size());
        //parsujemy strony
        for (String url : allUrls22) {
            flatRepository.save(od1.Parser_olx(url));
        }

        //---DOM GRATKA---
        //pobieramy urle
        ArrayList<String> allUrls3 = new ArrayList<String>();
        allUrls3 = od1.getUrls_domGratka(1,5);

        ArrayList<String> allUrls33 = flatService.removeDuplicate(allUrls3);
        System.out.println("Liczba nowych elementów: "+allUrls33.size());
        //parsujemy strony
        for (String url : allUrls33) {
            flatRepository.save(od1.Parser_domGratka(url));
        }
    }


}