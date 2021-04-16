package com.fzu.edu.daoyun.util;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Sign;
import com.fzu.edu.daoyun.entity.Teachercourse;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

class SignDetial{
    String teacherId;
    String teacherLocaltion;
    String deadtime;
}

public class SignSave {
    public static Map<LocalTime, SignDetial> signMap=new HashMap<>();
    public ReturnBean beginSign(Teachercourse teachercourse){
        return ReturnBean.success("开始签到");
    }
}
