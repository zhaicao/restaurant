package com.scuec.restaurant.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDao {

    int addStart(String attendanceId,
            String userId);

    int addLeave(String attendanceId,String userId);

    int updateFinish(String attendanceId);

    int deleteAttendance(String attendanceId);
}
