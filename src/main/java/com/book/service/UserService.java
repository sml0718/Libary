package com.book.service;

import com.book.entity.User;

public interface UserService {

	void register(User user);

	User login(String username, String password);



}
