package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.repo.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;

@Service
@Transactional
public class FlatServiceImpl implements FlatService {

    @Autowired
    FlatRepository flatRepository;

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
}
