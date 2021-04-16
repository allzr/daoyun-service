package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="Logintime对象", description="")
public class Logintime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "loginTimeID", type = IdType.AUTO)
    private Integer loginTimeID;

    @TableField("userID")
    private Integer userID;

    @TableField("loginType")
    private Integer loginType;

    @TableField("loginTime")
    private Integer loginTime;


}
