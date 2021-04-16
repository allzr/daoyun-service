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
@ApiModel(value="Teachercourse对象", description="")
public class Teachercourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "teaCouID", type = IdType.AUTO)
    private Integer teaCouID;

    @TableField("couID")
    private Integer couID;

    @TableField("userID")
    private Integer userID;

    private Integer openYear;

    @TableField("lastEditTime")
    private LocalDateTime lastEditTime;

    @TableField("lastEditorID")
    private Integer lastEditorID;

    @TableField("createTime")
    private LocalDateTime createTime;


}
