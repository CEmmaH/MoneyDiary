package entity.vo;

/**
 * 消息模型对象，数据响应
 * 		状态码： 1=成功； 0=失败
 * 		提示信息： 字符串
 * 		回显数据： Object
 * @version 1.0
 * @author CEmmH
 */
public class MessageModel {
	private Integer code = 1; //1  = success; 0 = failed
	private String msg = "Success!";
	private Object object; //回显对象（基本数据类型，字符串，list, map etc.）
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setMessage(String msg) {
		this.msg = msg;
	}
	public String getMessage() {
		return msg;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Object getObject() {
		return object;
	}
}
