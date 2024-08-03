package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.DTO.AssignDriverDTO;
import com.eaglestalwart.MailTransfer.DTO.LocationDto;
import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.DTO.MailDto;
import com.eaglestalwart.MailTransfer.models.Location;
import com.eaglestalwart.MailTransfer.models.Mail;
import com.eaglestalwart.MailTransfer.models.Recipient;
import com.eaglestalwart.MailTransfer.models.Sender;
import com.eaglestalwart.MailTransfer.repository.*;
import com.eaglestalwart.MailTransfer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class MailController {

    @Autowired
    private MailRepo mailRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private SenderRepo senderRepo;

    @Autowired
    private RecipientRepo recipientRepo;

    @Autowired
    private MailService mailService;



    @GetMapping("/{driverId}/mailss")
    public ResponseEntity<List<MailDetailsDto>> getMailsAssignedToDriver(@PathVariable Long driverId){
        List<MailDetailsDto> mails = mailService.getMailAssignedToDriver(driverId);
        return ResponseEntity.ok(mails);
    }


    @GetMapping("/mailDetails")
    public List<MailDetailsDto> getAllMailDetails(){
        return mailService.getAllMailDetails();
    }

    @GetMapping("/{id}/mailDetails")
    public MailDetailsDto getMailDetailsById(@PathVariable Long id){
        return mailService.getMailDetailsById(id);
    }


    @PutMapping("/{mailId}/status")
    public ResponseEntity<Void> updateDeliveryStatus(@PathVariable Long mailId, @RequestBody Map<String, Boolean> status){
        boolean delivered = status.get("delivered");
        mailService.updateDeliveryStatus(mailId, delivered);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mails")
    public Mail createParcel(@RequestBody MailDto mailDto) {

        Sender newSender = senderRepo.save(new Sender(mailDto.getSenderName(),mailDto.getSenderEmail(),mailDto.getSenderContact(),mailDto.getSenderAgency()));
        Recipient newRecipient = recipientRepo.save(new Recipient(mailDto.getRecipientName(),mailDto.getRecipientEmail(),mailDto.getRecipientContact(),mailDto.getRecipientDestination()));
        Mail mail = new Mail(mailDto.getContent(),mailDto.getCost(),mailDto.getPaymentMethod(),mailDto.isPriority());
        mail.setSender(newSender);
        mail.setRecipient(newRecipient);
        return mailRepo.save(mail);
    }

    @PostMapping("/mail")
    Mail newMail(@RequestBody Mail newMail){
        return mailRepo.save(newMail);
    }

    @GetMapping("/aMail/{trackingNumber}")
    public ResponseEntity<MailDetailsDto> getMailByTrackingNumber(@PathVariable String trackingNumber){
        MailDetailsDto mailDetails = mailService.getMailByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(mailDetails);
    }

    @GetMapping("/mail/{trackingNumber}/location")
    public ResponseEntity<?> getMailLocation(@PathVariable String trackingNumber){
        Optional<Mail> mailOpt = mailRepo.findByTrackingId(trackingNumber);
        if(mailOpt.isPresent()){
            Mail mail = mailOpt.get();
            Location location = mail.getDriver().getLocation();
            if(location != null){
            LocationDto locationDto = new LocationDto(mail.getLocation().getLongitude(),mail.getLocation().getLongitude());
            return ResponseEntity.ok(locationDto);

            }
        } 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }

    @GetMapping("/mails")
    List<Mail> getMails(){
        return mailRepo.findAll();
    }

    @GetMapping("/mail/{id}")
    Mail getMailById(@PathVariable("id") Long id){
        return mailRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Mail with id "
                                + id +
                                " does not exist"
                ));
    }

    @GetMapping("/track/{trackingId}")
    Mail findMailByTrackingId(@PathVariable("trackingId") String trackingId){

        return  mailRepo.findByTrackingId(trackingId).orElseThrow(() -> new IllegalStateException(
                "Mail with id "
                        + trackingId +
                        " does not exist"
        ));

    }

    @PutMapping("/mail/{id}")
    public Mail updateMail(@PathVariable Long id, @RequestBody MailDto mailDto){
        return mailService.updateMail(id, mailDto);
    }

    @PutMapping("/{id}/assign-driver")
    public Mail assignDriverToMail(@PathVariable Long id, @RequestBody AssignDriverDTO assignDriverDTO){
        return mailService.assignDriver(id, assignDriverDTO);
    }


   /* @PutMapping("/mail/{id}")
    Mail updateMail(@RequestBody Mail newMail, @PathVariable("id") Long id){
        Mail mail = mailRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Sender with id "
                                + id +
                                " does not exist"
                ));

        if (newMail.getContent() != null &&
                newMail.getContent().length() > 0 &&
                !Objects.equals(mail.getContent(), newMail.getContent())){
            mail.setContent(newMail.getContent());
        }


        if (newMail.getCost() > 0 &&
                !Objects.equals(mail.getCost(), newMail.getCost())){
            mail.setCost(newMail.getCost());
        }

        if (newMail.getDescription() != null &&
                newMail.getDescription().length() > 0 &&
                !Objects.equals(mail.getDescription(), newMail.getDescription())){
            mail.setDescription(newMail.getDescription());
        }



        if (newMail.getDeliveryStatus() !=null&&
                newMail.getDeliveryStatus().length() > 0 &&
                !Objects.equals(mail.getDeliveryStatus(), newMail.getDeliveryStatus())){

            mail.setDeliveryStatus(newMail.getDeliveryStatus());
        }

        if (!newMail.isPriority() &&
                !Objects.equals(mail.isPriority(), newMail.isPriority())){

            mail.setPriority(newMail.isPriority());
        }

        if (newMail.getDispatcher() != null){

            mail.setDispatcher(newMail.getDispatcher());
        }

        if (newMail.getSender() != null ){

            mail.setSender(newMail.getSender());
        }

        if (newMail.getRecipient() != null ){

            mail.setRecipient(newMail.getRecipient());
        }

        if (newMail.getDriver() != null ){

            mail.setDriver(newMail.getDriver());
        }

        if (newMail.getRoute() != null ){

            mail.setRoute(newMail.getRoute());
        }

        if (newMail.getLocation() != null ){

            mail.setLocation(newMail.getLocation());
        }


        return mailRepo.save(mail);
    }*/


    @DeleteMapping("/mail/{id}")
    String deleteMail(@PathVariable("id") Long id){
        if(!mailRepo.existsById(id)){
            throw new IllegalStateException(
                    "Mail with id "
                            + id +
                            " does not exist"
            );
        }
        mailRepo.deleteById(id);

        return "Mail has been deleted successfully";
    }



}
