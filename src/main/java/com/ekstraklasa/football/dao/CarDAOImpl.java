package com.ekstraklasa.football.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Repository
public class CarDAOImpl implements CarDAO {




/*
    //@Autowired
    //private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addCar(Car car) {
        getCurrentSession().save(car);
    }

    public void updateCar(Car car) {
        Car teamToUpdate = getCar(car.getIdcars());
        //todo
        getCurrentSession().update(teamToUpdate);

    }

    public Car getCar(long id) {
        Car car = (Car) getCurrentSession().get(Car.class, id);
        return car;
    }

    public Car getCar(String name) {
        Car car = (Car) getCurrentSession().get(Car.class, name);
        return car;
    }

    public void deleteCar(long id) {
        Car car = getCar(id);
        if (car != null)
            getCurrentSession().delete(car);
    }

    @SuppressWarnings("unchecked")
    public List<Car> getCars() {
        return getCurrentSession().createQuery("from Car").list();
    }

*/

}
