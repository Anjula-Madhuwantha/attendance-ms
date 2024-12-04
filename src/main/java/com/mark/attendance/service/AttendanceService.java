package com.mark.attendance.service;

import com.mark.attendance.model.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {

    String recordAttendance(Long employeeId, String action);

    List<Attendance> getAttendanceHistory();
}
