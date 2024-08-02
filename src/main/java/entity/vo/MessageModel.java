package entity.vo;

/**
 * Message model object for data response
 *     Status code: 1 = Success; 0 = Failure
 *     Message: String
 *     Echo data: Object
 * @version 1.0
 * @author CEmmH
 */

public class MessageModel {
	private Integer code = 1; //1  = success; 0 = failed
	private String msg = "Success!";
	private Object object; //Echo object (basic data types, strings, lists, maps, etc.)
	
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
