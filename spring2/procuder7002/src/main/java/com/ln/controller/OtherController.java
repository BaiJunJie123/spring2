package com.ln.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.idn.Punycode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 白俊杰
 * @Date 2019/12/9
 * @Description
 **/
@RestController
public class OtherController  {

    @RequestMapping("/dl")
    public String logins(HttpServletRequest request){

        return "login";
    }
}
