package com.jy.pc.Enum;

/**
 * 用于维护前后端联调状态码 200为成功
 * 系统异常：5xxx
 * 业务逻辑校验：6xxx
 */
public enum InterfaceCode {
	
	SUCCESS("200","调用成功"),
	FAIL_UNKNOWN_ERROR("5001","后台发生未知异常"),
	FAIL_PARAM_EMPTY("5002","缺乏必要参数"),
	FAIL_PARAM_ERROR("5003","参数错误"),
	LESSON_NUM_LIMIT("6001","报名人数已达上限");
	;
	
	private String code;
    private String message;
    InterfaceCode(String code,String message){
    	this.code = code;
    	this.message = message;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
