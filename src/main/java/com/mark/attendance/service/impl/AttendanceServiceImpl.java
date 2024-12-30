package com.mark.attendance.service.impl;

import com.mark.attendance.model.Attendance;
import com.mark.attendance.repository.AttendanceRepository;
import com.mark.attendance.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private static final Double officeLatitude = 6.9211147;
    private static final Double officeLongitude = 79.9746109;
    private static final Double allowRadius = 0.01;

    @Override
    public String recordAttendance(String employeeId, Double latitude, Double longitude, String action) {

        if (latitude == null || longitude == null) {
            return "Invalid location data. Please provide valid latitude and longitude.";
        }

        Double distance = calculateDistance(latitude, longitude);
        System.out.println("Distance from office: " + distance + " kilometers");

        if (distance > allowRadius) {
            return "Attendance failed. You are outside the allowed geolocation range of the office.";
        }

        LocalDate today = LocalDate.now();
        List<Attendance> todayAttendance = attendanceRepository.findByEmployeeIdAndTimestampDate(employeeId, today, action);

        if (!todayAttendance.isEmpty()) {
            return "You have already recorded your " + action + " for today.";
        }

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setAction(action);
        attendance.setTimestamp(LocalDateTime.now());
        attendanceRepository.save(attendance);

        return action + " recorded successfully!";
    }

    private double calculateDistance(Double lat2, Double lon2) {

        System.out.println("Target Latitude: " + lat2);
        System.out.println("Target Longitude: " + lon2);

        final int EARTH_RADIUS_KM = 6371;

        double dLat = Math.toRadians(lat2 - officeLatitude);
        double dLon = Math.toRadians(lon2 - officeLongitude);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(officeLatitude)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    @Override
    public List<Attendance> getAttendanceByAction(LocalDate startDate, LocalDate endDate, String action) {
        return attendanceRepository.findByDateRangeAndAction(startDate, endDate, action);
    }
}
