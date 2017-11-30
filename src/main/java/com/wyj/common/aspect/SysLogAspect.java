package com.wyj.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wyj.common.annotation.SysLog;
import com.wyj.entity.data.SysLogEntity;
import com.wyj.service.data.SysLogService;


/**
 * 日志切面
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    
    @Pointcut("@annotation(com.wyj.common.annotation.SysLog)")
    public void pointCut(){}
    
    @Around("pointCut()")
    public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable{
        
        // 开始时间
        long beginTime = System.currentTimeMillis();
        
        //执行目标方法
        Object result = joinPoint.proceed();
        
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        
        //保存日志
        saveSysLog(joinPoint, time);
        return result;
    }
    
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLogEntity = new SysLogEntity();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if(sysLog != null){
            //注解上的描述
            sysLogEntity.setOperation(sysLog.action());
        }
        //获取目标类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = signature.getName();
        sysLogEntity.setMethod(className+"."+methodName+"()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length !=0 && args[0] != null){
            sysLogEntity.setParams(args[0].toString());
        }
        sysLogEntity.setTime(time);
//        //保存系统日志
        sysLogService.save(sysLogEntity);
    }
}
