package com.example.guanli.aop;

import com.alibaba.fastjson2.JSON;
import com.example.guanli.mapper.OperateLogMapper;
import com.example.guanli.pojo.OperateLog;
import com.example.guanli.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    //切入点：拦截 com.example.guanli.controller包下，加了@Log注解的方法
    @Pointcut(" @annotation(com.example.guanli.anno.Log)")
    public void logPointCut() {
    }

    //环绕通知：可以拿到入参、返回值、计算耗时
    @Around("logPointCut()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.记录开始时间
        long start = System.currentTimeMillis();

        // 2.执行目标方法
        Object result = joinPoint.proceed();

        // 3.计算耗时
        long costTime = System.currentTimeMillis() - start;

        // 4.封装日志对象
        OperateLog operateLog = new OperateLog();
        //操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        //目标类全类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        //目标方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());
        //方法参数：把参数数组转为JSON字符串
        Object[] args = joinPoint.getArgs();
        String paramsJson = JSON.toJSONString(args);
        //限制长度，防止数据库超长
        if(paramsJson.length() > 2000){
            paramsJson = paramsJson.substring(0,2000);
        }
        operateLog.setMethodParams(paramsJson);

        //返回值转JSON
        String returnJson = JSON.toJSONString(result);
        if(returnJson.length() > 2000){
            returnJson = returnJson.substring(0,2000);
        }
        operateLog.setReturnValue(returnJson);

        operateLog.setCostTime(costTime);

        // ========= 获取操作人ID =========
        // 这里根据你的项目实际情况修改！
        // 示例：从session / token中获取登录用户id，这里写一个示意
        // Integer empId = (Integer) request.getSession().getAttribute("empId");
        Integer empId = getCurrentOperateEmpId();
        operateLog.setOperateEmpId(empId);

        // 5.保存日志到数据库
        log.info("保存操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        // 返回目标方法执行结果
        return result;
    }

    /**
     * 获取当前登录操作人ID，根据你的认证方案自行修改
     * 如JWT：解析token获取empId
     */
    private Integer getCurrentOperateEmpId(){
        //示例代码，替换成你项目的逻辑

       String token = request.getHeader("token");
       if (token == null || token.isEmpty()){
           return null;
       }
        try {
            Claims claims = JwtUtils.parseJwt(token);
            Object id = claims.get("id");
            return id == null ? null : Integer.valueOf(id.toString());
        } catch (Exception e) {
            log.warn("解析token获取操作人ID失败：{}", e.getMessage());
            return null;
        }
        //return 1;
    }
}
