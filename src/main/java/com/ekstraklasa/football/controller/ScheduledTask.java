package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.parser.od_parser;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.service.FlatService;
import com.ekstraklasa.football.service.FlatServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

@Component
public class ScheduledTask {

    @Autowired
    FlatServiceImpl flatService;
    @Autowired
    FlatRepository flatRepository;

    //private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTask.class);
    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //co 20minut - 1200000

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
    }
}