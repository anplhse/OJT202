package com.example.demo.exception;

public class IssueCollectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public IssueCollectionException(String message) {
		super(message);
	}

	public static String NotFoundException(String id) {
		return "Issue with " + id + " not found!";
	}

	public static String IssueAlreadyExists() {
		return "Issue with given name already exists";
	}
}
