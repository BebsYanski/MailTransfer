package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

    List<Location> findByDriver_Id(Long driverId);
}
