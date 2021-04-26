package com.fzu.edu.daoyun.controller;

import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.util.GitHubConstant;
import com.fzu.edu.daoyun.util.HttpClient;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GithubLogin {
    @PostMapping("/githubLogin")
    @ApiOperation(value = "github登录")
    public ReturnBean githubLogin(String code, String state, Model model, HttpServletRequest request) throws Exception {
        if(!StringUtils.isEmpty(code)&&!StringUtils.isEmpty(state)){
            //拿到我们的code,去请求token
            //发送一个请求到
            String token_url = GitHubConstant.TOKEN_URL.replace("CODE", code);
            //得到的responseStr是一个字符串需要将它解析放到map中
            String responseStr = HttpClient.doGet(token_url);
            // 调用方法从map中获得返回的--》 令牌
            String token = HttpClient.getMap(responseStr).get("access_token");

            //根据token发送请求获取登录人的信息  ，通过令牌去获得用户信息
            String userinfo_url = GitHubConstant.USER_INFO_URL.replace("TOKEN", token);
            responseStr = HttpClient.doGet(userinfo_url);//json

            Map<String, String> responseMap = HttpClient.getMapByJson(responseStr);

        }
        return ReturnBean.success("成功");
    }
}
