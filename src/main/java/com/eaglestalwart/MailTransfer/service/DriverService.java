package com.eaglestalwart.MailTransfer.service;

import com.eaglestalwart.MailTransfer.DTO.DriverDetails;
import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.repository.DriverRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Transactional
    public List<DriverDetails> getAllDriverDetails(){
        return driverRepo.findAllDriverDetails();
    }

    @Transactional
    public DriverDetails getAllDriverDetailsById(Long driverId){
        return driverRepo.findAllDriverDetailsById(driverId);
    }
}
