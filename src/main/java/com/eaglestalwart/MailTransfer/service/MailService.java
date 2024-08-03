package com.eaglestalwart.MailTransfer.service;

import com.eaglestalwart.MailTransfer.DTO.AssignDriverDTO;
import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.DTO.MailDto;
import com.eaglestalwart.MailTransfer.models.*;
import com.eaglestalwart.MailTransfer.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    @Autowired
    private MailRepo mailRepo;


    @Autowired
    private SenderRepo senderRepo;

    @Autowired
    private RecipientRepo recipientRepo;

    @Autowired
    private DispatcherRepo dispatcherRepo;
    @Autowired
    private DriverRepo driverRepo;

    @Transactional
    public Mail updateMail(Long id, MailDto mailDto) {
        Mail mail = mailRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mail not found with id " + id));

        mail.setCost(mailDto.getCost());
        mail.setContent(mailDto.getContent());
        mail.setPaymentMethod(mailDto.getPaymentMethod());
        mail.setPriority(mailDto.isPriority());

        // Update sender's name and contact
        Sender sender = mail.getSender();
        if (sender != null) {
            sender.setName(mailDto.getSenderName());
            sender.setEmail(mailDto.getSenderEmail());
            sender.setContact(mailDto.getSenderContact());
            sender.setAgency(mailDto.getSenderAgency());
            senderRepo.save(sender);
        }

        // Update recipient's name and contact
        Recipient recipient = mail.getRecipient();
        if (recipient != null) {
            recipient.setName(mailDto.getRecipientName());
            recipient.setEmail(mailDto.getRecipientEmail());
            recipient.setContact(mailDto.getRecipientContact());
            recipient.setDestination(mailDto.getRecipientDestination());
            recipientRepo.save(recipient);
        }

       /* if (mailDto.getDispatcherId() != null) {
            Dispatcher dispatcher = dispatcherRepo.findById(mailDto.getDispatcherId())
                    .orElseThrow(() -> new EntityNotFoundException("Dispatcher not found with id " + mailDto.getDispatcherId()));
            mail.setDispatcher(dispatcher);
        }*/

        return mailRepo.save(mail);
    }

    @Transactional
    public List<MailDetailsDto> getAllMailDetails(){
        return mailRepo.findAllMailDetails();
    }

    @Transactional
    public MailDetailsDto getMailDetailsById(Long mailId){
        return mailRepo.findMailDetailsById(mailId);
    }

    @Transactional
    public Mail assignDriver(Long mailId, AssignDriverDTO assignDriverDTO) {
        Mail mail = mailRepo.findById(mailId)
                .orElseThrow(() -> new EntityNotFoundException("Mail not found with id " + mailId));
        Driver driver = driverRepo.findById(assignDriverDTO.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id " + assignDriverDTO.getDriverId()));

        mail.setDriver(driver); // Assuming Mail entity has setDriver method
        driver.getMails().add(mail); // Assuming Driver entity has getMails method

        driverRepo.save(driver); // Save the driver to update the relationship
        return mailRepo.save(mail);
    }

    public List<MailDetailsDto> getMailAssignedToDriver(Long driverId){
        return mailRepo.findMailsByDriverIdAndDeliveryStatusNot(driverId);
    }

    public List<MailDetailsDto> findMailsByDriver(Long driverId){
        return mailRepo.findByDriver(driverId);
    }

    public void updateDeliveryStatus(Long mailId, boolean delivered){
        Mail mail = mailRepo.findById(mailId).orElseThrow(() -> new EntityNotFoundException("Mail not found"));
        mail.setDeliveryStatus(delivered ? "DELIVERED" : "PENDING");
        mailRepo.save(mail);
    }

    public MailDetailsDto getMailByTrackingNumber(String trackingNumber){
        return mailRepo.findByTrackingNumber(trackingNumber);
    }
}
