package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.DTO.AdminDto;
import com.eaglestalwart.MailTransfer.DTO.DispatcherDto;
import com.eaglestalwart.MailTransfer.DTO.DriverDto;
import com.eaglestalwart.MailTransfer.configuration.JwtUtil;
import com.eaglestalwart.MailTransfer.models.Administrator;
import com.eaglestalwart.MailTransfer.models.Dispatcher;
import com.eaglestalwart.MailTransfer.models.Driver;
import com.eaglestalwart.MailTransfer.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/admin")
    Administrator newAdmin(@RequestBody Administrator newAdmin){
        return adminRepo.save(newAdmin);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> register(@RequestBody Administrator newAdmin){
        if(adminRepo.findByEmail(newAdmin.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
        newAdmin.setPassword((passwordEncoder.encode(newAdmin.getPassword())));
        adminRepo.save(newAdmin);

        String token = JwtUtil.generateToken(newAdmin.getEmail());
        AdminDto response = new AdminDto(newAdmin.getId(),newAdmin.getFirstName(),newAdmin.getEmail(),newAdmin.getRole(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/admin")
    List<Administrator> getAdmins(){
        return adminRepo.findAll();
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody Administrator loginAdmin){
        Administrator admin = adminRepo.findByEmail(loginAdmin.getEmail());

        if(admin == null || !passwordEncoder.matches(loginAdmin.getPassword(), admin.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(("Invalid email or password"));

        }

        String token = JwtUtil.generateToken(admin.getEmail());
        AdminDto response = new AdminDto(admin.getId(),admin.getFirstName(),admin.getEmail(), admin.getRole(), token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/logout")
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

    @GetMapping("/admin/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String email = JwtUtil.extractEmail(token.replace("Bearer ", ""));
        if(email == null || !JwtUtil.validateToken(token.replace("Bearer ", ""), email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        return ResponseEntity.ok("Token is Valid");
    }
    

    @GetMapping("/admin/{id}")
    Administrator getAdminById(@PathVariable("id") Long id){
        return adminRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Administrator with id "
                                + id +
                                " does not exist"
                ));
    }


    @GetMapping("/adminName/{adminName}")
    public List<Administrator> printStudentByFirstName(@PathVariable("adminName") String firstName){

              return  adminRepo.findByFirstName(firstName);

    }


    @PutMapping("/admin/{id}")
    Administrator updateAdmin(@RequestBody Administrator newAdmin, @PathVariable Long id){
        Administrator admin = adminRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Administrator with id "
                                + id +
                                " does not exist"
                ));

        if (newAdmin.getFirstName() != null &&
                newAdmin.getFirstName().length() > 0 &&
                !Objects.equals(admin.getFirstName(), newAdmin.getFirstName())){
            admin.setFirstName(newAdmin.getFirstName());
        }

        if (newAdmin.getLastName() != null &&
                newAdmin.getLastName().length() > 0 &&
                !Objects.equals(admin.getLastName(), newAdmin.getLastName())){
            admin.setLastName(newAdmin.getLastName());
        }

        if (newAdmin.getPhoneNumber() != null &&
                newAdmin.getPhoneNumber().toString().length() > 0 &&
                !Objects.equals(admin.getPhoneNumber(), newAdmin.getPhoneNumber())){
            admin.setPhoneNumber(newAdmin.getPhoneNumber());
        }

        if (newAdmin.getAgency() != null &&
                newAdmin.getAgency().length() > 0 &&
                !Objects.equals(admin.getAgency(), newAdmin.getAgency())){
            admin.setAgency(newAdmin.getAgency());
        }

        if (newAdmin.getPassword() != null &&
                newAdmin.getPassword().length() > 0 &&
                !Objects.equals(admin.getPassword(), newAdmin.getPassword())){
            admin.setPassword(newAdmin.getPassword());
        }

        if (newAdmin.getEmail() !=null&&
                newAdmin.getEmail().length() > 0 &&
                !Objects.equals(admin.getEmail(), newAdmin.getEmail())){
            Administrator administratorOptional = adminRepo.findByEmail(newAdmin.getEmail());
            if (administratorOptional != null){
                throw new IllegalStateException("email taken");
            }
            admin.setEmail(newAdmin.getEmail());
        }
        return adminRepo.save(admin);
    }

    /*@PutMapping("/admin/{id}")
    Administrator updateUser(@RequestBody Administrator newAdmin, @PathVariable Long id){
        return adminRepo.findById(id)
                .map(admin -> {
                    admin.setFirstName(newAdmin.getFirstName());
                    admin.setLastName(newAdmin.getLastName());
                    admin.setEmail(newAdmin.getEmail());
                    admin.setPhoneNumber(newAdmin.getPhoneNumber());
                    admin.setAgency(newAdmin.getAgency());
                    admin.setPassword(newAdmin.getPassword());
                    return adminRepo.save(admin);
                }).orElseThrow(()
                        -> new IllegalStateException(
                        "Administrator with id "
                                + id +
                                " does not exist"
                ));
    }*/


    @DeleteMapping("/admin/{id}")
    String deleteAdmin(@PathVariable Long id){
        if(!adminRepo.existsById(id)){
            throw new IllegalStateException(
                    "Administrator with id "
                            + id +
                            " does not exist"
            );
        }
        adminRepo.deleteById(id);

        return "Admin has been deleted successfully";
    }



}
