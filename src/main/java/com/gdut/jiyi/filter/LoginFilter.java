package com.gdut.jiyi.filter;

import cn.hutool.core.util.URLUtil;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.util.CookieUtil;
import com.gdut.jiyi.util.RedisUtil;
import com.gdut.jiyi.util.RegUtil;
import com.gdut.jiyi.util.ResultUtil;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 登录控制过滤器
 */
public class LoginFilter implements Filter {

    private HashOperations<String, String, Object> hashOperations;
    /**
     * 不用登录即可以访问的url
     */
    private static List<String> passUrls = new ArrayList<>();

    static {
        //注册
        passUrls.add("/user/addUser");
        passUrls.add("/user/add");
        passUrls.add("/user/addFarmer");
        passUrls.add("/user/login");
        passUrls.add("/user/getUserInfoById");
        passUrls.add("/user/resetPassByPhone");
        passUrls.add("/code/imgCode");
        passUrls.add("/code/imgCodeWithCookie/**");
        passUrls.add("/code/phoneCode");
        passUrls.add("/code/resetPassPhoneCode");
        passUrls.add("/wb/code");
        passUrls.add("/wb/token");
        passUrls.add("/qq/code");
        passUrls.add("/qq/token");
        passUrls.add("/authorize/banding");
        passUrls.add("/upload/getDefaultAvatar");
        passUrls.add("/upload/uploadAvatar");
        passUrls.add("/upload/uploadAvatar");
        passUrls.add("/product/listPageCapableBuyProduct");
        passUrls.add("/product/getCapableBuyProductSize");
        passUrls.add("/product/searchCapableBuyProductSize");
        passUrls.add("/product/searchPageCapableBuyProduct");
        passUrls.add("/product/getProductDetailById");
        passUrls.add("/product/listPageAllProduct");
        passUrls.add("/product/getHotProduct");
        passUrls.add("/product/getProductDetailByCategory");


        passUrls.add("/alipay/doPay");
        passUrls.add("/alipay/returnUrl");
        passUrls.add("/alipay/notifyUrl");
        passUrls.add("/product/getPhoto");

        passUrls.add("/admin/login");
        passUrls.add("/admin/add");
        passUrls.add("/admin/checkLogin");

        passUrls.add("/swagger-ui.html/**");
        passUrls.add("/swagger-resources/**");
        passUrls.add("/swagger/**");
        passUrls.add("/**/v2/api-docs");
        passUrls.add("/**/*.js");
        passUrls.add("/**/*.html");
        passUrls.add("/**/*.css");
        passUrls.add("/actuator/**");
        passUrls.add("/webjars/springfox-swagger-ui/**");
        passUrls.add("/swagger-ui.html#");
        passUrls.add("/**/*.ico");
        passUrls.add("/**/*.png");
        passUrls.add("/category/listAllNoRank");
        passUrls.add("/category/listActive");




    }

    @Override
    public void init(FilterConfig filterConfig) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        hashOperations = context.getBean(HashOperations.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //跨域请求，*代表允许全部类型
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        //允许请求方式
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //用来指定本次预检请求的有效期，单位为秒，在此期间不用发出另一条预检请求
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        //请求包含的字段内容，如有多个可用哪个逗号分隔如下
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,Authorization, x-ui-request,lang");
        //访问控制允许凭据，true为允许
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // 浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
        // 配置options的请求返回
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.setStatus(HttpStatus.SC_OK);
            httpServletResponse.getWriter().write("OPTIONS returns OK");
            return;
        }
        String requestURI = httpServletRequest.getRequestURI();
        String path = URLUtil.getPath(requestURI);

        String sessionCookieVal = CookieUtil.getSessionCookieVal(httpServletRequest, httpServletResponse);

        RedisUtil.updateSessionExpireTime( hashOperations,sessionCookieVal);
        if(path.startsWith("/null")){
            path = path.substring(5);
        }
        for (String str : passUrls){
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            if ( antPathMatcher.match(str, path)) {
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }
        }

        String sessionUserId = RedisUtil.getSessionUserId(hashOperations, sessionCookieVal);
        if ( RegUtil.isEmpty(sessionUserId)){
            needLoginResponse( httpServletResponse,"未登录");
        }else {
            //放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private void needLoginResponse(HttpServletResponse httpServletResponse, String msg) throws IOException {
        ResultVo resultVo = ResultUtil.UNAUTHORIZED(msg);
        JSONObject jsonObject = new JSONObject(resultVo);
        jsonObject.remove("success");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
        httpServletResponse.getOutputStream().flush();
        httpServletResponse.getOutputStream().close();
    }
}
