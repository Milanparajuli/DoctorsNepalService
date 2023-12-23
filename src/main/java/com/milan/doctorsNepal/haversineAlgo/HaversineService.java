package com.milan.doctorsNepal.haversineAlgo;

import org.springframework.stereotype.Service;

@Service
public class HaversineService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public double calculateHaversineDistance(double startLat, double startLon, double endLat, double endLon) {
        // Convert degrees to radians
        double startLatRad = Math.toRadians(startLat);
        double startLonRad = Math.toRadians(startLon);
        double endLatRad = Math.toRadians(endLat);
        double endLonRad = Math.toRadians(endLon);

        // Calculate differences
        double latDiff = endLatRad - startLatRad;
        double lonDiff = endLonRad - startLonRad;

        // Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(startLatRad) * Math.cos(endLatRad) * Math.pow(Math.sin(lonDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate distance
        return EARTH_RADIUS_KM * c;
    }
}
