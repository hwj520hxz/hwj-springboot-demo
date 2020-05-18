package com.hwj.demo.user.authorize.integrated.shiro;

import com.hwj.demo.component.audit.support.AuditContext;
import com.hwj.demo.component.encryption.jwt.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：JWT拦截器(重写内置BasicHttpAuthenticationFilter过滤器)
 * 流程：
 * 1.先执行preHandle方法：预处理
 * 2.执行isAccessAllowed方法：该方法用于判断是否登录，底层通过subject.isAuthenticated()方法判断的是否登录。
 * 如果未登录则返回false，进入onAccessDenied方法，如果已经登录则返回true，不用继续验证可以直接访问接口
 * 3.未登录执行onAccessDenied方法：判断是否拒绝访问，就是当用户没有访问过该过滤器过滤的接口就必须进行httpBasic验证
 * 在HttpAuthenticationFilter中可以看到，该方法先执行isLoginAttempt方法判断是否登录模式，如果是登录模式则执行executeLogin方法，否则执行sendChallenge方法
 * 4.如果是登录模式执行isLoginAttempt，如果header携带Authorization则返回true表示是登录模式，反之不是登录模式
 * 5.如果不是登录模式执行sendChallenge，该方法的作用是在请求没有携带header（Authorization）时，添加响应头WWW-Authenticate进行httpBasic验证
 * 6.浏览器接收到含有WWW-Authenticate的响应头会弹出输入用户名与密码的输入框
 * 7.executeLogin方法：通过获取请求头Authorization的获取username与password创建token然后掉用subject.login(token)使用该token进行登录验证
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    String loginUrl;

    /** 常量定义 */
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String AJAX_SIGN = "XMLHttpRequest";

    public JWTFilter(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * 请求是否已经登录（携带token）
     * 检测header里面是否包含Authorization字段 或 检测cookie里是否包含accessToken
     **/
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;  //获取request对象
        // 如果已经携带Authorization参数说明已经登录过
        if(StringUtils.isNotEmpty(req.getHeader(JWTUtil.TOKEN))){
            return true;
        }
        // 如果cookies中存在accessToken令牌说明已经登录过
        return StringUtils.isNotEmpty(this.getTokenWithCookie(req));
    }

    /**
     * 执行shiro的登录动作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(JWTUtil.TOKEN);  //获取header中的Authorization
        // 如果header中没有包含Authorization字段则从cookies中获取
        if(StringUtils.isEmpty(authorization)){
            String accessToken = this.getTokenWithCookie(req);  //获取身份令牌
            if(StringUtils.isNotEmpty(accessToken)){
                authorization = accessToken;
            }
        }
        JWTToken token = new JWTToken(authorization);
        // 执行realm进行登录
        try {
            getSubject(request,response).login(token);
        } catch (AuthenticationException e){
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    /**
     * 未授权的请求返回
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //ajax请求比传统请求多一个请求头X-Requested-With
        String header = httpServletRequest.getHeader("X-Requested-With"); //ajax所持有的标识
        if(StringUtils.isNotEmpty(header) && AJAX_SIGN.equals(header)){
            return super.sendChallenge(request,response);
        }
        try {
            // 重定向
            httpServletResponse.sendRedirect(loginUrl);
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行
     * 清理资源
     **/
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        UserContext.remove();
        AuditContext.remove();
        super.afterCompletion(request, response, exception);
    }


    /**
     * 获取Cookie里的token
     */
    public String getTokenWithCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();  //获取cookies
        String token = null;
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(ACCESS_TOKEN.equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }
}
