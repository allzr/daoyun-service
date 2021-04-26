package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userID", type = IdType.AUTO)
    private Integer userID;

    @TableField("sex")
    @ApiModelProperty("性别，1表示男，2表示女")
    private int sex;

    @TableField("userType")
    @ApiModelProperty("标志用户类型，1表示学生，2表示老师，3表示管理员，必填")
    private Integer userType;

    @TableField("stu_tea_adm_Number")
    @ApiModelProperty("学生学号或老师工号或管理员工号")
    private String stuTeaAdmNumber;

    @ApiModelProperty("真实姓名，必填")
    private String realName;

    @TableField("bornYear")
    private Integer bornYear;

    @TableField("bornMonth")
    private Integer bornMonth;

    @ApiModelProperty("手机号码，必填")
    @TableField("phoneNumber")
    private String phoneNumber;

    @ApiModelProperty("用户名，必填")
    private String username;

    @ApiModelProperty("密码，必填")
    private String password;

    @ApiModelProperty("经验值")
    @TableField("EXP")
    private Integer exp;

    @ApiModelProperty("githubToken过期时间")
    @TableField("githubTokenDeadtime")
    private LocalDateTime githubTokenDeadtime;

    @ApiModelProperty("邮箱，必填")
    @TableField("email")
    private String email;

    @ApiModelProperty("githubToken")
    @TableField("githubToken")
    private String githubToken;

    @TableField("githubID")
    private String githubID;

    @TableField("schoolName")
    private String schoolName;

    @TableField("collegeName")
    private String collegeName;

    @TableField("headProtrait")
    private String headProtrait;

    @ApiModelProperty("不填")
    @TableField("lastLoginTime")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("不填")
    @TableField("lastLoginIP")
    private String lastLoginIP;

    @ApiModelProperty("不填")
    private Boolean isDelete;

    @ApiModelProperty("不填")
    @TableField("createTime")
    private LocalDateTime createTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDelete;
    }

}
