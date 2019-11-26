package com.ln.dao;

import java.util.List;

import serverCloudApi.entitys.User;
public interface UserDao{

	public List<User> findList();
}
