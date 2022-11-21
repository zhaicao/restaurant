package com.scuec.restaurant.entities;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Message {
    private String messageId;

    private String messageUserid;

    private String messageOrderid;

    private String messageContent;

    private int messageType;

//    @ApiModelProperty(value = "创建时间")
//    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    private int messageComplete;

    private int messageDel;

}
