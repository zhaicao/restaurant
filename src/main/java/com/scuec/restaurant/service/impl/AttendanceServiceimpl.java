package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.dao.AttendanceDao;
import com.scuec.restaurant.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceimpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public int addStart(String attendanceId, String userId) {
        return attendanceDao.addStart(attendanceId,userId);
    }

    @Override
    public int addLeave(String attendanceId, String userId) {
        return attendanceDao.addLeave(attendanceId,userId);
    }

    @Override
    public int updateFinish(String attendanceId) {
        return attendanceDao.updateFinish(attendanceId);
    }

    @Override
    public int deleteAttendanceById(String attendanceId) {
        return attendanceDao.deleteAttendance(attendanceId);
    }
}
