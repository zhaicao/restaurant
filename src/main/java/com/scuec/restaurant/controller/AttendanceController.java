package com.scuec.restaurant.controller;


import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Attendance;
import com.scuec.restaurant.service.AttendanceService;
import com.scuec.restaurant.service.CommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/attendance")
@ApiOperation(value = "考勤管理", notes = "考勤管理相关业务")
@Slf4j
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CommonService commonService;


    @PostMapping("/addAttendance")
    @ApiOperation(value = "上班打卡", notes = "增加上班打卡信息")
    public String addTable(@RequestBody Attendance attendance){
        String attendanceId = commonService.getId();
        int result = attendanceService.addStart(attendanceId,attendance.getUserId());
        if (result == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Attendance Error");
    }

    @PostMapping("/addLeave")
    @ApiOperation(value = "请假", notes = "请假")
    public String addLeave(@RequestBody Attendance attendance){
        String attendanceId = commonService.getId();
//        String UserID =attendance.getUserId();
        int result = attendanceService.addLeave(attendanceId,attendance.getUserId());
        if (result == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Leave Error");
    }

    @PutMapping("/updateFinish")
    @ApiOperation(value = "下班打卡", notes = "下班打卡")
    public String updateFinish(String attendanceId){
        int res = attendanceService.updateFinish(attendanceId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update updateFinish Error, attendanceId:" + attendanceId);
    }


    @DeleteMapping("/deleteAttendance")
    @ApiOperation(value = "根据id删除考勤信息", notes = "根据id删除考勤信息")
    @ApiImplicitParam(name = "attendanceId", value = "考勤记录ID", required = true, dataType = "String", paramType = "query")
    public String deleteAttendanceById(String attendanceId){
        int res = attendanceService.deleteAttendanceById(attendanceId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Delete Attendance Error, attendanceId:" + attendanceId);
    }
}
