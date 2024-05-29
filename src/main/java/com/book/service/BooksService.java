package com.book.service;

import java.util.List;

import com.book.entity.Books;

public interface BooksService {

	List<Books> lists();

	

	Books findById(Integer id);

	void update(Books books);

	void delete(Integer id);



	List<Books> seachBook(String searchName, String searchAuthor, String lowPrice, String topPrice);



	void save(Books books);

}
