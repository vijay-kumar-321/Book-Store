package com.bookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import java.util.*;

@Service
public class BookService {
	@Autowired
	private BookRepository bRepo;
	
	public void save(Book b) {
		bRepo.save(b);
	}
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public Book getBookId(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
}
