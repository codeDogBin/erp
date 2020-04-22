package com.my.erp.sys.controller;

import com.my.erp.sys.common.ActiverUser;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.Loginfo;
import com.my.erp.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping("/login")
    public ResultObj login(String loginname, String pwd, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);//将永明和密码设置为令牌
        try {
            subject.login(token);//使用shiro执行登录
            ActiverUser activerUser =( ActiverUser) subject.getPrincipal();//获取登录凭证
            HttpSession session = request.getSession();//获取session
            session.setAttribute("user",activerUser.getUser());//将对象存储到session
            //创建一个登录日志对象
            Loginfo entity = new Loginfo();
            entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
            entity.setLoginip(request.getRemoteAddr());
            entity.setLogintime(new Date());
            ///存入数据库
            loginfoService.save(entity);
            return  ResultObj.LOGIN_SUCCESS;//返回登录成功
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;//返回登陆失败
        }
    }
}
