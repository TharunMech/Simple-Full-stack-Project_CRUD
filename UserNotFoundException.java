package com.codewithme.fullstack_backend.exception;

public class UserNotFoundException extends RuntimeException{
 
	public UserNotFoundException(long id) {
		super("Not Found the User"+id);
	}
}
