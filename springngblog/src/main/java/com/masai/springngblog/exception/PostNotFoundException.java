package com.masai.springngblog.exception;

public class PostNotFoundException extends RuntimeException{

	 public PostNotFoundException(String message) {
	        super(message);
	    }
}
