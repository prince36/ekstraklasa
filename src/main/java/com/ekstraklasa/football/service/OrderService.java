package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Flat;
import com.ekstraklasa.football.model.Order;

import java.util.List;

public interface OrderService {
    List<Flat> getFlatsPerOrder(Order order);
}
