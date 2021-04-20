package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sign对象", description="")
public class Sign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "signID", type = IdType.AUTO)
    private Integer signID;

    @TableField("teaCouID")
    @ApiModelProperty("记录学生签到信息是保存stuCouID，记录老师发起签到信息时保存teaCouID")
    private Integer teaCouID;

    @TableField("stuTeaCouID")
    private Integer stuTeaCouID;

    @TableField("createTime")
    @ApiModelProperty("记录老师发起签到信息时填写终止时间，前端正常传入createTime")
    private LocalDateTime createTime;

    @ApiModelProperty("记录老师发起签到信息时填写开始时间，前端正常传入signTime信息")
    @TableField("signTime")
    private LocalDateTime signTime;

    @TableField("signLocationX")
    private String signLocationX;

    @TableField("signLocationY")
    private String signLocationY;

}
