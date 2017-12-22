package com.tal.peiyoupad.peiyoupad.aop;

import com.alibaba.fastjson.JSONObject;
import com.tal.peiyoupad.peiyoupad.annotation.AnnotationType;
import com.tal.peiyoupad.peiyoupad.annotation.TALMonitor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.tal.peiyoupad.peiyoupad.param.TalMonitorConfigReader;
import com.tal.peiyoupad.peiyoupad.util.HttpClientUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@ComponentScan
public class TalMonitorAOP {

    private Logger logger = LoggerFactory.getLogger(TalMonitorAOP.class);

    // 线程本地变量，为每个线程关联一个唯一的序号
    private static final ThreadLocal<Long> millsTime = new ThreadLocal<Long>();

    @Pointcut("@annotation(com.tal.peiyoupad.peiyoupad.annotation.TALMonitor)")
    public void annotationProcessor() {
    }

    /**
     * 前置通知
     */
    @Before("annotationProcessor()")
    public void doBeforeController(JoinPoint joinPoint) {
        try {
            System.out.println("doBefore");
            logger.info("doBefore");
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            System.out.println(
                    "====" + joinPoint.getTarget().getClass());// com.tal.com.spring.boot.demo.peiyoupad.controller.HelloController
            Method method = signature.getMethod();
            System.out.println("method= " + method.getName());// index
            TALMonitor action = method.getAnnotation(TALMonitor.class);
            System.out.println("action名称 " + action.type().length);// 2 不填的时候是4
            System.out.println("action名称 " + action.type()[0]);// qps
            logger.info("action名称 " + action.type().length);

            // 统一发出请求，由日志方进行处理
            // HttpClientUtil
            millsTime.set(System.currentTimeMillis());
        } catch (Exception e) {

        }
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "annotationProcessor()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        try {
            JSONObject json = TalMonitorConfigReader.readFromCongigFile();
            JSONObject serverJson = json.getJSONObject("server");
            String logServerJson = json.getString("log-server");
            String serverIp = serverJson.getString("server_ip");
            String serverName = serverJson.getString("server_name");
            System.out.println("server_ip:" + serverIp);
            System.out.println("server_name:" + serverName);
            System.out.println("log-server_is:" + logServerJson);

            Map<String, String> paramsMap = new HashMap<String, String>();

            System.out.println("doAfter");
            logger.info("doAfter");
            System.out.println("doAfter retValue is:" + retValue);// Greetings from Spring Boot!

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            System.out.println("doAfter ====" + joinPoint.getTarget()
                    .getClass());// com.tal.com.spring.boot.demo.peiyoupad.controller.HelloController
            String className = joinPoint.getTarget().getClass().getName();
            Method method = signature.getMethod();
            System.out.println("doAfter method= " + method.getName());// index
            TALMonitor action = method.getAnnotation(TALMonitor.class);
            System.out.println("doAfter action名称 " + action.type().length);// 2 不填的时候是4
            if (action.type().length > 0) {
                StringBuffer types = new StringBuffer();
                for (String type : action.type()) {
                    types.append(type);
                    types.append(",");

                    if (type.equals(AnnotationType.LATENCY)) {
                        System.out.println("doAfter action名称 " + type);// qps

                        Long startTime = millsTime.get();
                        Long endTime = System.currentTimeMillis();
                        System.out.println("endTime-startTime=" + (endTime - startTime));

                        paramsMap.put("latency", "" + (endTime - startTime));
                    }
                }

                paramsMap.put("serverIp", serverIp);
                paramsMap.put("serverName", serverName);
                paramsMap.put("className", className);
                paramsMap.put("methodName", method.getName());
                paramsMap.put("types", types.toString());
                HttpClientUtil.doPost(logServerJson, paramsMap, "UTF-8");
            }
        } catch (Exception e) {

        }
    }

    // @Around(value = "annotationProcessor_1()")
    public void count(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final String methodName = proceedingJoinPoint.getSignature().getName();

        Long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long finishTime = System.currentTimeMillis();

        Signature signature = proceedingJoinPoint.getSignature();
        String[] packageName = signature.getDeclaringTypeName().split("\\.");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < packageName.length; ++i) {
            if (i < packageName.length - 1) {
                stringBuilder.append(packageName[i].substring(0, 1));
            } else {
                stringBuilder.append(packageName[i]);
            }
            stringBuilder.append(".");
        }

        logger.info("Executing: " + stringBuilder + signature.getName() + " took: "
                + (finishTime - startTime) + " ms");

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        if (method.getDeclaringClass().isInterface()) {
            method = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(methodName,
                    method.getParameterTypes());
        }

        // 方法上的注解优先级比类上的注解高,可以覆盖类上注解的值
        TALMonitor talMonitor = null;
        if (method.isAnnotationPresent(TALMonitor.class)) {
            // 处理方法上的注解
            talMonitor = method.getAnnotation(TALMonitor.class);
            if (talMonitor.type().length > 0) {
                logArgs(proceedingJoinPoint.getArgs());
            }
        } else {
            // 处理类上面的注解
            Object target = proceedingJoinPoint.getTarget();
            if (target.getClass().isAnnotationPresent(TALMonitor.class)) {
                talMonitor = target.getClass().getAnnotation(TALMonitor.class);
                if (talMonitor.type().length > 0) {
                    logArgs(proceedingJoinPoint.getArgs());
                }
            }
        }

    }

    private void logArgs(Object[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; ++i) {
            stringBuilder.append("[");
            stringBuilder.append(i);
            stringBuilder.append("]: ");
            stringBuilder.append(args[i].toString());

            if (i < args.length - 1) {
                stringBuilder.append(", ");
            }
        }

        if (!stringBuilder.toString().isEmpty()) {
            logger.info("Argument List: " + stringBuilder);
        } else {
            logger.info("Argument List: Empty");
        }
    }
}
