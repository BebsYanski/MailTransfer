package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Administrator;
import com.eaglestalwart.MailTransfer.models.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispatcherRepo extends JpaRepository<Dispatcher, Long> {

    public List<Dispatcher> findByFirstName(String firstName);

    Dispatcher findByEmail(String email);
}
