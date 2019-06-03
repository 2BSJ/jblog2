package com.cafe24.jblog.dto;

public class JSONResult {

	private String result; // success , fail
	private String message; // if fail, set
	private Object data; // if success, set

	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data); 
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	private JSONResult(String result,String message, Object data) { // public으로 기본생성자로 만들면 외부에서 생성자를 접근해버릴수 있고
																		// private로 기본생성자로 만들어버리면 팩토리 메서드를 외부에서 사용할 수
																		// 있게해서 접근할 방법을 제공해야함.
		this.result = result;
		this.message = message;
		this.data = data;

	}
	
	

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}
