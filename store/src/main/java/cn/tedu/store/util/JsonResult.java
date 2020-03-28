package cn.tedu.store.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 向客户端响应操作结果的数据类型
 * @author lenovo
 *
 * @param <T> 向客户端响应的数据
 */
@JsonInclude(Include.NON_NULL)
public class JsonResult<T> {
	private Integer state;
	private String message;
	private T data;
	
	
	public JsonResult() {
		super();
	}
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	public Integer getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	

}
