package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apicars")
public class CarApiController {

    @Autowired
    private CarRepository carRepository;

    @RequestMapping(value = "/listPageable", method = RequestMethod.GET)
    Page<Car> employeesPageable(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @RequestMapping(value = "/{idcars}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("idcars") Long id) {
        return carRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getCarsAll() {
        return carRepository.findAll();
    }

}
