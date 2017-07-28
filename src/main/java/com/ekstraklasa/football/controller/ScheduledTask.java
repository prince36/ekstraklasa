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

    @Scheduled(fixedRate = 600000)
    public void executeTask() throws IOException {
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
    }
}
