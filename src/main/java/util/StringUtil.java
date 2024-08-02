package util;

/**
 * 字符串工具类
 * @version 1.0
 * @author CEmmH
 */
public class StringUtil {
	/**
	 * check if a string is empty.
	 * @param str the string to check.
	 * @return true if the string is null or empty; false otherwise 
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str == "") {
			return true;
		}
		return false;
	}
}
