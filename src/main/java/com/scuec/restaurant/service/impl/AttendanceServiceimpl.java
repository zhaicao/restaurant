package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.AttendanceDao;
import com.scuec.restaurant.entities.Attendance;
import com.scuec.restaurant.service.AttendanceService;
import com.scuec.restaurant.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceimpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public int addStart(String attendanceId, String userId) {
        Attendance attendance = attendanceDao.getAttendanceById(userId);
        if (attendance == null){
            return attendanceDao.addStart(attendanceId,userId);
        }else
            return -1;
    }

    @Override
    public int addLeave(String attendanceId, String userId) {
        Attendance attendance = attendanceDao.getAttendanceById(userId);
        if (attendance == null){
            return attendanceDao.addLeave(attendanceId,userId);
        }else
            return -1;
    }

    @Override
    public int updateFinish(String userId) {
        return attendanceDao.updateFinish(userId);
    }

    @Override
    public int deleteAttendanceById(String attendanceId) {
        return attendanceDao.deleteAttendance(attendanceId);
    }

    @Override
    public IPage<Attendance> getAttendanceList(int currentPage, int pageSize, String userId, String loginName,String realName,int attendanceType) {
        return attendanceDao.getAttendanceList(new Page<>(currentPage, pageSize), userId,loginName, realName,attendanceType);
    }

    @Override
    public Attendance getAttendanceById(String userId) {
        return attendanceDao.getAttendanceById(userId);
    }
}
