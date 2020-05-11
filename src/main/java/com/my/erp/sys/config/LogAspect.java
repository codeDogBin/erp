package com.my.erp.sys.config;

import com.google.gson.Gson;
import com.my.erp.sys.common.MyFileUtils;
import com.my.erp.sys.domain.Operatelog;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.OperatelogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspect {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(LogAspect.class);

    private String requestPath = null ; // 请求地址
    private Integer userid = null ; // 用户名
    private String username = null ; // 用户名
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String remoteAddr;  //请求IP
    private String description; //方法描述
    // 需要用到google的gson解析包
    private Gson gson =new Gson();
    @Autowired
    private OperatelogService operatelogService;
    /**
     * Controller层切点 注解拦截
     */
    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.my.erp.sys.config.Log)")
    public void controllerAspect() {}

    /**
     *
     * @Title：doBeforeInServiceLayer
     * @Description: 方法调用前触发
     * @param joinPoint
     */
    @Before("execution(* com.my.erp.*.controller.*Controller.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    /**
     *
     * @Title：doAfterInServiceLayer
     * @Description: 方法调用后触发
     *  记录结束时间
     * @author shaojian.yu
     * @date 2014年11月2日 下午4:46:21
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void doAfterInServiceLayer(JoinPoint joinPoint) throws IOException {
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        if(StringUtils.isBlank(description)){
            description = "-";
        }
        //构造一个新的opeartelog对象
        Operatelog operatelog = new Operatelog();
        //存入数据库
        operatelog.setUserid(userid);
        operatelog.setUsername(username);
        operatelog.setCtime(new Date());
        operatelog.setParam(gson.toJson(inputParamMap));
        operatelog.setResult(gson.toJson(outputParamMap));
        operatelog.setRequestaddr(remoteAddr);
        operatelog.setRequestpath(requestPath);
        operatelog.setDescription(description);
        operatelogService.save(operatelog);
        //存入日志
        this.printOptLog();
    }

    /**
     *
     * @param point
     */
    @Around("execution(* com.my.erp.*.controller.*Controller.*(..))")
    private Object doAround(ProceedingJoinPoint point) throws Throwable {
        //获取Request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        //获取用户id
        User user = ((User) request.getSession().getAttribute("user"));
        userid = user.getId();
        username = user.getName();
        remoteAddr = request.getRemoteAddr();//请求的IP
        if(StringUtils.isBlank(remoteAddr)){
            remoteAddr = "-";
        }
        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
         requestPath = request.getRequestURI();
        if(StringUtils.isBlank(requestPath)){
            requestPath = "-";
        }
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        //获取注解上的描述
        Log userAction = method.getAnnotation(Log.class);
        if (userAction != null) {
            // 注解上的描述
            description = userAction.value();
        }

        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执
        outputParamMap = new HashMap<>();
        Object result = null;// result的值就是被拦截方法的返回值
        result = point.proceed();
        outputParamMap.put("result", result);
        return result;
    }

    /**
     *
     * @Title：printOptLog
     * @Description: 输出日志
     * @author shaojian.yu
     * @date 2014年11月2日 下午4:47:09
     */
    private void printOptLog() {

        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        String data1 = "用户编号：" + userid + "\r\n"
                + "请求地址:" + requestPath + "\r\n"
                + "请求IP:" + remoteAddr + "\r\n"
                + "方法描述:" + description + "\r\n"
                + "开始时间:" + optTime + "\r\n"
                + "调用方法时间:" + (endTimeMillis - startTimeMillis) + "ms;" + "\r\n"
                + "参数:" + gson.toJson(inputParamMap) + "\r\n"
                + "返回参数:" + gson.toJson(outputParamMap) + "\r\n";
            logger.info(data1);
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String logName = sdf.format(now);
            File mir = new File(MyFileUtils.LOG_PATH);
            if(!mir.exists()) mir.mkdirs();
            File logFile = new File(MyFileUtils.LOG_PATH, logName + ".txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            //数据分析用 用户编号 IP 方法 时间
            String data = "用户编号:"+userid+"用户名称"+username+ " 请求ip:" + remoteAddr + "详情:" + description + " 操作时间:" + optTime + "\t" + "\r\n";
            FileOutputStream out = new FileOutputStream(logFile, true);
            out.write(data.getBytes());
            out.close();
        } catch (Exception e) {
        }
    }

}
