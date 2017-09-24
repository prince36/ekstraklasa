package com.ekstraklasa.football.repo;

import com.ekstraklasa.football.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>, JpaRepository<Order, Long> {


}
