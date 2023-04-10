package com.adobe.exception;

public class MyErrorDetails extends RuntimeException{
	
	private String fieldName;
	private String fieldTitle;
	private String fieldInfo;
	
	
	public MyErrorDetails(String fieldName, String fieldTitle, String fieldInfo) {
		super(String.format("%s is not found with %s : %s",fieldName,fieldTitle,fieldInfo));
		this.fieldName = fieldName;
		this.fieldTitle = fieldTitle;
		this.fieldInfo = fieldInfo;
	}
	
	public MyErrorDetails(String msg) {
		super(String.format("%s",msg));
		this.fieldName = msg;
		
	}

}
