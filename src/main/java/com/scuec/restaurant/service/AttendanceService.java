package com.scuec.restaurant.service;

public interface AttendanceService {
    int addStart(String attendanceId,String userId);

    int addLeave(String attendanceId,String userId);

    int updateFinish(String attendanceId);

    int deleteAttendanceById(String attendanceId);
}
