package com.fzu.edu.daoyun.config.security;


import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IUserService;
import com.fzu.edu.daoyun.util.GitHubConstant;
import com.fzu.edu.daoyun.util.HttpClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private IUserService userService;
    @Value("${jwt.gitTokenHeader}")
    private String gitTokenHeader;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;
        //如果要做细的限制，仅限某域名下的可以进行跨域访问到此，可以将*改为对应的域名。
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "1728000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization" );
        String authHeader = httpServletRequest.getHeader(tokenHeader);
        if(null!=authHeader&&authHeader.startsWith(tokenHead)){
            String authToken = authHeader.substring(tokenHead.length()+1);
            String phoneNumber=jwtTokenUtils.getPhoneNumberFromToken(authToken);
            if(null!= phoneNumber&& null==SecurityContextHolder.getContext().getAuthentication()){
                User user = userService.getUserByPhoneNumber(phoneNumber);
                if(jwtTokenUtils.validateToken(authToken, user)){
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        else if(null!=authHeader){
            String userinfo_url = GitHubConstant.USER_INFO_URL;
            userinfo_url = HttpClient.doGetToken(userinfo_url,authHeader);//json
            Map<String, String> responseMap = HttpClient.getMapByJson(userinfo_url);
            User tmp = userService.getUserByPhoneNumber(responseMap.get("id"));
            if(null!=tmp)
            {
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(tmp,null,tmp.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
