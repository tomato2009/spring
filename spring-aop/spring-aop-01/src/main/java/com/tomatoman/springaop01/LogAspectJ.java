package com.tomatoman.springaop01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 切面
 */
@Aspect
@Component
public class LogAspectJ {

    /**
     * 引入，在不修改目标方法的基础上增加功能
     */
    @DeclareParents(value = "com.tomatoman.springaop01.CalculatorImpl", defaultImpl = com.tomatoman.springaop01.VerifyImpl.class)
    public Verify verify;

    @Pointcut(value = ("execution(* com.tomatoman.springaop01.CalculatorImpl.*(..))"))
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeMethod() {
        System.out.println("Before...");
    }

    @After("pointCut()")
    public void afterMethod() {
        System.out.println("After...");
    }

    @AfterReturning("pointCut()")
    public void afterReturningMethod() {
        System.out.println("AfterReturning...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowingMethod() {
        System.out.println("AfterThrowing...");
    }

    @Around ("pointCut()")
    public int around(ProceedingJoinPoint jp ) {
        System.out.println( "around before.. ..");
        int result = 0;
        try {
            result = (int)jp .proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("around after ....");
        return result;
    }

    /**
     * 传递参数
     * @param a
     * @param b
     */
    @Before("execution(* com.tomatoman.springaop01.CalculatorImpl.*(..))" +
            "&& args(a, b)")
    public void beforeMethod2(int a, int b) {
        System.out.println("Before...a b " + a +"  "+ b);
    }

//    @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(..))")
//    public void beforeMethod1() {
//        System.out.println("before...return = public int");
//    }
//
//    /**
//     * 返回类型
//     */
//    @Before(value = "execution(public String com.tomatoman.springaop01.CalculatorImpl.*(..))")
//    public void beforeMethod2() {
//        System.out.println("before...return = public String");
//    }
//
//    /**
//     * 参数
//     */
//    @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(int, int))")
//    public void beforeMethod3() {
//        System.out.println("before...args = (int, int)");
//    }
//
//    /**
//     * 参数
//     */
//    @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(String, String))")
//    public void beforeMethod4() {
//        System.out.println("before...args = (String, String)");
//    }
//
//    /**
//     * 参数
//     */
//    @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(Object, Object))")
//    public void beforeMethod5() {
//        System.out.println("before...args = (Object, Object)");
//    }



//    //前置通知：目标方法也即连接点执行之前
//    @Before(value = "execution(public int com.tomatoman.springaop01.CalculatorImpl.*(int,int))")
//    public void beforeMethod(JoinPoint joinPoint) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//        System.out.println("aop Before: method is " + method + " args is " + list );
//    }
//
//    //后置通知：目标方法也即连接点执行之后，无论目标方法是否发生异常
//    //在后置通知中不能方位连接点的执行结果
//    @After(value = "execution(public int com.tomatoman.springaop01.Calculator.*(int, int))")
//    public void afterMethod(JoinPoint joinPoint) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//        System.out.println("aop After: method is " + method +" args is " + list);
//    }
//
//    //后置通知：目标方也即连接点法执行之后，发生异常不执行
//    @AfterReturning(value = "execution(public int com.tomatoman.springaop01.Calculator.*(int, int))", returning="result")
//    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//        System.out.println("aop AfterReturnning: method is " + method +" args is " + list);
//        System.out.println("aop AfterReturnning: result is " + result);
//    }
//
//    //异常通知：目标方也即连接点法执行出现异常后执行
//    @AfterThrowing(value = "execution(public int com.tomatoman.springaop01.Calculator.*(..))", throwing="ex")
//    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//        System.out.println("aop AfterThrowing ex1: method is " + method +" args is " + list);
//        System.out.println("aop AfterThrowing ex1: exception is " + ex);
//    }
//
//    //异常通知：目标方也即连接点法执行出现异常后执行
//    //且可以在指定抛出指定异常的时候执行 如 给的ex 类型为 NullPointerException 时执行该通知
//    @AfterThrowing(value = "execution(public int com.tomatoman.springaop01.Calculator.*(..))", throwing="ex")
//    public void afterThrowingMethod(JoinPoint joinPoint, NullPointerException ex) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//        System.out.println("aop AfterThrowing ex2: method is " + method +" args is " + list);
//        System.out.println("aop AfterThrowing ex2: exception is " + ex);
//    }
//
//
//    //异常通知：目标方也即连接点法执行出现异常后执行
//    //且可以在指定抛出指定异常的时候执行 如 给的ex 类型为 NullPointerException 时执行该通知
//    @Around(value = "execution(public int com.tomatoman.springaop01.Calculator.*(..))")
//    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
//        String method = joinPoint.getSignature().getName();
//        List list = Arrays.asList(joinPoint.getArgs());
//
//        Object result;
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        System.out.println("aop AfterThrowing ex2: method is " + method +" args is " + list);
////        System.out.println("aop AfterThrowing ex2: exception is " + ex);
//
//        return null;
//    }
//
//    @Before(value = "execution(public String com.tomatoman.springaop01.AopController.helloAop())")
//    public void beforeHelloAopMethod() {
//        System.out.println("beforeHelloAopMethod ");
//    }
}
