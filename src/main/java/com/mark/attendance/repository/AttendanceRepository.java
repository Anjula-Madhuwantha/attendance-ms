package com.mark.attendance.repository;

import com.mark.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND DATE(a.timestamp) = :date AND a.action = :action")
    List<Attendance> findByEmployeeIdAndTimestampDate(String employeeId, LocalDate date, String action);

    @Query("SELECT a FROM Attendance a WHERE DATE(a.timestamp) BETWEEN :startDate AND :endDate AND a.action = :action")
    List<Attendance> findByDateRangeAndAction(LocalDate startDate, LocalDate endDate, String action);
}
