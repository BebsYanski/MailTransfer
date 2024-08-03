package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.models.Driver;
import com.eaglestalwart.MailTransfer.models.Recipient;
import com.eaglestalwart.MailTransfer.repository.RecipientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class RecipientController {

    @Autowired
    private RecipientRepo recipientRepo;

    @PostMapping("/recipient")
    Recipient newRecipient(@RequestBody Recipient newRecipient){
        return recipientRepo.save(newRecipient);
    }

    @GetMapping("/recipient")
    List<Recipient> getRecipients(){
        return recipientRepo.findAll();
    }


    @GetMapping("/recipient/{id}")
    Recipient getRecipientById(@PathVariable("id") Long id){
        return recipientRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Recipient with id "
                                + id +
                                " does not exist"
                ));
    }

   /* @GetMapping("/recipientName/{recipientName}")
    List<Recipient> findRecipientByName(@PathVariable("recipientName") String name){

        return  recipientRepo.findByName(name);

    }*/

    @GetMapping("/recipientName/{recipientName}")
    List<Recipient> getRecipientByNameContaining(@PathVariable("recipientName") String name){

        return recipientRepo.findByNameContaining(name);
    }


    @PutMapping("/recipient/{id}")
    Recipient updateRecipient(@RequestBody Recipient newRecipient, @PathVariable("id") Long id){
        Recipient recipient = recipientRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Recipient with id "
                                + id +
                                " does not exist"
                ));

        if (newRecipient.getName() != null &&
                newRecipient.getName().length() > 0 &&
                !Objects.equals(recipient.getName(), newRecipient.getName())){
            recipient.setName(newRecipient.getName());
        }


        if (newRecipient.getContact() != null &&
                newRecipient.getContact().toString().length() > 0 &&
                !Objects.equals(recipient.getContact(), newRecipient.getContact())){
            recipient.setContact(newRecipient.getContact());
        }

        if (newRecipient.getDestination() != null &&
                newRecipient.getDestination().length() > 0 &&
                !Objects.equals(recipient.getDestination(), newRecipient.getDestination())){
            recipient.setDestination(newRecipient.getDestination());
        }



        if (newRecipient.getEmail() !=null&&
                newRecipient.getEmail().length() > 0 &&
                !Objects.equals(recipient.getEmail(), newRecipient.getEmail())){

            recipient.setEmail(newRecipient.getEmail());
        }
        return recipientRepo.save(recipient);
    }


    @DeleteMapping("/recipient/{id}")
    String deleteRecipient(@PathVariable("id") Long id){
        if(!recipientRepo.existsById(id)){
            throw new IllegalStateException(
                    "Recipient with id "
                            + id +
                            " does not exist"
            );
        }
        recipientRepo.deleteById(id);

        return "Recipient has been deleted successfully";
    }
}
