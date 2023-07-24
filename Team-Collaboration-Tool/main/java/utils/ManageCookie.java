package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCookie {
	//荑좏궎瑜� �깮�꽦
	public static void makeCookie(HttpServletResponse response,String cName,String cValue, int cTime) {
		Cookie cookie = new Cookie(cName,cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	//荑좏궎 媛� �씫湲�(諛섑솚)
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue="";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				String cookName = c.getName();
				if(cookName.equals(cName)){
					cookieValue=c.getValue();
				}
			}
		}
		return cookieValue;
	}
	
	//荑좏궎瑜� �궘�젣(
	public static void deleteCookie(HttpServletResponse response,String cName) {
		makeCookie(response, cName,"",0);
	}
}
