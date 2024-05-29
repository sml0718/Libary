package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.BooksDao;
import com.book.entity.Books;

@Service
@Transactional
public class BooksServiceImpl implements BooksService{

	@Autowired
	private BooksDao booksDao;
	
	@Override
	public List<Books> lists() {
		
		return booksDao.lists();
	}

	
	@Override
	public Books findById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return booksDao.findById(id);
	}

	@Override
	public void update(Books books) {
		// TODO 自動生成されたメソッド・スタブ
		booksDao.update(books);
	}

	@Override
	public void delete(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		booksDao.delete(id);
	}


	@Override
	public void save(Books books) {
		// TODO 自動生成されたメソッド・スタブ
		booksDao.save(books);
	}

	@Override
	public List<Books> seachBook(String searchName, String searchAuthor, String lowPrice, String topPrice) {
		
		return booksDao.seachBook(searchName,searchAuthor,lowPrice,topPrice);
	}

}
