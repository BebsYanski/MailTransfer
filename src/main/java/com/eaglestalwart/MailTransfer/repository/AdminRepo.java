package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Administrator;
import com.eaglestalwart.MailTransfer.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Administrator, Long> {
    public List<Administrator> findByFirstName(String firstName);

    Administrator findByEmail(String email);

}
