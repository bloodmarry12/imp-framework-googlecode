package com.huawei.imp.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zenggh
 * @version :IMPV100R001DA0,Mar 16, 2010
 * @see 
 * @since CMS IMPV100R001DA0
 */
public class CookieUtil {
	 private CookieUtil(){}
	 
	 public static final boolean hasCookie(String cookieName, HttpServletRequest request){
	  Cookie cookie = getCookie(cookieName, request);
	  return null==cookie?false:true;
	 }
	 
	 public static final String getValue(String cookieName, HttpServletRequest request){
	  Cookie cookie = getCookie(cookieName, request);
	  if(null != cookie){
	   return cookie.getValue();
	  }
	  return null;
	 }
	 
	 private static final Cookie getCookie(String cookieName, HttpServletRequest request){
	  Cookie[] cookies = request.getCookies();
	  if(null != cookieName && !"".equals(cookieName.trim()) && null != cookies){
	   for(Cookie cookie : cookies){
	    if(cookieName.equals(cookie.getName())){
	     return cookie;
	    }
	   }
	  }
	  return null;
	 }
}
