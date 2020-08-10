package com.jy.pc.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 实现全局异常处理
 * @author 
 *
 */
@ControllerAdvice
public class ExceptionHandlerContrller {
 
	/**
	 * 
	 * 定义@Valid注解全局异常处理机制
	 */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<Object> exception(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder("参数错误：[");
        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError)objectError;
            sb.append(fieldError.getDefaultMessage()).append(',');
        });
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "1");
		map.put("message", sb.toString());
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
