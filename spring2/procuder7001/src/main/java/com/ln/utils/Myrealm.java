package com.ln.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
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
  //private  static  Map<String,String> quanxinaMap = new HashMap<>();
  static {
      map.put("admin", "123");

  }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      String ss =principalCollection.getPrimaryPrincipal().toString();
      String zz = principalCollection.getRealmNames().toString();
      System.out.println(zz+"=============进入权限========"+ss);
      //通过CacheManager获取Cache
      //String userName = (String) principalCollection.getPrimaryPrincipal();
      SimpleAuthorizationInfo sim = new SimpleAuthorizationInfo();
      Set<String> roleSet = new HashSet<>();
      Set<String> prenemSet = new HashSet<>();
      //prenemSet.add("perms");
      roleSet.add("admin");
      roleSet.add("admins");
      sim.setRoles(roleSet);
      //sim.setStringPermissions(prenemSet);
      return sim;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
      String name =  authenticationToken.getPrincipal().toString();
      String pass = map.get(name);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name,pass,null,"OK");

        return info;
    }
}
