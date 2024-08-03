package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.DTO.DriverDetails;
import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.models.Dispatcher;
import com.eaglestalwart.MailTransfer.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver,Long> {

    public List<Driver> findByFirstName(String firstName);

    Driver findByEmail(String email);

    @Query(value = "SELECT new com.eaglestalwart.MailTransfer.DTO.DriverDetails(d.id, d.role, d.firstName, d.lastName, d.email, d.phoneNumber, d.agency) " +
            "FROM Driver d " )
    List<DriverDetails> findAllDriverDetails();

    @Query(value = "SELECT new com.eaglestalwart.MailTransfer.DTO.DriverDetails(d.id, d.role, d.firstName, d.lastName, d.email, d.phoneNumber, d.agency) " +
            "FROM Driver d "+
            "WHERE d.id = :driverId")
    DriverDetails findAllDriverDetailsById(@Param("driverId") Long driverId);
//    Optional<Driver> findByEmail(String email);
}
