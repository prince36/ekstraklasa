package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.repo.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FlatServiceImpl implements FlatService {

    @Autowired
    FlatRepository flatRepository;

    private final static int PAGESIZE = 20;

    @Override
    public ArrayList<String> removeDuplicate(ArrayList<String> listUrls) {
        ArrayList<String> returnList = new ArrayList<String>();
        for (String url :
                listUrls) {
            if (flatRepository.findByUrl(url).isEmpty()) {
                returnList.add(url);
            }
        }

        return returnList;
    }

    public List<Flat> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "idflat");

        return flatRepository.findAll(request).getContent();
    }

    public List<Flat> getPageforCity(int pageNumber, String city) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "idflat");

        return flatRepository.findByCity(city, request);
    }

    @Override
    public Map<String, String> getAllCity() {
        Map< String, String > country12 = new LinkedHashMap<String, String>();
        for (String countr : flatRepository.findAllCity()) {
            try {
                if (countr.length()>3) {
                    country12.put(countr, countr);
                }
            }
            catch (NullPointerException xx){
                //System.out.println("nullpointer");
            }
        }
        return country12;
    }

}
