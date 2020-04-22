package com.my.erp.sys.cache;

import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.vo.DeptVo;
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

    //声明一个缓存容器
    private Map<String,Object> CACHE_CONTAINER = new HashMap<>();

    //声明切面表达式
    private static final String POINTCUT_DEPT_UPDATE="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.getOne(..))";
    private static final String POINTCUT_DEPT_REMOVE="execution (* com.my.erp.sys.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX="dept";
    /**
     * 查询切入
     */
      @Around(value=POINTCUT_DEPT_GET)
      public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
          //取出第一个参数
          Integer object =(Integer) joinPoint.getArgs()[0];
          //查询缓存中是否有数据
          Dept result =(Dept) CACHE_CONTAINER.get(object);
          if(result!=null){
              return result;
          }else{
              //将数据存入缓存 并且返回
              Dept res2 = (Dept) joinPoint.proceed();
              CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+res2.getId(), res2);
              return res2;
          }
      }

    /**
     * 更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        DeptVo deptVo = (DeptVo) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Dept dept = (Dept) CACHE_CONTAINER.get(deptVo.getId());
            if (null == dept) {
                dept = new Dept();
                BeanUtils.copyProperties(deptVo, dept);
                CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);
            }
        }
        return isSuccess;
    }

    /**
     * 删除的切入
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
            }
        return isSuccess;
    }




}
