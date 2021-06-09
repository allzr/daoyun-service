package com.fzu.edu.daoyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="Datadic对象", description="")
public class Datadic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dataDicID", type = IdType.AUTO)
    @ApiModelProperty(value = "字典ID，自动生成")
    private Integer dataDicID;

    @ApiModelProperty(value = "字典Key，必填")
    private String dataChineseKey;

    @ApiModelProperty(value = "字典Key，必填")
    private String dataEnglishKey;


    @TableField("createTime")
    @ApiModelProperty(value = "不填")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "不填")
    @TableField("lastEditorID")
    private Integer lastEditorID;

    @ApiModelProperty(value = "不填")
    @TableField("lastEditTime")
    private LocalDateTime lastEditTime;

    @ApiModelProperty(value = "不填")
    private Boolean isDelete;

}
