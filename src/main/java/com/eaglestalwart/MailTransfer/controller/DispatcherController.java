package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.DTO.DispatcherDto;
import com.eaglestalwart.MailTransfer.DTO.DriverDto;
import com.eaglestalwart.MailTransfer.configuration.JwtUtil;
import com.eaglestalwart.MailTransfer.models.Dispatcher;
import com.eaglestalwart.MailTransfer.models.Driver;
import com.eaglestalwart.MailTransfer.repository.DispatcherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DispatcherController {

    @Autowired
    private DispatcherRepo dispatcherRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/dispatcher")
    Dispatcher newDispatcher(@RequestBody Dispatcher dispatcher){
        return dispatcherRepo.save(dispatcher);
    }

    @PostMapping("/dispatcher/register")
    public ResponseEntity<?> register(@RequestBody Dispatcher newDispatcher){
        if(dispatcherRepo.findByEmail(newDispatcher.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
        newDispatcher.setPassword((passwordEncoder.encode(newDispatcher.getPassword())));
        dispatcherRepo.save(newDispatcher);

        String token = JwtUtil.generateToken(newDispatcher.getEmail());
        DispatcherDto response = new DispatcherDto(newDispatcher.getId(),newDispatcher.getFirstName(),newDispatcher.getEmail(),newDispatcher.getRole(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/dispatcher")
    List<Dispatcher> getDispatchers(){
        return dispatcherRepo.findAll();
    }

    @PostMapping("/dispatcher/login")
    public ResponseEntity<?> login(@RequestBody Dispatcher loginDispatcher){
        Dispatcher dispatcher = dispatcherRepo.findByEmail(loginDispatcher.getEmail());

        if(dispatcher == null || !passwordEncoder.matches(loginDispatcher.getPassword(), dispatcher.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(("Invalid email or password"));

        }

        String token = JwtUtil.generateToken(dispatcher.getEmail());
        DispatcherDto response = new DispatcherDto(dispatcher.getId(),dispatcher.getFirstName(),dispatcher.getEmail(), dispatcher.getRole(), token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/dispatcher/logout")
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

    @GetMapping("/dispatcher/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String email = JwtUtil.extractEmail(token.replace("Bearer ", ""));
        if(email == null || !JwtUtil.validateToken(token.replace("Bearer ", ""), email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        return ResponseEntity.ok("Token is Valid");
    }

    @GetMapping("/dispatcher/{id}")
    Dispatcher getDispatcherById(@PathVariable("id") Long id){
        return dispatcherRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Dispatcher with ID = "+ id + " does not exist."
                ));
    }

    @GetMapping("/dispatcherName/{firstName}")
    List<Dispatcher> getDispatcherByFirstName(@PathVariable("firstName") String firstName){
        return dispatcherRepo.findByFirstName(firstName);
    }


    @PutMapping("/dispatcher/{id}")
    Dispatcher updateDispatcher(@RequestBody Dispatcher newDispatcher, @PathVariable Long id){
        Dispatcher dispatcher = dispatcherRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Dispatcher with id "
                                + id +
                                " does not exist"
                ));

        if (newDispatcher.getFirstName() != null &&
                newDispatcher.getFirstName().length() > 0 &&
                !Objects.equals(dispatcher.getFirstName(), newDispatcher.getFirstName())){
            dispatcher.setFirstName(newDispatcher.getFirstName());
        }

        if (newDispatcher.getLastName() != null &&
                newDispatcher.getLastName().length() > 0 &&
                !Objects.equals(dispatcher.getLastName(), newDispatcher.getLastName())){
            dispatcher.setLastName(newDispatcher.getLastName());
        }

        if (newDispatcher.getPhoneNumber() != null &&
                newDispatcher.getPhoneNumber().toString().length() > 0 &&
                !Objects.equals(dispatcher.getPhoneNumber(), newDispatcher.getPhoneNumber())){
            dispatcher.setPhoneNumber(newDispatcher.getPhoneNumber());
        }

        if (newDispatcher.getAgency() != null &&
                newDispatcher.getAgency().length() > 0 &&
                !Objects.equals(dispatcher.getAgency(), newDispatcher.getAgency())){
            dispatcher.setAgency(newDispatcher.getAgency());
        }

        if (newDispatcher.getPassword() != null &&
                newDispatcher.getPassword().length() > 0 &&
                !Objects.equals(dispatcher.getPassword(), newDispatcher.getPassword())){
            dispatcher.setPassword(newDispatcher.getPassword());
        }
//!mail.getDispatcher().equals(newMail.getDispatcher()) &&
//                newMail.getDispatcher() != null
        if (newDispatcher.getMailList() != null &&
                !newDispatcher.getMailList().isEmpty() &&
                !dispatcher.getMailList().equals(newDispatcher.getMailList())
                ){
            dispatcher.setMailList(newDispatcher.getMailList());
        }

        if (newDispatcher.getEmail() !=null&&
                newDispatcher.getEmail().length() > 0 &&
                !Objects.equals(dispatcher.getEmail(), newDispatcher.getEmail())){
            Dispatcher dispatcherOptional = dispatcherRepo.findByEmail(newDispatcher.getEmail());
            if (dispatcherOptional != null){
                throw new IllegalStateException("email taken");
            }
            dispatcher.setEmail(newDispatcher.getEmail());
        }
        return dispatcherRepo.save(dispatcher);
    }


    @DeleteMapping("/dispatcher/{id}")
    String deleteUser(@PathVariable Long id){
        if(!dispatcherRepo.existsById(id)){
            throw new IllegalStateException(
                    "Dispatcher with id "
                            + id +
                            " does not exist"
            );
        }
        dispatcherRepo.deleteById(id);

        return "Dispatcher has been deleted successfully";
    }
}
