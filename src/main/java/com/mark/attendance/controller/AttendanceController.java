package com.mark.attendance.controller;

import com.mark.attendance.controller.requset.CheckInRequest;
import com.mark.attendance.model.Attendance;
import com.mark.attendance.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping(value = "/check-in", headers = "X-Api-Version=v1")
    public String checkIn(@RequestBody CheckInRequest checkInRequest) {

        return attendanceService.recordAttendance(checkInRequest.getEmployeeId(),
                checkInRequest.getLatitude(),
                checkInRequest.getLongitude(),
                "CheckIn");
    }

    @PostMapping(value = "/check-out", headers = "X-Api-Version=v1")
    public String checkOut(@RequestBody CheckInRequest checkInRequest) {

        return attendanceService.recordAttendance(checkInRequest.getEmployeeId(),
                checkInRequest.getLatitude(),
                checkInRequest.getLongitude(),
                "Check-Out");
    }

    @GetMapping(value = "/attendance/actions", headers = "X-Api-Version=v1")
    public List<Attendance> getAttendanceByAction(@RequestParam("startDate") LocalDate startDate,
                                                  @RequestParam("endDate") LocalDate endDate,
                                                  @RequestParam String action) {

        return attendanceService.getAttendanceByAction(startDate, endDate, action);
    }
}
