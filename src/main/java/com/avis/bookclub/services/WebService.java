package com.avis.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.avis.bookclub.model.Book;
import com.avis.bookclub.model.User;
import com.avis.bookclub.model.UserLogin;
import com.avis.bookclub.repositories.BookRepository;
import com.avis.bookclub.repositories.UserRepository;


@Service
public class WebService {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private UserRepository userRepo;
	
//	Created user
	public User createUser(User U) {
		return this.userRepo.save(U);
	}
	
	//get all user
	public List<User> getAllUsers(){
		return (List<User>)this.userRepo.findAll();	
		}
	//get one user
	
	public User getOneUser(Long id) {
		return this.userRepo.findById(id).orElse(null);
}
	
//	Created book
	public Book createBook(Book b) {
		return this.bookRepo.save(b);
	}
	//edit book
	public Book editBook(Book b) {
		return this.bookRepo.save(b);
	}
	//get all books
	public List<Book> getAllBooks(){
		return (List<Book>)this.bookRepo.findAll();
	}
	//get one book
	public Book getOneBook(Long id) {
		return this.bookRepo.findById(id).orElse(null);
	}
	
	public User registerUser(User userReg, BindingResult result) {
		Optional<User> checkNewUserEmail = this.userRepo.findByEmail(userReg.getEmail());
		//check if email exists
		
		if(checkNewUserEmail.isPresent()) {
			result.rejectValue("email","emailduplicate",  "This Email is already in database!.");		}
		//check password and confirm password
		if(!userReg.getPassword().equals(userReg.getConfirmPassword())) {
			result.rejectValue("confirm","nomatchy", "passwords must match");
		}
//		return null if result had errors
		if(result.hasErrors()) {
			return null;
		}
		else {
			String passwordHashed= BCrypt.hashpw(userReg.getPassword(), BCrypt.gensalt());
//			Set the password as hashed password 
			userReg.setPassword(passwordHashed);
			return this.userRepo.save(userReg);
		}
	}
	public User login(UserLogin userLoginObj, BindingResult result) {
		//find user in db by email
		Optional<User> checkLoginUserEmail= this.userRepo.findByEmail(userLoginObj.getEmail());
		if(!checkLoginUserEmail.isPresent()) {
			result.rejectValue("email", "notfound", "email not found in db!");
		}
		//else if email exists check if passwords match using brcypt
		else {
			if(!BCrypt.checkpw(userLoginObj.getPassword(),checkLoginUserEmail.get().getPassword() )) {
				result.rejectValue("password","passwordincorrect", "invaild password");
			}
			
		}
//		return null if  result has errors
		if(result.hasErrors()) {
			return null;
		}else {
//			return the userLoginObj obj
			return checkLoginUserEmail.get();
		}
		
		}
		
	
	
}
