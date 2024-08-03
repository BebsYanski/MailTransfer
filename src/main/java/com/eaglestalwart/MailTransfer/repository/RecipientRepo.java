package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient, Long> {
    public List<Recipient> findByName(String name);
    List<Recipient> findByNameContaining(String name);

    Optional<Recipient> findByEmail(String email);
}
