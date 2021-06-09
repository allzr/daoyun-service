package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value = "Course对象")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "couID", type = IdType.AUTO)
    @ApiModelProperty(value="课程ID,自动生成")
    private Integer couID;

    @ApiModelProperty("课程名，必填")
    private String name;

    @TableField("lastEditorID")
    private Integer lastEditorID;

    @TableField("lastEditTime")
    private LocalDateTime lastEditTime;

    private Boolean isDelete;



}
