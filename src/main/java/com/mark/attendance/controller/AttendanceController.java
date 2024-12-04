package com.mark.attendance.controller;

import com.mark.attendance.model.Attendance;
import com.mark.attendance.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping(value = "/check-in", headers = "X-Api-Version=v1")
    public String checkIn(@RequestParam Long employeeId) {

        return attendanceService.recordAttendance(employeeId, "Check-In");
    }

    @PostMapping(value = "/check-out", headers = "X-Api-Version=v1")
    public String checkOut(@RequestParam Long employeeId) {

        return attendanceService.recordAttendance(employeeId, "Check-Out");
    }

    @GetMapping(value = "/employees", headers = "X-Api-Version=v1")
    public List<Attendance> getHistory() {

        List<Attendance> attendanceList = attendanceService.getAttendanceHistory();

        return attendanceList;

    }
}
