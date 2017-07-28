package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.model.CountModelCar;
import com.ekstraklasa.football.repo.CarOwnRepository;
import com.ekstraklasa.football.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarOwnRepository carOwnRepository;
    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Page<Car> listAllByPage(Car pageable) {
        return null;
    }

    @Override
    public Page<Car> listAllByPage(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findBybrand(brand);
    }


    @Override
    public List<CountModelCar> brandModelStatistics(String brand) {

        ArrayList<CountModelCar> countModelCarsL = new ArrayList<CountModelCar>();
        CountModelCar countModelCar;
        Long count;
        List<String> allmodelsForBrand = carRepository.findAllModelsForBrand(brand);
        for (final String tmp :
                allmodelsForBrand) {
            count= Long.valueOf(0);
            countModelCar=new CountModelCar();
            count=carRepository.count_model(brand,tmp);
            countModelCar= new CountModelCar(0, brand, tmp, "",carRepository.count_model(brand, tmp),carRepository.count_model(brand, tmp));
            System.out.println(countModelCar.getModel()+" Liczba: "+countModelCar.getCountmodel());
            //save countmodelcars
            countModelCarsL.add(countModelCar);
        }

        return countModelCarsL;
    }


/*
    @Autowired
    private CarDAO carDAO;


    @Override
    public void addCar(Car car) {

    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public Car getCar(long id) {
        return carDAO.getCar(id);
    }

    @Override
    public Car getCar(String name) {
        return null;
    }

    @Override
    public void deleteCar(int id) {

    }

    @Override
    public List<Car> getCars() {
        return carDAO.getCars();
    }*/
}
