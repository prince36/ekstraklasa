package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.model.CountModelCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CarService {/*
    public void addCar(Car car);
    public void updateCar(Car car);
    public Car getCar(long id);
    public Car getCar(String name);
    public void deleteCar(int id);
    public List<Car> getCars();*/

    Page<Car> listAllByPage(Car pageable);

    Page<Car> listAllByPage(Pageable pageable);

    public List<Car> findByBrand(String brand);

    public List<CountModelCar> brandModelStatistics(String brand);
}
