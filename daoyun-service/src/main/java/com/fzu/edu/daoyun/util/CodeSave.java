package com.fzu.edu.daoyun.util;

import com.fzu.edu.daoyun.entity.CodeDetail;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class CodeSave {
    private static ConcurrentHashMap<String, CodeDetail> rem = new ConcurrentHashMap<>();

    public static void saveCode(String phoneNumber,CodeDetail codeDetail){
        rem.put(phoneNumber, codeDetail);
       // deleteCodeByTime(rem);
    }

    public static boolean compCode(String phoneNumber,String code){
        CodeDetail codeDetail=rem.get(phoneNumber);
        if (codeDetail == null) return false;
        boolean res = code.equals(codeDetail.getCode()) & LocalDateTime.now().isBefore(codeDetail.getDeadtime());
        rem.remove(phoneNumber);
        return res;
    }

//    public static void deleteCodeByPhoneNumber(Map rem, String phoneNumber){
//        rem.remove(phoneNumber);
//    }
//    private static void deleteCodeByTime(Map<String,CodeDetail> rem){
//        Iterator<String> iterator=rem.keySet().iterator();
//        while(iterator.hasNext()){
//            String key=iterator.next();
//            if(LocalDateTime.now().isAfter(rem.get(key).getDeadtime()))
//                rem.remove(key);
//
//        }
//    }
}
