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
		return "Issue with with given name already exists!";
	}

	public static String IssueNameNotFoundException(){
		return "Sai MAC";
	}

	public static String RecommendNotFoundException() {
		return "Hỗ trợ KH reset MAC trên tool inside";
	}

	public static String listIssueNotFoundException(){
		return "WiFi kết nối được nhưng không truy cập được internet, Không truy cập được một số dịch vụ hoặc website";
	}
}
