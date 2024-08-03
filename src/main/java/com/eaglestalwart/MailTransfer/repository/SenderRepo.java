package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Recipient;
import com.eaglestalwart.MailTransfer.models.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SenderRepo extends JpaRepository<Sender,Long> {

    public List<Sender> findByName(String name);
    List<Sender> findByNameContaining(String name);

    Optional<Sender> findByEmail(String email);
}
