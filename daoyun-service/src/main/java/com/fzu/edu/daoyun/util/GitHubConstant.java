package com.fzu.edu.daoyun.util;

public class GitHubConstant {
    // 这里填写在GitHub上注册应用时候获得 CLIENT ID
    public static final String  CLIENT_ID="601c024ea0657ca6cb13";
    //这里填写在GitHub上注册应用时候获得 CLIENT_SECRET
    public static final String CLIENT_SECRET="11802cb65a16cbd119c238666f44ba5d0429a5dc";
    // 回调路径
    public static final String CALLBACK = "http://localhost:8081/githubLogin";
    //获取code的url
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&state=STATE&redirect_uri="+CALLBACK+"";
    //获取token的url
    public static  String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code=";
    //获取用户信息的url
    public static final String USER_INFO_URL = "https://api.github.com/user";

}
