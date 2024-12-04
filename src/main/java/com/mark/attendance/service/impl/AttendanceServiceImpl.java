package com.mark.attendance.service.impl;

import com.mark.attendance.controller.response.LocationResponse;
import com.mark.attendance.model.Attendance;
import com.mark.attendance.repository.AttendanceRepository;
import com.mark.attendance.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final RestTemplate restTemplate;

    private static final Double officeLatitude = 6.921145443732835;
    private static final Double officeLongitude = 79.97456788192477;

    @Override
    public String  recordAttendance(Long employeeId, String action) {

        String url = "http://ip-api.com/json/";

        LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);

        Double allowRadius = 0.01;

        Double distance = calculateDistance(response.getLat(), response.getLon());

        System.out.println("Distance: "+ distance);

        if (distance < allowRadius) {
            return "Attendance failed. You are outside the allowed geolocation range of the office.";
        }

        LocalDate today = LocalDate.now();
        List<Attendance> todayAttendance = attendanceRepository.findByEmployeeIdAndTimestampDate(employeeId, today, action);

        if (!todayAttendance.isEmpty()) {
            return "You have already recorded your attendance for today.";
        }

        LocalDateTime timeStamp = LocalDateTime.now();

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setAction(action);
        attendance.setTimestamp(timeStamp);
        attendanceRepository.save(attendance);
        return action + " record successfully!";
    }

    private Double calculateDistance(Double lat2, Double lon2) {

        System.out.println("Latitude: "+lat2);
        System.out.println("Longitude: "+lon2);

            final int R = 6371; // Earth's radius in kilometers
            double dLat = Math.toRadians(lat2 - officeLatitude);
            double dLon = Math.toRadians(lon2 - officeLongitude);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(officeLatitude)) * Math.cos(Math.toRadians(lat2)) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c; // Distance in kilometers
    }

    @Override
    public List<Attendance> getAttendanceHistory() {

        return attendanceRepository.findAll();
    }
}
