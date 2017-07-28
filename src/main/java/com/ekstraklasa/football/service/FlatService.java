package com.ekstraklasa.football.service;


import com.ekstraklasa.football.repo.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;


public interface FlatService  {

    ArrayList<String> removeDuplicate(ArrayList<String> listUrls);

}
