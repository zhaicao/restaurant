package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Attendance;
import com.scuec.restaurant.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDao {

   

    int addStart(String attendanceId,
                 String userId);

    int addLeave(String attendanceId,String userId);

    int updateFinish(String userId);

    int deleteAttendance(String attendanceId);

    IPage<Attendance> getAttendanceList(Page<Object> objectPage, String userId, String loginName,String realName, int attendanceType);

    Attendance getAttendanceById(String userId);
}
