package org.example.servlet;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.daomain.SysLog;
import org.example.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class aclass;//访问的类
    private Method method;//访问的方法

    //前置通知 获取开始时间，访问的类，访问的方法
    @Before("execution(* org.example.servlet.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        this.visitTime=new Date();
        aclass=jp.getTarget().getClass();
        String methodName=jp.getSignature().getName();
        Object[] args=jp.getArgs();
        if (args==null||args.length==0){
            method=aclass.getMethod(methodName);//只能获取无参数的方法
        }else{
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();
            }
            method=aclass.getMethod(methodName,classArgs);//获取有参方法
        }

    }

    //后置通知 获取操作者，获取访问时长，获取访问url，获取访问IP
    @After("execution(* org.example.servlet.*.*(..))")
    public void doAfter(JoinPoint jp){
        Long time=new Date().getTime()-this.visitTime.getTime();//获取访问时长
        String ip="";//访问IP
        String url="";//访问url
        String username;//操作者
        SysLog sysLog=new SysLog();
        //获取url
        if (aclass!=null&&method!=null&& aclass!=LogAop.class){
            RequestMapping classAnnotation= (RequestMapping) aclass.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String classValue=classAnnotation.value()[0];
                url=url+classValue;
                RequestMapping methodAnnotation=method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String methodValue=methodAnnotation.value()[0];
                    url=url+methodValue;
                    ip=request.getRemoteAddr();
                    SecurityContext context= SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    username=user.getUsername();
                    sysLog.setUrl(url);
                    sysLog.setExecutionTime(time);//执行时长
                    sysLog.setUsername(username);
                    sysLog.setIp(ip);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]"+aclass.getName()+"[方法名]"+method.getName());
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
