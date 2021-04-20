package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.config.security.JwtTokenUtils;
import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.mapper.UserMapper;
import com.fzu.edu.daoyun.service.IUserService;
import com.fzu.edu.daoyun.util.CodeSave;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import net.bytebuddy.asm.Advice;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl<UserService> extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private LogintimeServiceImpl logintimeService;


    @Override
    public ReturnBean loginByPassword(UserLogin userLogin) {
        String phoneNumber=userLogin.getPhoneNumber();
        String password=userLogin.getPassword();
        System.out.println(password);
        if(null==phoneNumber) return ReturnBean.error("手机号不能为空");
        User user=getUserByPhoneNumber(phoneNumber);
        System.out.println(user);
        if(null==user)
            user=getUserByEmail(phoneNumber);
        if(null==user)
            return ReturnBean.error("用户不存在");
        if(null==user||!password.equals(user.getPassword()))
            return ReturnBean.error("用户名或密码错误");

        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token=jwtTokenUtils.generateToken(user);
        Map<String ,String> tokenMap = new HashMap<>();

        tokenMap.put("token",tokenHead+" "+token);
        //tokenMap.put("tokenHead",tokenHead);
        user=getUserByPhoneNumber(phoneNumber);
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
        logintimeService.insertLoginTime(user,LocalDateTime.now(),1);
        return ReturnBean.success("登陆成功",tokenMap);
    }

    @Override
    public ReturnBean loginByCode(UserLogin userLogin) {
        String phoneNumber=userLogin.getPhoneNumber();
        String password=userLogin.getPassword();
        System.out.println(userLogin.getPhoneNumber());
        System.out.println(userLogin.getPassword());
        if(null==phoneNumber) return ReturnBean.error("手机号不能为空");
        if(6!=password.length()) return ReturnBean.error("验证码不正确");
        if(!CodeSave.compCode(phoneNumber,password)) {
            return ReturnBean.error("验证码不正确");
        }
        User user=getUserByPhoneNumber(phoneNumber);
        if(null==user)
            user=getUserByEmail(phoneNumber);
        if(null==user)
            return ReturnBean.error("用户不存在");

        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token=jwtTokenUtils.generateToken(user);
        Map<String ,String> tokenMap = new HashMap<>();

        tokenMap.put("token",tokenHead+" "+token);
        //tokenMap.put("tokenHead",tokenHead);
        user=getUserByPhoneNumber(phoneNumber);
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
        logintimeService.insertLoginTime(user,LocalDateTime.now(),1);
        return ReturnBean.success("登陆成功",tokenMap);
    }


    @Override
    public ReturnBean register(UserRegiser userRegiser) {
        User user = new User();
        user.setPhoneNumber(userRegiser.getPhoneNumber());
        user.setUserType(userRegiser.getUserType());
        user.setUsername(userRegiser.getUsername());
        user.setPassword(userRegiser.getPassword());
        if(null==userRegiser.getPhoneNumber())
            return ReturnBean.error("请输入手机号");
        if(null==userRegiser.getPassword())
            return ReturnBean.error("请输入密码");
        if(!CodeSave.compCode(userRegiser.getPhoneNumber(),userRegiser.getCode())) return ReturnBean.error("验证码不正确");
        if(null!=getUserByUsername(userRegiser.getUsername()))
            return ReturnBean.error("用户名已存在");
        if(null!=getUserByPhoneNumber(userRegiser.getPhoneNumber()))
            return ReturnBean.error("手机号码已存在");
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        return ReturnBean.success("注册成功");
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username).eq("isDelete",0));
    }

    @Override
    public User getUserByID(int userID) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("userID",userID).eq("isDelete",0));
    }

    @Override
    public ReturnBean getCodeAndReturn(String phoneNumber) {
        String code = new String();
        Random random = new Random();
        while (code.length() < 6) {
            code+=(random.nextInt(10));
        }
        System.out.println(code);
        return sendCodeAndReturn(phoneNumber,code);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber){
        return userMapper.selectOne(new QueryWrapper<User>().eq("phoneNumber",phoneNumber).eq("isDelete",0));
    }


    @Override
    public ReturnBean getCode(String phoneNumber) {
        if(11!=phoneNumber.length())
            return ReturnBean.error("手机号码不正确");
        String code = new String();
        Random random = new Random();
        while (code.length() < 6) {
            code+=(random.nextInt(10));
        }
        System.out.println(code);
        return sendCode(phoneNumber,code);
    }

    @Override
    public User getUserByEmail(String Email) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("email",Email).eq("isDelete",false));
    }

    @Override
    public ReturnBean deleteUser(String phoneNumber) {
        User user=getUserByPhoneNumber(phoneNumber);
        if(null==user)
            return ReturnBean.error("查无此人");
        user.setIsDelete(true);
        userMapper.updateById(user);
        return ReturnBean.success("删除成功");
    }

    @Override
    public ReturnBean sendCode(String phoneNumber,String code) {
        try{
            String secretID="AKID1rVxALll4RaZKrxPJxd6ye4QyHLAJ8MV";
            String secretKey="Gn7D5lJceXBdpkgaFgZ01sc6NUNs8F9p";
            Credential cred = new Credential(secretID, secretKey);
            SmsClient client = new SmsClient(cred, "ap-guangzhou");
            SendSmsRequest req = new SendSmsRequest();
            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
            String appid = "1400455705";
            req.setSmsSdkAppid(appid);
            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
            String sign = "BabkaCabin";
            req.setSign(sign);
            String templateID = "789504";
            req.setTemplateID(templateID);
            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
            String[] phoneNumbers = {"+86"+phoneNumber};
            req.setPhoneNumberSet(phoneNumbers);
            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {code};
            req.setTemplateParamSet(templateParams);
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);
            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

            CodeDetail codeDetail=new CodeDetail(code,LocalDateTime.now().plusMinutes(5));

            CodeSave.saveCode(phoneNumber,codeDetail);

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return ReturnBean.error(e.getMessage());
        }
        return  ReturnBean.success("发送成功");
    }

    @Override
    public ReturnBean sendCodeAndReturn(String phoneNumber,String code) {
        try{
            String secretID="AKID1rVxALll4RaZKrxPJxd6ye4QyHLAJ8MV";
            String secretKey="Gn7D5lJceXBdpkgaFgZ01sc6NUNs8F9p";
            Credential cred = new Credential(secretID, secretKey);
            SmsClient client = new SmsClient(cred, "ap-guangzhou");
            SendSmsRequest req = new SendSmsRequest();
            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
            String appid = "1400455705";
            req.setSmsSdkAppid(appid);
            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
            String sign = "BabkaCabin";
            req.setSign(sign);
            String templateID = "789504";
            req.setTemplateID(templateID);
            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
            String[] phoneNumbers = {"+86"+phoneNumber};
            req.setPhoneNumberSet(phoneNumbers);
            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {code};
            req.setTemplateParamSet(templateParams);
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);
            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

            //CodeDetail codeDetail=new CodeDetail(code,LocalDateTime.now().plusMinutes(5));

            //CodeSave.saveCode(phoneNumber,codeDetail);

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return ReturnBean.error(e.getMessage());
        }
        return  ReturnBean.success("发送成功",code);
    }

    @Override
    public ReturnBean updateUserInfo(User user) {
        if(null!=user.getUserID())
            userMapper.updateById(user);
        user=getUserByPhoneNumber(user.getPhoneNumber());
        userMapper.updateById(user);
        return ReturnBean.success("更新成功");
    }

    @Override
    public ReturnBean updatePassword(UserLogin userLogin) {
        if(null==userLogin.getPassword()||null==getUserByPhoneNumber(userLogin.getPhoneNumber()))
            return ReturnBean.error("手机号不存在");
        User user=getUserByPhoneNumber(userLogin.getPhoneNumber());
        user.setPassword(userLogin.getPassword());
        userMapper.updateById(user);
        return ReturnBean.success("密码修改成功");
    }
}
