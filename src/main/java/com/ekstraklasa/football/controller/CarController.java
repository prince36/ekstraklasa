package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.model.Car;
import com.ekstraklasa.football.model.CountModelCar;
import com.ekstraklasa.football.repo.CarRepository;
import com.ekstraklasa.football.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/home1", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model, Pageable pageable) {

        //model.put("car1", carRepository.findAll(pageable));
        System.out.println("liczbax: "+carRepository.findAll(pageable).getTotalElements());
        return "home";
    }
    //DASHBOARD
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Map<String, Object> model) {

        return "dashboard";
    }


    //CARS
    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String homeCars(Model model) {
        List<Car> arc = carRepository.findAll();
        Integer countCars = arc.size();

        model.addAttribute("cars", arc);
        model.addAttribute("countcars", countCars);
        model.addAttribute("brands", carRepository.findAllBrand());

        return "allCarsPage";
    }


    //PAGE CAR
    @RequestMapping(value = "/{idcars}", method = RequestMethod.GET)
    public String carPage(Map<String, Object> model, @PathVariable("idcars") Long id) {



        model.put("car1", carRepository.findOne(id));
        model.put("brands", carRepository.findAllBrand());
        return "carPage";
    }


    //PAGE BRAND
    @RequestMapping(value = "/cars/{brand}", method = RequestMethod.GET)
    public String getCarsByBrand(@PathVariable("brand") String brand, Model model) {

        String nameBrand=brand;

        List<Car> arc = carService.findByBrand(brand);

        List<CountModelCar> cMC = carService.brandModelStatistics(brand);
        for (final CountModelCar car: cMC) {
            System.out.println(car.getModel()+" ilość: "+car.getCountmodel());
        }


        Long countBrand = Long.valueOf(arc.size());

        model.addAttribute("cars", arc);
        model.addAttribute("brand", nameBrand);
        model.addAttribute("brands", carRepository.findAllBrand());
        model.addAttribute("countcarsb", countBrand);
        model.addAttribute("countcars", cMC);

        System.out.println("test17: " + countBrand+ " teste "+carService.brandModelStatistics(brand).get(0).getCountmodel());
        return "carsPageBrand";
    }

}