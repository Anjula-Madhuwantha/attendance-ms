package com.mark.attendance.controller.response;

import lombok.Data;

@Data
public class LocationResponse {

    private String status; // API response status (e.g., "success" or "fail")
    private String country; // Country name
    private String countryCode; // Country code
    private String region; // Region name
    private String regionName; // Region full name
    private String city; // City name
    private String zip; // ZIP code
    private Double latitude; // Latitude
    private Double longitude; // Longitude
    private String timezone; // Timezone
    private String isp; // Internet Service Provider
    private String query; // IP address
}
