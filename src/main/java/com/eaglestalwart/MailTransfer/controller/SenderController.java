package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.models.Sender;
import com.eaglestalwart.MailTransfer.repository.SenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class SenderController {
    
    @Autowired
    private SenderRepo senderRepo;

    @PostMapping("/sender")
    Sender newSender(@RequestBody Sender newSender){
        return senderRepo.save(newSender);
    }

    @GetMapping("/sender")
    List<Sender> getSenders(){
        return senderRepo.findAll();
    }


    @GetMapping("/sender/{id}")
    Sender getSenderById(@PathVariable("id") Long id){
        return senderRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Sender with id "
                                + id +
                                " does not exist"
                ));
    }

   /* @GetMapping("/senderName/{senderName}")
    List<Sender> findSenderByName(@PathVariable("senderName") String name){

        return  senderRepo.findByName(name);

    }*/

    @GetMapping("/senderName/{senderName}")
    List<Sender> getSenderByFirstNameContaining(@PathVariable("senderName") String name){

        return senderRepo.findByNameContaining(name);
    }


    @PutMapping("/sender/{id}")
    Sender updateSender(@RequestBody Sender newSender, @PathVariable("id") Long id){
        Sender sender = senderRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Sender with id "
                                + id +
                                " does not exist"
                ));

        if (newSender.getName() != null &&
                newSender.getName().length() > 0 &&
                !Objects.equals(sender.getName(), newSender.getName())){
            sender.setName(newSender.getName());
        }


        if (newSender.getContact() != null &&
                newSender.getContact().toString().length() > 0 &&
                !Objects.equals(sender.getContact(), newSender.getContact())){
            sender.setContact(newSender.getContact());
        }

        if (newSender.getAgency() != null &&
                newSender.getAgency().length() > 0 &&
                !Objects.equals(sender.getAgency(), newSender.getAgency())){
            sender.setAgency(newSender.getAgency());
        }



        if (newSender.getEmail() !=null&&
                newSender.getEmail().length() > 0 &&
                !Objects.equals(sender.getEmail(), newSender.getEmail())){

            sender.setEmail(newSender.getEmail());
        }
        return senderRepo.save(sender);
    }


    @DeleteMapping("/sender/{id}")
    String deleteSender(@PathVariable("id") Long id){
        if(!senderRepo.existsById(id)){
            throw new IllegalStateException(
                    "Sender with id "
                            + id +
                            " does not exist"
            );
        }
        senderRepo.deleteById(id);

        return "Sender has been deleted successfully";
    }
}
