package com.book.dao;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.User;
@Mapper
public interface UserDao {

	User findByUserName(String username);

	void save(User user);

}
