package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.models.Location;
import com.eaglestalwart.MailTransfer.models.Mail;
import com.eaglestalwart.MailTransfer.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationRepo locationRepo;

    @PostMapping("/locations")
    Location newLocation(@RequestBody Location newLocation){
        return locationRepo.save(newLocation);
    }

    @GetMapping("/locations")
    List<Location> getLocation(){
        return locationRepo.findAll();
    }

}
