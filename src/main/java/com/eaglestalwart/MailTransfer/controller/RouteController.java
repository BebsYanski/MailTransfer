package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.models.Route;
import com.eaglestalwart.MailTransfer.models.Route;
import com.eaglestalwart.MailTransfer.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class RouteController {
    @Autowired
    private RouteRepo routeRepo;

    @PostMapping("/route")
    Route newRoute(@RequestBody Route newRoute){
        return routeRepo.save(newRoute);
    }

    @GetMapping("/route")
    List<Route> getRoutes(){
        return routeRepo.findAll();
    }

    @GetMapping("/route/{id}")
    Route getRouteById(@PathVariable("id") Long id){
        return routeRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Route with id "
                                + id +
                                " does not exist"
                ));
    }

//    findByRouteNameContaining

    @GetMapping("/routeName/{routeName}")
    List<Route> getRouteByNameContaining(@PathVariable("routeName") String name){

        return routeRepo.findByRouteNameContaining(name);
    }


    @PutMapping("/route/{id}")
    Route updateRoute(@RequestBody Route newRoute, @PathVariable("id") Long id){
        Route route = routeRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Route with id "
                                + id +
                                " does not exist"
                ));


        if (newRoute.getOrigin() != null &&
                newRoute.getOrigin().length() > 0 &&
                !Objects.equals(route.getOrigin(), newRoute.getOrigin())){
            route.setOrigin(newRoute.getOrigin());
            route.setRouteName(newRoute.getOrigin()+"-"+route.getDestination());

        }


        if (newRoute.getDestination() != null &&
                newRoute.getDestination().toString().length() > 0 &&
                !Objects.equals(route.getDestination(), newRoute.getDestination())){
            route.setDestination(newRoute.getDestination());
            route.setRouteName(route.getOrigin()+"-"+newRoute.getDestination());

        }



        if ((newRoute.getOrigin() != null && newRoute.getDestination() != null) &&
                ((newRoute.getOrigin().toString().length() > 0) && (newRoute.getDestination().toString().length() > 0)) &&
                (!Objects.equals(route.getDestination(), newRoute.getDestination()) && (!Objects.equals(route.getOrigin(), newRoute.getOrigin())))){
//            route.setDestination(newRoute.getDestination());
            route.setRouteName(newRoute.getOrigin()+"-"+newRoute.getDestination());
        }

        if (newRoute.getStops() != null &&
                newRoute.getStops().length() > 0 &&
                !Objects.equals(route.getStops(), newRoute.getStops())){
            route.setStops(newRoute.getStops());
        }



        return routeRepo.save(route);
    }


    @DeleteMapping("/route/{id}")
    String deleteRoute(@PathVariable("id") Long id){
        if(!routeRepo.existsById(id)){
            throw new IllegalStateException(
                    "Route with id "
                            + id +
                            " does not exist"
            );
        }
        routeRepo.deleteById(id);

        return "Route has been deleted successfully";
    }
}
