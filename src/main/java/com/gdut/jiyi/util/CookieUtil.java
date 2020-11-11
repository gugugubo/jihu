package com.gdut.jiyi.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CookieUtil {
     public static final String SESSION_COOKIE_NAME = "scn";

    /**
     * 确保用户cookie存在，若不存在，则添加一个名为scn的cookie，并返回value
     * @param request request
     * @return cookie
     */
    public static String getSessionCookieVal(HttpServletRequest request, HttpServletResponse response){
        String cookieValue = getCookieValue(request);
        if (RegUtil.isEmpty(cookieValue)){
            Cookie cookie = new Cookie(SESSION_COOKIE_NAME, UUID.randomUUID().toString().replaceAll("-",""));
            cookie.setPath("/");
            cookieValue = cookie.getValue();
            response.addCookie(cookie);
        }
        return cookieValue;
    }


    /**
     * 获取名为scn的cookie值
     * @param request request
     * @return cookie值
     */
    private static String getCookieValue(HttpServletRequest request){
        if (request == null || CookieUtil.SESSION_COOKIE_NAME == null){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies){
            if (CookieUtil.SESSION_COOKIE_NAME.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
