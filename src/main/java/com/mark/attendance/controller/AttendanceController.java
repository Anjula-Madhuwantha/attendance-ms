package com.mark.attendance.controller;

import com.mark.attendance.model.Attendance;
import com.mark.attendance.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping(value = "/check-in", headers = "X-Api-Version=v1")
    public String checkIn(@RequestParam Long employeeId,
                          @RequestParam Double latitude,
                          @RequestParam Double longitude) {

        return attendanceService.recordAttendance(employeeId,latitude,longitude,"CheckIn");
    }

    @PostMapping(value = "/check-out", headers = "X-Api-Version=v1")
    public String checkOut(@RequestParam Long employeeId,
                           @RequestParam Double latitude,
                           @RequestParam Double longitude) {

        return attendanceService.recordAttendance(employeeId,latitude,longitude, "Check-Out");
    }

    @GetMapping(value = "/attendance/actions", headers = "X-Api-Version=v1")
    public List<Attendance> getAttendanceByAction(@RequestParam ("startDate")LocalDate startDate,
                                                  @RequestParam ("endDate")LocalDate endDate,
                                                  @RequestParam String action) {

        return attendanceService.getAttendanceByAction(startDate, endDate, action);
    }
}
