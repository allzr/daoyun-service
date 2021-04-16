package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="Userright对象", description="")
public class Userright implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rightID", type = IdType.AUTO)
    private Integer rightID;

    @TableField("userID")
    private Integer userID;

    @TableField("manuManage")
    private Boolean manuManage;

    @TableField("userManage")
    private Boolean userManage;

    @TableField("courseManage")
    private Boolean courseManage;

    @TableField("dicManage")
    private Boolean dicManage;

    @TableField("systemManage")
    private Boolean systemManage;

    @TableField("lastEditorID")
    private Integer lastEditorID;

    @TableField("lastEditTime")
    private LocalDateTime lastEditTime;


}
