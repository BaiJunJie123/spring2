package com.ln.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Component
public class Myrealm extends AuthorizingRealm {

  private  static  Map<String,String> map = new HashMap<>();
  static {
      map.put("admin", "123");

  }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      System.out.println("=============进入权限========");
      SimpleAuthorizationInfo sim = new SimpleAuthorizationInfo();
      Set<String> roleSet = new HashSet<>();
      roleSet.add("admin");
      sim.setRoles(roleSet);
      return sim;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
      String name =  authenticationToken.getPrincipal().toString();
      String pass = map.get(name);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken,pass,"OK");

        return info;
    }
}
