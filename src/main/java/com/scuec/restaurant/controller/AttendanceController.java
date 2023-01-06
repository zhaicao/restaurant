package com.scuec.restaurant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Attendance;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.AttendanceService;
import com.scuec.restaurant.service.CommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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


    /**
     * 获取用户list
     * @param currentPage
     * @param pageSize
     * @param userId 用户UUID，精确
     * @param loginName 用户登录名，精确
     * @param realName 用户真实姓名，模糊查询
     * @param attendanceType 考勤类型
     * @return
     */
    @GetMapping("/getAttendanceList")
    @ApiOperation(value = "多条件获取用户考勤List", notes = "多条件查询用户考勤list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID，精确查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "loginName", value = "用户登录名，精确查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名，模糊查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "attendanceType", value = "考勤类型", required = true, dataType = "int", paramType = "query")
    })
    public IPage<Attendance> getAttendanceList(int currentPage,
                                   int pageSize,
                                   String userId,
                                   String loginName,
                                   String realName,
                                   int attendanceType){

        return attendanceService.getAttendanceList(currentPage, pageSize, userId,loginName,realName,  attendanceType);
    }



    @PostMapping("/addAttendance")
    @ApiOperation(value = "上班打卡", notes = "增加上班打卡信息")
    public String addTable(@RequestBody Attendance attendance){
        String attendanceId = commonService.getId();
        int result = attendanceService.addStart(attendanceId,attendance.getUserId());
        if (result == 1)
            return "successful";
        else if (result == -1)
            throw new GlobalException(ResponseCode.ERROR, "今天已有打卡信息" );
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Attendance Error");
    }

    @PostMapping("/addLeave")
    @ApiOperation(value = "请假", notes = "请假")
    public String addLeave(@RequestBody Attendance attendance){
        String attendanceId = commonService.getId();
        int result = attendanceService.addLeave(attendanceId,attendance.getUserId());
        if (result == 1)
            return "successful";
        else if (result == -1)
            throw new GlobalException(ResponseCode.ERROR, "今天已有上班打卡信息" );
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Leave Error");
    }

    @PutMapping("/updateFinish")
    @ApiOperation(value = "下班打卡", notes = "下班打卡")
    public String updateFinish(String userId){
        int res = attendanceService.updateFinish(userId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "请先打卡上班");
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

    @GetMapping("/getUserById")
    @ApiOperation(value = "通过Id获取用户考勤信息", notes = "通过Id获取用户考勤信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    public Attendance getAttendanceById(String userId){
        Attendance attendance = attendanceService.getAttendanceById(userId);
        return attendance;
    }
}
