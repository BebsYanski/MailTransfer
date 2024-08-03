package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.DTO.MailDetailsDto;
import com.eaglestalwart.MailTransfer.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepo extends JpaRepository<Mail, Long> {

     Optional<Mail> findByTrackingId(String trackingId);
     List<MailDetailsDto> findByDriver_Id(Long driverId);


     @Query("SELECT new com.eaglestalwart.MailTransfer.DTO.MailDetailsDto(m.id, m.trackingId, m.cost, m.creationDate, m.deliveryStatus, m.content,s.name, s.contact, " +
             "s.agency, s.email, r.name, r.contact, r.destination, r.email, d.id, d.firstName, d.lastName, l.latitude, l.longitude) " +
             "FROM Mail m " +
             "JOIN m.sender s " +
             "JOIN m.recipient r " +
             "LEFT JOIN m.driver d " +
             "LEFT JOIN d.location l " +
             "WHERE m.trackingId = :trackingNumber")
     MailDetailsDto findByTrackingNumber(@Param("trackingNumber") String trackingNumber);



          @Query("SELECT new com.eaglestalwart.MailTransfer.DTO.MailDetailsDto(m.id, m.trackingId, m.cost, m.creationDate, m.deliveryStatus, m.content, s.name, s.contact, s.agency, s.email, r.name, r.contact, r.destination, r.email, d.id, d.firstName, d.lastName, l.latitude, l.longitude) " +
                  "FROM Mail m " +
                  "JOIN m.sender s " +
                  "JOIN m.recipient r " +
                  "LEFT JOIN m.driver d " +
                  "LEFT JOIN d.location l " +
                  "WHERE d.id = :driverId AND m.deliveryStatus != 'DELIVERED'")
          List<MailDetailsDto> findMailsByDriverIdAndDeliveryStatusNot(@Param("driverId") Long driverId);


     @Query("SELECT new com.eaglestalwart.MailTransfer.DTO.MailDetailsDto(m.id, m.trackingId, m.cost, m.creationDate, m.deliveryStatus, m.content,s.name, s.contact, " +
             "s.agency, s.email, r.name, r.contact, r.destination, r.email, d.id, d.firstName, d.lastName, l.latitude, l.longitude) " +
             "FROM Mail m " +
             "LEFT JOIN m.sender s " +
             "LEFT JOIN m.recipient r " +
             "LEFT JOIN m.driver d " +
             "LEFT JOIN d.location l " +
             "WHERE d.id = :driverId")
     List<MailDetailsDto> findByDriver(@Param("driverId") Long driverId);
//     List<Tuple> findMailsByDriver(@Param("driverId") Long driverId);

     @Query(value = "SELECT new com.eaglestalwart.MailTransfer.DTO.MailDetailsDto(m.id, m.trackingId, m.cost, m.creationDate, m.deliveryStatus, m.content,s.name, s.contact, " +
             "s.agency, s.email, r.name, r.contact, r.destination, r.email, d.id, d.firstName, d.lastName, l.latitude, l.longitude) " +
               "FROM Mail m " +
               "JOIN m.sender s "+
               "JOIN m.recipient r "+
               "LEFT JOIN m.driver d "+
               "LEFT JOIN d.location l"
     )
     List<MailDetailsDto> findAllMailDetails();

     @Query(value = "SELECT new com.eaglestalwart.MailTransfer.DTO.MailDetailsDto(m.id, m.trackingId, m.cost, m.creationDate, m.deliveryStatus, m.content,s.name, s.contact, " +
             "s.agency, s.email, r.name, r.contact, r.destination, r.email, d.id, d.firstName, d.lastName, l.latitude, l.longitude) " +
               "FROM Mail m " +
               "JOIN m.sender s "+
               "JOIN m.recipient r "+
               "LEFT JOIN m.driver d "+
               "LEFT JOIN d.location l "+
               "WHERE m.id = :mailId")
     MailDetailsDto findMailDetailsById(@Param("mailId") Long mailId);

     /*@Query(value = "SELECT  m.cost, m.content,s.name AS name, s.contact AS contact, s.agency AS agency, r.name AS name," +
             " r.contact AS contact, r.destination AS destination " +
               "FROM mail m " +
               "JOIN sender s ON m.sender_id = s.id "+
               "JOIN recipient r ON m.recipient_id = r.id",
          nativeQuery = true)
     List<MailDetailsDto> findAllMailDetails();


     @Query("SELECT m.id as mailId, m.trackingId as trackingId, m.cost as cost, m.content as content, " +
             "s.name as senderName, s.contact as senderContact, " +
             "r.name as recipientName, r.contact as recipientContact, r.destination as recipientDestination, " +
             "d.id as driverId, d.firstName as driverFirstName, d.lastName as driverLastName " +
             "FROM Mail m " +
             "LEFT JOIN m.sender s " +
             "LEFT JOIN m.recipient r " +
             "LEFT JOIN m.driver d " +
             "WHERE d.id = :driverId")*/

}
