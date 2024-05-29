package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.Books;
@Mapper
public interface BooksDao {

	List<Books> lists();

	void save(Books books);

	Books findByID();

	Books findById(Integer id);

	void update(Books books);

	void delete(Integer id);


	List<Books> seachBook(String searchName, String searchAuthor, String lowPrice, String topPrice);

}
