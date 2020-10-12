package com.selab.boot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("execution(* com.selab.boot.controller.*.*(..))")
    public Object doAroundControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        MethodSignature proceedingSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        if(proceedingSignature.getMethod().getAnnotation(PostMapping.class) != null) {

            String userInfo = "";

            StringBuilder argsStringBuilder = new StringBuilder("\targs:\n");

            String[] paramNames = proceedingSignature.getParameterNames();

            Object[] args = proceedingJoinPoint.getArgs();

            for (int i = 0; i < args.length; i++) {

                Object arg = args[i];

                String argName = paramNames[i];

                if(arg instanceof Principal){

                    Principal principal = (Principal) arg;

                    userInfo = "USER: " + principal.getName();

                    continue;
                }

                argsStringBuilder.append("\t\t")
                        .append(argName)
                        .append(" ")
                        .append(arg != null ? arg.toString() : "null")
                        .append("\n");
            }

            // TODO DB로깅

            String logString = new StringBuilder("POST: ")
                    .append(proceedingSignature.toShortString())
                    .append("\t")
                    .append(userInfo)
                    .append("\n")
                    .append(argsStringBuilder)
                    .toString();

            log.info(logString);
        }

        Object result = proceedingJoinPoint.proceed();      // AOP 대상이된 메서드 실행

        return result;
    }
}
