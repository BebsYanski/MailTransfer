package com.eaglestalwart.MailTransfer.controller;


import com.eaglestalwart.MailTransfer.DTO.DriverDetails;
import com.eaglestalwart.MailTransfer.DTO.DriverDto;
import com.eaglestalwart.MailTransfer.DTO.LocationDto;
import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.configuration.JwtUtil;
import com.eaglestalwart.MailTransfer.models.Driver;
import com.eaglestalwart.MailTransfer.models.Location;
import com.eaglestalwart.MailTransfer.repository.DriverRepo;
import com.eaglestalwart.MailTransfer.repository.LocationRepo;
import com.eaglestalwart.MailTransfer.service.DriverService;
import com.eaglestalwart.MailTransfer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MailService mailService;

    @GetMapping("/{driverId}/mails")
    public List<MailDetailsDto> getDriverMails(@PathVariable Long driverId){
        return mailService.findMailsByDriver(driverId);
    }

    @GetMapping("/driverDetails")
    public List<DriverDetails> getAllMailDetails(){
        return driverService.getAllDriverDetails();
    }

    @GetMapping("/{id}/driverDetails")
    public DriverDetails getMailDetailsById(@PathVariable Long id){
        return driverService.getAllDriverDetailsById(id);
    }


    @PutMapping("/driver/{driverId}/location")
    public ResponseEntity<?> updateDriverLocation(@PathVariable Long driverId, @RequestBody LocationDto locationDto){
        Optional<Driver> driverOpt = driverRepo.findById(driverId);
        if(driverOpt.isPresent()){
            Driver driver = driverOpt.get();

            Location location = driver.getLocation();

            if(location == null){
                location = new Location();
            }
            location.setLatitude(locationDto.getLatitude());
            location.setLongitude(locationDto.getLongitude());
            location.setTimestamp(new Timestamp(System.currentTimeMillis()));
            location = locationRepo.save(location);

            driver.setLocation(location);
            driverRepo.save(driver);

            return ResponseEntity.ok().build();
            } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        }


    }


    @PostMapping("/driver/register")
    public ResponseEntity<?> register(@RequestBody Driver newDriver){
        if(driverRepo.findByEmail(newDriver.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
        newDriver.setPassword((passwordEncoder.encode(newDriver.getPassword())));
        driverRepo.save(newDriver);

        String token = JwtUtil.generateToken(newDriver.getEmail());
        DriverDto response = new DriverDto(newDriver.getDriverId(),newDriver.getFirstName(),newDriver.getEmail(),newDriver.getRole(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/driver")
    List<Driver> getDrivers(){
        return driverRepo.findAll();
    }

    @GetMapping("/driver/{id}")
    Driver getDriverById(@PathVariable("id") Long id){
        return driverRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Driver with id "
                                + id +
                                " does not exist"
                ));
    }

    @GetMapping("/driverEmail/{email}")
    Driver getDriverByEmail(@PathVariable("email") String email){
        return driverRepo.findByEmail(email);
               /* .orElseThrow(() -> new IllegalStateException(
                        "Driver with email "
                                + email +
                                " does not exist"
                ));*/
    }

    @PostMapping("/driver/login")
    public ResponseEntity<?> login(@RequestBody Driver loginDriver){
        Driver driver = driverRepo.findByEmail(loginDriver.getEmail());

        if(driver == null || !passwordEncoder.matches(loginDriver.getPassword(), driver.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(("Invalid email or password"));

        }

        String token = JwtUtil.generateToken(driver.getEmail());
        DriverDto response = new DriverDto(driver.getId(),driver.getFirstName(),driver.getEmail(),driver.getRole(), token);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/driver/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token){
        try {
            String actualToken = token.replace("Bearer ", "");
            String email = JwtUtil.extractEmail(actualToken);
            if (email == null || !JwtUtil.validateToken(actualToken, email)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            return ResponseEntity.ok("Logout Successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logout Failed");
        }
    }

    @GetMapping("/driver/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String email = JwtUtil.extractEmail(token.replace("Bearer ", ""));
        if(email == null || !JwtUtil.validateToken(token.replace("Bearer ", ""), email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        return ResponseEntity.ok("Token is Valid");
    }



    @GetMapping("/driverName/{name}")
    public List<Driver> findDriverByFirstName(@PathVariable("name") String firstName){

        return  driverRepo.findByFirstName(firstName);

    }




    @PutMapping("/driver/{id}")
    Driver updateDriver(@RequestBody Driver newDriver, @PathVariable Long id){
        Driver driver = driverRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Administrator with id "
                                + id +
                                " does not exist"
                ));

        if (newDriver.getFirstName() != null &&
                newDriver.getFirstName().length() > 0 &&
                !Objects.equals(driver.getFirstName(), newDriver.getFirstName())){
            driver.setFirstName(newDriver.getFirstName());
        }

        if (newDriver.getLastName() != null &&
                newDriver.getLastName().length() > 0 &&
                !Objects.equals(driver.getLastName(), newDriver.getLastName())){
            driver.setLastName(newDriver.getLastName());
        }

        if (newDriver.getPhoneNumber() != null &&
                newDriver.getPhoneNumber().toString().length() > 0 &&
                !Objects.equals(driver.getPhoneNumber(), newDriver.getPhoneNumber())){
            driver.setPhoneNumber(newDriver.getPhoneNumber());
        }

        if (newDriver.getAgency() != null &&
                newDriver.getAgency().length() > 0 &&
                !Objects.equals(driver.getAgency(), newDriver.getAgency())){
            driver.setAgency(newDriver.getAgency());
        }

        if (newDriver.getPassword() != null &&
                newDriver.getPassword().length() > 0 &&
                !Objects.equals(driver.getPassword(), newDriver.getPassword())){
            driver.setPassword(newDriver.getPassword());
        }

        if (newDriver.getEmail() !=null&&
                newDriver.getEmail().length() > 0 &&
                !Objects.equals(driver.getEmail(), newDriver.getEmail())){
            Driver driverOptional = driverRepo.findByEmail(newDriver.getEmail());
            if (driverRepo.findByEmail(newDriver.getEmail()) != null){
                throw new IllegalStateException("email taken");
            }
            driver.setEmail(newDriver.getEmail());
        }
        return driverRepo.save(driver);
    }

    @DeleteMapping("/driver/{id}")
    String deleteDriver(@PathVariable Long id){
        if(!driverRepo.existsById(id)){
            throw new IllegalStateException(
                    "Driver with id "
                            + id +
                            " does not exist"
            );
        }
        driverRepo.deleteById(id);

        return "Driver has been deleted successfully";
    }
}
