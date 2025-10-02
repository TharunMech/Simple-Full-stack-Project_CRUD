package com.codewithme.fullstack_backend.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithme.fullstack_backend.exception.UserNotFoundException;
import com.codewithme.fullstack_backend.model.User;
import com.codewithme.fullstack_backend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	 @Autowired
	 private UserRepository userRepository;
	 
	 @PostMapping("/user")
	 User newUser(@RequestBody User newUser) {
		 return userRepository.save(newUser);
	 }
	 
	 @GetMapping("/users")
	 java.util.List<User> getAllusers(){
		 return userRepository.findAll();
	 }
	 @GetMapping("/user/{id}")
	 User getuserById(@PathVariable Long id) {
		 return userRepository.findById(id)
				 .orElseThrow(()-> new UserNotFoundException(id));
	 }
	 
	 @PutMapping("/user/{id}")
	 User updateUser(@RequestBody User newUser,@PathVariable long id) {
		 return userRepository.findById(id)
				 .map(user ->{
					 user.setUsername(newUser.getUsername());
					 user.setName(newUser.getName());
					 user.setGmail(newUser.getGmail());
					 return userRepository.save(user);
				 }).orElseThrow(()-> new UserNotFoundException(id));
	 }
	 
	 @DeleteMapping("/user/{id}")
	 String deleteUser(@PathVariable long id) {
		 if(!userRepository.existsById(id)) {
			 throw new UserNotFoundException(id);
		 }
		 userRepository.deleteById(id);
		 return "user with id"+id+" has deleted succussfully";
	 }
	 
	 
	 
	 
}
