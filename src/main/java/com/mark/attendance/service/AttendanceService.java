package com.mark.attendance.service;

import com.mark.attendance.model.Attendance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {

    String recordAttendance(Long employeeId, Double latitude, Double longitude, String action);

    List<Attendance> getAttendanceByAction(LocalDate startDate, LocalDate endDate, String action);
}
