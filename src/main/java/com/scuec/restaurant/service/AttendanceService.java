package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Attendance;

public interface AttendanceService {
    int addStart(String attendanceId,String userId);

    int addLeave(String attendanceId,String userId);

    int updateFinish(String userId);

    int deleteAttendanceById(String attendanceId);

    IPage<Attendance> getAttendanceList(int currentPage, int pageSize, String userId,String loginName,String realName,  int attendanceType);

    Attendance getAttendanceById(String userId);
}
