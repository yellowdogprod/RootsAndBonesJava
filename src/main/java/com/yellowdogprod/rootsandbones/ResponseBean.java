package com.yellowdogprod.rootsandbones;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T result;
	private String message;
	private Integer code;
	
	public ResponseBean(T res){
		this.result = res;
		this.code = 200;
	}
	
	public ResponseBean(T res, String msg) {
		this.result = res;
		this.message = msg;
		this.code = 200;
	}
	
	public static ResponseBean error(String errorMessage) {
		ResponseBean rb = new ResponseBean();
		rb.setCode(405);
		rb.setMessage(errorMessage);
		return rb;
	}
	
	public static ResponseBean message(String message) {
		ResponseBean rb = new ResponseBean();
		rb.setCode(200);
		rb.setMessage(message);
		return rb;
	}
	
}
