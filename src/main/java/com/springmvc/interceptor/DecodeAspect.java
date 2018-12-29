package com.springmvc.interceptor;

import com.springmvc.util.DES3;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * 用aop的思想，在进入方法前进行对加密参数体解密，减少重复代码
 */
@Aspect
@Order(100)
@Component("decodeAspect")
public class DecodeAspect {
    private static final Logger logger = Logger.getLogger(DecodeAspect.class);

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     *
     */
    @Around(value = "execution(* com.springmvc.controller.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint jp) {

        String methodName = jp.getSignature().getName();
        Object result;
        try {
            logger.info("【Aspect前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
            Object[] obj = jp.getArgs();//获取接口所有参数
            String param = (String) obj[0];
            //此处可以对参数进行统一处理,解密字符串
            String decodeStr = DES3.decode(param);
            obj[0]=decodeStr;
            //执行目标方法
            result = jp.proceed(obj);
            logger.info("【Aspect返回通知】：the method 【" + methodName + "】 ends with " + result);
        } catch (Throwable e) {
            logger.error("【Aspect异常通知】：the method 【" + methodName + "】 occurs exception " + e.getMessage());
            result="出现异常:"+e.getMessage();
            return result;
        }
        logger.info("【Aspect后置通知】：-----------------end.----------------------");
        return result;
    }
}
