package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {

    List<Route> findByRouteNameContaining(String name);
}
