package com.tomatoman.springaop01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AopController {

    @Autowired
    CalculatorImpl calculatorImpl;

    @ResponseBody
    @RequestMapping("helloaop")
    public String helloAop(){
        int result = calculatorImpl.add(1, 2);

        System.out.println("add result = " + result);
        return "Hello Aop";
    }
}
