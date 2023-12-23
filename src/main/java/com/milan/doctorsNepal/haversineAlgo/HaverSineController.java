package com.milan.doctorsNepal.haversineAlgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/distance")
public class HaverSineController {
    @Autowired
    private HaversineService haversineService;

    @GetMapping("/haversine")

    public double getHaversineDistance(
            @RequestParam double startLat,
            @RequestParam double startLon,
            @RequestParam double endLat,
            @RequestParam double endLon) {

        double distance = haversineService.calculateHaversineDistance(startLat, startLon, endLat, endLon);

        return  distance;
    }
}
