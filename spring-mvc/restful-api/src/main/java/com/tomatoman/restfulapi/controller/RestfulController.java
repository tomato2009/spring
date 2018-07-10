package com.tomatoman.restfulapi.controller;

import com.tomatoman.restfulapi.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring mvc 对Restful的匹配是精准匹配优先，有点类似于nginx location匹配
 *
 * 客户端测试工具：postman，轻松模拟 get、post、put、delete请求
 */
@Controller
public class RestfulController {

    @RequestMapping("hello")
    @ResponseBody
    private String hello() {
        return "hello";
    }

    /**
     * 任何只有一级path 的请求，都可能被定位到该方法
     * 如访问：
     *  http://localhost:8089/123
     *  http://localhost:8089/ddd
     *
     * 但是如果有一个RequestMapping精准匹配上了，就不会响应此方法，而是调用精准匹配的方法
     * 如访问：http://localhost:8089/hello
     * 只会响应到 @RequestMapping("hello")
     * @param cmd
     * @return
     */
    @RequestMapping("{cmd}")
    @ResponseBody
    private String anything(@PathVariable String cmd) {
        return "{cmd} " + cmd;
    }

    /**
     * http://localhost:8089/123/qqq
     * http://localhost:8089/ddd/rtt
     * 任何只有两级path 的请求，都可能被定位到该方法
     *
     * 但是如果有一个RequestMapping精准匹配上了，就不会响应此方法，而是调用精准匹配的方法
     * 如访问：http://localhost:8089/hello/123
     * 只会响应到 @RequestMapping("hello/{id}")
     * @param cmd
     * @param cmd2
     * @return
     */
    @RequestMapping("{cmd}/{cmd2}")
    @ResponseBody
    private String anythingDouble(@PathVariable String cmd, @PathVariable String cmd2) {
        return "{cmd}/{cmd2} " + cmd + " " + cmd2;
    }

    /**
     * http://localhost:8089/hello/555
     * http://localhost:8089/hello/rtt
     * 任何只有两级path 的请求，且一级path为hello，都可能被定位到该方法
     *
     * 但是：
     * 如果有一个RequestMapping精准匹配上了，就不会响应此方法，而是调用精准匹配的方法
     * 如访问：http://localhost:8089/hello/hello
     * 只会响应到 @RequestMapping("hello/hello")
     *
     * 如果有一个RequestMapping 的Method 精准匹配上了，就不会响应此方法，而是调用精准匹配的方法
     * 如访问：http://localhost:8089/hello/hello  为GET请求
     * 只会响应到 @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
     * @param id
     * @return
     */
    @RequestMapping("hello/{id}")
    @ResponseBody
    private String hello(@PathVariable String id) {
        return "hello/{id} " + id;
    }

    /** 精准匹配
     * http://localhost:8089/hello/hello
     * @return
     */
    @RequestMapping("hello/hello")
    @ResponseBody
    private String helloDouble() {
        return "hello/hello ";
    }

    /**
     * 访问：http://localhost:8089/hello/123
     * http://localhost:8089/hello/ggg GET请求
     * @param id
     * @return
     */
    @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
    @ResponseBody
    private String helloGet(@PathVariable String id) {
        return "get  hello/{id} " + id;
    }

    /**
     * 访问：http://localhost:8089/hello
     * http://localhost:8089/hello POST请求
     * @return
     */
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    private String helloPost(User user) {
        return "post  hello " + user.toString();
    }

    /**
     * 访问：http://localhost:8089/hello
     * http://localhost:8089/hello PUT请求
     * @return
     */
    @RequestMapping(value = "hello", method = RequestMethod.PUT)
    @ResponseBody
    private String helloPut(User user) {
        return "put  hello " + user.toString();
    }

    /**
     * 访问：http://localhost:8089/hello/123
     * http://localhost:8089/hello/ggg DELETE请求
     * @param id
     * @return
     */
    @RequestMapping(value = "hello/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    private String helloDelete(@PathVariable String id) {
        return "delete  hello/{id} " + id;
    }

    /**
     * 方便定义api版本，更具版本升级api
     * 访问：http://localhost:8089/v1/hello
     * http://localhost:8089/v2/hello
     * @return
     */
    @RequestMapping("{version}/hello")
    @ResponseBody
    private String helloVersion(@PathVariable String version) {
        return "{version}/hello " + version;
    }

    /**
     * 访问：http://localhost:8089/v1/hello/123
     * http://localhost:8089/v2/hello/fff
     * @return
     */
    @RequestMapping("{version}/hello/{id}")
    @ResponseBody
    private String helloVersionId(@PathVariable String version, @PathVariable String id) {
        return "{version}/hello/{id} " + version + " id " + id;
    }

    /**
     * 访问：
     * http://localhost:8089/hello/ff123
     * http://localhost:8089/hello/ffggg
     *
     *如果访问
     * http://localhost:8089/hello/zz123
     * 二级path不以ff开始，请求会响应到
     * @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
     * @return
     */
    @RequestMapping("hello/ff{id}")
    @ResponseBody
    private String helloVersionIdPrefix(@PathVariable String id) {
        return "prefix hello/{id} " + id;
    }

    /**
     * 访问：
     * http://localhost:8089/hello/db123ss
     * http://localhost:8089/hello/123ss
     *
     *如果访问
     * http://localhost:8089/hello/123zz
     * 二级path不以ff开始，请求会响应到
     * @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
     * @return
     */
    @RequestMapping("hello/{id}ss")
    @ResponseBody
    private String helloVersionIdSubfix(@PathVariable String id) {
        return "subfix hello/{id} " +   id;
    }

}
