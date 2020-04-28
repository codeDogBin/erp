package com.my.erp.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.extension.api.R;
import com.my.erp.sys.common.ActiverUser;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.Loginfo;
import com.my.erp.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginfoService loginfoService;
    //hutool验证码工具
    private LineCaptcha lineCaptcha;

    /**
     * 登录功能
     * @param loginname
     * @param pwd
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ResultObj login(String loginname, String pwd, String captcha, HttpServletRequest request){

        if(!lineCaptcha.verify(captcha)){
          return ResultObj.LOGIN_ERROR_CODE;//返回验证码错误
        }
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

    /**
     * 获取验证码的接口
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("getCaptcha")
    public void getCaptcha(HttpServletResponse response,HttpSession session) throws IOException {
       lineCaptcha = CaptchaUtil.createLineCaptcha(100, 36);
       lineCaptcha.write(response.getOutputStream());
    }



}
