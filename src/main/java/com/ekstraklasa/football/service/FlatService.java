package com.ekstraklasa.football.service;


import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.repo.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public interface FlatService  {

    ArrayList<String> removeDuplicate(ArrayList<String> listUrls);

    List<Flat> getPage(int pageNumber);

}
