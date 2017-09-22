package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;
import com.ekstraklasa.football.repo.FlatRepository;
import com.ekstraklasa.football.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Flat> getFlatsPerOrder(Order order) {
        return flatRepository.findForOrder(order.getLocation(),order.getDistrict(),order.getPrice_from(),order.getPrice_to(),order.getNumrooms());
    }

    @Override
    public List<Flat> getFlatsPerOrderByDate(Order order) {
        return flatRepository.findForOrderByDate(order.getLocation(),order.getDistrict(),order.getPrice_from(),order.getPrice_to(),order.getNumrooms(),order.getLastPush());
    }



    @Override
    public Boolean deleteOrder(Long idorder) {
        if (orderRepository.findOne(idorder)!=null){
            orderRepository.delete(idorder);
            return true;
        }
        else return false;
    }

    @Override
    public String goPush(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for (Flat flat: getFlatsPerOrderByDate(order)) {
            sb.append("<p>");
            sb.append(flat.getUrl());
            sb.append("</p>");
            sb.append(" ");
        }
        sb.append("</html>");
        return String.valueOf(sb);
    }
}
