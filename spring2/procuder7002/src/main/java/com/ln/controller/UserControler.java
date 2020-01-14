package com.ln.controller;

import com.ln.config.QuartzConfig;
import com.ln.utils.RedisCacheManager;
import com.ln.utils.Redisutils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import serverCloudApi.entitys.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserControler {
	@Autowired
	private Redisutils redisutils;
	@Autowired
	private RedisCacheManager redisCacheManager;
	@Autowired
	private com.ln.service.Userservice Userservice;
	//@Autowired
	//private DiscoveryClient client; //eureka服务发现
	@RequiresRoles("admins")
	@RequestMapping(value ="/findList",method=RequestMethod.GET)
	public String findList() {
		//Userservice.findList();
		System.out.println("进入........");
		List<User> list = Userservice.findList();
		for(User s : list){
			System.out.println(s.getDbSource()+"=="+s.getName());
		}
		return "lists";
	}

	@RequestMapping("/denglu")
	public String Login(String name,String pass){
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(name);
		token.setPassword(pass.toCharArray());
		Subject subject= SecurityUtils.getSubject();
		subject.login(token);

		boolean d =	subject.hasRole("admin");
		return "hello";
	}
	@RequestMapping("yidenglu")
	public String denglu(HttpServletRequest request){
		HttpSession session = request.getSession();
		String data = session.getAttribute("ce").toString();
		System.out.println(data+"==================");
		String sts =SecurityUtils.getSubject().getPrincipals().toString();
		System.out.println(sts+"==========");
		return "sucess";
	}

	@RequestMapping("/userLogin")
	public String logins(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("ce", "ok");
		redisutils.puts("aaa", "ceshiRedis",Long.valueOf(500));

		return "login";
	}
	@RequestMapping("/logout")
	public  String logout(HttpServletRequest request){
		HttpSession session =request.getSession();
		String datas = session.getAttribute("ce").toString();
		System.out.println("session共享：======================"+datas);
		String data = redisutils.getforString("aaa").toString();
		System.out.println("redis值得获取：======================"+data);
		SecurityUtils.getSubject().logout();
		redisCacheManager.getCache("shiroCache").remove(SecurityUtils.getSubject().getPrincipals());
		return "login";
	}

}
