package com.tomatoman.springaop01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @Autowired
    Calculator calculator;

    @ResponseBody
    @RequestMapping("helloaop/{action}")
    public String helloAop(@PathVariable String action){
        int result = 0;

        if ("plus".endsWith(action)) {
            result = calculator.add(1, 2);
        }else if("min".endsWith(action)) {
            result = calculator.min(1, 2);
        }else if("mul".endsWith(action)) {
            result = calculator.mul(1, 2);
        }else if("div".endsWith(action)) {
            Verify verify = (Verify) calculator;
            int b = 0;
            if (verify.verify(b)) {
                result = calculator.div(1, 0);
            }
        }

        return "Hello Aop " + result;
    }
}
