package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.repo.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FlatRepository flatRepository;

    @Override
    public List<Flat> getFlatsPerOrder(Order order) {
        List<Flat> returnList;
        returnList = flatRepository.findForOrder(order.getLocation(),order.getPrice_from(),order.getPrice_to(),order.getDistrict(),order.getNumrooms());
        return returnList;
    }
}
