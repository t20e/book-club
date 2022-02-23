package com.avis.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avis.bookclub.model.Book;
import com.avis.bookclub.model.User;
import com.avis.bookclub.model.UserLogin;
import com.avis.bookclub.services.WebService;


@Controller
public class WebController {
	@Autowired
	private WebService webService;
	
	
	@GetMapping("/")
	public String index(Model view ) {
		view.addAttribute("RegUser", new User());
		view.addAttribute("loginUser", new UserLogin());
		return "index.jsp";
	}
	@GetMapping("/profile")
	public String index( HttpSession session, Model view) {
		//check logged in?
		Long id = (Long) session.getAttribute("loggedInUserId");
		if( id == null ) {
			return "redirect:/";
		}
		view.addAttribute("getAllBooksBook", this.webService.getAllBooks() );
		view.addAttribute("user", this.webService.getOneUser(id) );
		return "profile.jsp";
	}
	//view page
	@GetMapping("/book/view/{id}")
	public String getBookInfo(@PathVariable("id")Long id, Model view, HttpSession session) {
		//check logged in?
		Long idInSession = (Long) session.getAttribute("loggedInUserId");
		if( idInSession == null ) {
			return "redirect:/";
		}
		view.addAttribute("book", this.webService.getOneBook(id));
		return "showOneBook.jsp";
	}
	//reg
	@PostMapping("/user/reg")
	public String regUser(@Valid @ModelAttribute("RegUser") User regUser, BindingResult result , Model view, HttpSession session) {
		// registerUSer willl do all the valid user form
		User user = this.webService.registerUser(regUser, result);
		if(result.hasErrors()) {
			view.addAttribute("loginUser", new UserLogin());
			return "index.jsp";
		}
		// no errors then do this
		//store id in session and redirect
		session.setAttribute("loggedInUserId", user.getId());
		return "redirect:/profile";
	}
	//login
	@PostMapping("/user/login")
	public String loginUser(@Valid @ModelAttribute("loginUser")UserLogin loginUser, BindingResult result, Model view, HttpSession session ) {
			User user = webService.login(loginUser, result);
			if(result.hasErrors()) {
				view.addAttribute("RegUser", new User());
				return "index.jsp";
			}
			//no errors then
			session.setAttribute("loggedInUserId", user.getId());
			System.out.println(user.getEmail());
		return "redirect:/profile";
	}
//	logout 
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//add book page
	@GetMapping("/book/add")
	public String addBookPage(Model view, HttpSession session) {
		Long id = (Long) session.getAttribute("loggedInUserId");
		if( id == null ) {
			return "redirect:/";
		}
		view.addAttribute("sessionId", id );
		
		view.addAttribute("addBookObj", new Book());
		return "add_book.jsp";
	}
//	adding book
	@PostMapping("/book/adding")
	public String addingBook(@Valid @ModelAttribute("addBookObj") Book bookobj, BindingResult result,Model view) {
		if(result.hasErrors()) {
			return "add_book.jsp";
		}
		Book addBook = this.webService.createBook(bookobj);
		return "redirect:/profile";
	}
	
	//edit book page
	@GetMapping("book/edit/{id}")
	public String editBookPage(@PathVariable("id") Long id, Model view, HttpSession session)	{
		Long iDLong = (Long) session.getAttribute("loggedInUserId"); 
		if( iDLong == null ) {
			return "redirect:/";
		}
		view.addAttribute("sessionId", iDLong );
		view.addAttribute("bookObj", new Book());
		view.addAttribute("book", this.webService.getOneBook(id));
		return "edit_page.jsp";
	}
	//editing book
	@PutMapping("/book/editing/{id}")
	public String editingBook(@Valid @ModelAttribute("book") Book editingBook, BindingResult result, @PathVariable("id") Long id) {
		if( result.hasErrors() ) {
			return "edit_page.css";
		}
		Book editBook = this.webService.editBook(editingBook);
		return "redirect:/profile";
		
	}
	
	
	
	
	
}
