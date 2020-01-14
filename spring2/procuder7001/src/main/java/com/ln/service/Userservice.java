package com.ln.service;

import java.util.List;

import serverCloudApi.entitys.User;

public interface Userservice {

	public List<User> findList();

	int add(User user);
}
