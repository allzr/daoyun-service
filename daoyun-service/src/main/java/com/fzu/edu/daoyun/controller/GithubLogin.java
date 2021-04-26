package com.fzu.edu.daoyun.controller;

import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IUserService;
import com.fzu.edu.daoyun.util.GitHubConstant;
import com.fzu.edu.daoyun.util.HttpClient;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping
public class GithubLogin {

    @Autowired
    private IUserService userService;

    @PostMapping("/githubLogin")
    @ApiOperation(value = "github登录")
    public ReturnBean githubLogin(@RequestBody String code) throws Exception {
        if(!StringUtils.isEmpty(code)){
            //拿到我们的code,去请求token
            //发送一个请求到
            String token_url = GitHubConstant.TOKEN_URL.replace("CODE", code);
            System.out.println(token_url);
            //得到的responseStr是一个字符串需要将它解析放到map中
            String responseStr = HttpClient.doGet(token_url);
            // 调用方法从map中获得返回的--》 令牌
            String token = HttpClient.getMap(responseStr).get("access_token");
            System.out.println(token);
            //根据token发送请求获取登录人的信息  ，通过令牌去获得用户信息
            String userinfo_url = GitHubConstant.USER_INFO_URL.replace("TOKEN", token);
            userinfo_url = HttpClient.doGet(userinfo_url);//json
            System.out.println(userinfo_url);
            Map<String, String> responseMap = HttpClient.getMapByJson(responseStr);
            System.out.println(userinfo_url);
            User user=new User();
            //user.setGithubID(responseMap()
            user.setGithubToken(token);
            user.setGithubTokenDeadtime(LocalDateTime.now());
            userService.insert(user);
            return ReturnBean.success("登录成功", token);

        }
        return ReturnBean.error("登录失败");
    }
}
