package com.my.erp.sys.cache;

import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.User;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志处理类
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    //声明一个缓存容器
    private Map<String,Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;

    //声明切面表达式
    private static final String POINTCUT_DEPT_UPDATE="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_REMOVE="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.removeById(..))";
    private static final String POINTCUT_DEPT_ADD="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.save(..))";

    private static final String CACHE_DEPT_PROFIX="dept:";

    /**
     * 部门查询切入
     */
      @Around(value=POINTCUT_DEPT_GET)
      public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
          //取出第一个参数
          Integer id =(Integer) joinPoint.getArgs()[0];
          //查询缓存中是否有数据
          Dept result =(Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX+id);
          log.info("已从缓存中找到部门对象"+CACHE_DEPT_PROFIX+id);
          if(result!=null){
              return result;
          }else{
              //将数据存入缓存 并且返回
              log.info("未从缓存中找到部门对象,去数据库查询并放入缓存");
              Dept res2 = (Dept) joinPoint.proceed();
              CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+res2.getId(), res2);
              return res2;
          }
      }

    /**
     * 部门更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept dept = (Dept) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Dept newdept = (Dept) CACHE_CONTAINER.get(dept.getId());
            if (null == newdept) {
                newdept = new Dept();
                BeanUtils.copyProperties(dept, newdept);
                log.info("部门对象缓存已更新"+CACHE_DEPT_PROFIX + newdept.getId());
                CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + newdept.getId(), newdept);
            }
        }
        return isSuccess;
    }

    /**
     * 部门添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_DEPT_ADD)
    public Object cacheDeptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept dept = (Dept) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);
            log.info("部门对象缓存已增加"+CACHE_DEPT_PROFIX + dept.getId());
            }
        return isSuccess;
    }


    /**
     * 部门删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_DEPT_REMOVE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX+id);
            log.info("已从缓存中找到部门对象"+CACHE_DEPT_PROFIX+id);
            }
        return isSuccess;
    }







    //声明切面表达式
    private static final String POINTCUT_USER_UPDATE="execution (* com.my.erp.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET="execution (* com.my.erp.sys.service.impl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_REMOVE="execution (* com.my.erp.sys.service.impl.UserServiceImpl.removeById(..))";
    private static final String POINTCUT_USER_ADD="execution (* com.my.erp.sys.service.impl.UserServiceImpl.save(..))";

    private static final String CACHE_USER_PROFIX="user:";

    /**
     * 用户查询切入
     */
    @Around(value=POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        User result =(User) CACHE_CONTAINER.get(CACHE_USER_PROFIX+id);
        log.info("已从缓存中找到用户对象"+CACHE_USER_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到用户对象,去数据库查询并放入缓存");
            User res2 = (User) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 用户更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User user = (User) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            User newuser = (User) CACHE_CONTAINER.get(user.getId());
            if (null == newuser) {
                newuser = new User();
                BeanUtils.copyProperties(user, newuser);
                log.info("用户对象缓存已更新"+CACHE_USER_PROFIX + newuser.getId());
                CACHE_CONTAINER.put(CACHE_USER_PROFIX + newuser.getId(), newuser);
            }
        }
        return isSuccess;
    }

    /**
     * 用户添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User user = (User) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + user.getId(), user);
            log.info("用户对象缓存已增加"+CACHE_USER_PROFIX + user.getId());
        }
        return isSuccess;
    }


    /**
     * 用户删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_USER_REMOVE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX+id);
            log.info("已从缓存中找到用户对象"+CACHE_USER_PROFIX+id);
        }
        return isSuccess;
    }

}
