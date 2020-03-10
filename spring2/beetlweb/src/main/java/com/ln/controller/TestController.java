package com.ln.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-16 08:51
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String index1(HttpServletRequest request){

        request.setAttribute("title", "www.bjj.com");
        request.setAttribute("ok", "controller set this value");
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("a4");
        list.add("a5");
        list.add("a6");
        request.setAttribute("lista",list);
        return "hello";
       // return "hello";
    }
}
