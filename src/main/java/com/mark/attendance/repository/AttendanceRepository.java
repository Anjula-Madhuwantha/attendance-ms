package com.mark.attendance.repository;

import com.mark.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findAll();

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND FUNCTION('DATE', a.timestamp) = :date AND a.action = :action")
    List<Attendance> findByEmployeeIdAndTimestampDate(Long employeeId, LocalDate date, String action);


}
