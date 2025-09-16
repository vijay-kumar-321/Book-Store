package com.bookStore.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
//import com.bookStore.repository.BookRepository;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import org.springframework.ui.Model;

@Controller
public class BookController {
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
//	
//	@Autowired
//	BookRepository brepo;
//    
	@GetMapping("/")
	public String home() {
		return "home";
	}
    @GetMapping("/book_register")
    public String bookRegister() {
    	return "bookRegister";
    }
    @GetMapping("/available_books")
    public ModelAndView getAllBooks() {
    	List<Book>list=service.getAllBook();
    	return new ModelAndView("bookList","book",list);
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model){
    	List<MyBookList>list=myBookService.getAllMyBooks();
    	model.addAttribute("book",list);
    	return "myBooks";
    	
    }
    
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
//    	System.out.println(b);
    	service.save(b);
    	return "redirect:/available_books";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
    	Book b=service.getBookById(id);
    	MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
    	myBookService.saveMyBooks(mb);
    	return "redirect:/my_books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
    	Book b=service.getBookById(id);
    	model.addAttribute("book",b);
    	return "bookEdit";
    }
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
}
