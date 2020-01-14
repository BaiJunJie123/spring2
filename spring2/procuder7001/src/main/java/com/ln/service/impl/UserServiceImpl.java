package com.ln.service.impl;

import java.util.List;

import com.ln.dao.UserDao;
import com.ln.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverCloudApi.entitys.User;
@Service
public class UserServiceImpl implements Userservice {
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> findList() {
		// TODO Auto-generated method stub
		return userDao.findList();
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

}
