package com.fzu.edu.daoyun.util;

import com.fzu.edu.daoyun.entity.CodeDetail;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class CodeSave {
    private static ConcurrentHashMap<String, CodeDetail> rem = new ConcurrentHashMap<>();

    public static void saveCode(String phoneNumber,CodeDetail codeDetail){
        if(null!=rem.get(phoneNumber))
        {
            rem.remove(phoneNumber);
            CodeDetail value=rem.get(phoneNumber);
            value.setCode(codeDetail.getCode());
            value.setDeadtime(codeDetail.getDeadtime());
            rem.put(phoneNumber,value);
        }
        rem.put(phoneNumber, codeDetail);
       // deleteCodeByTime(rem);
    }

    public static boolean compCode(String phoneNumber,String code){
        deleteCodeByTime();
        CodeDetail codeDetail=rem.get(phoneNumber);
        if (codeDetail == null) return false;
        if(code.equals(codeDetail.getCode()) & LocalDateTime.now().isBefore(codeDetail.getDeadtime())) {
            rem.remove(phoneNumber);
            return true;
        }
        return false;
    }

//    public static void deleteCodeByPhoneNumber(Map rem, String phoneNumber){
//        rem.remove(phoneNumber);
//    }
    private static void deleteCodeByTime(){
        Iterator<String> iterator=rem.keySet().iterator();
        while(iterator.hasNext()){
            String key=iterator.next();
            if(LocalDateTime.now().isAfter(rem.get(key).getDeadtime()))
                rem.remove(key);

        }
    }
}
