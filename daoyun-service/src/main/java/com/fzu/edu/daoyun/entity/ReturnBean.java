package com.fzu.edu.daoyun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.table.TableRowSorter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBean {
    private int code;
    private String message;
    private Object obj;

    public static ReturnBean success(String message){
        return new ReturnBean(200,message,null);
    }

    public static ReturnBean success(String message,Object obj){
        return new ReturnBean(200,message, obj);
    }
    public static ReturnBean error(String message, Object obj){
        return new ReturnBean(500,message, obj);
    }
    public static ReturnBean error(String message){
        return new ReturnBean(500,message, null);
    }
}
