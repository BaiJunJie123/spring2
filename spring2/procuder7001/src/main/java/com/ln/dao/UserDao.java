package com.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import serverCloudApi.entitys.User;
public interface UserDao{

	public List<User> findList();

	int add(@Param("user") User user);
}
