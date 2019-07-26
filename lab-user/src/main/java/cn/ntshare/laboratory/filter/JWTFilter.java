package cn.ntshare.laboratory.filter;

import cn.ntshare.laboratory.token.JWTToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的JWT过滤器
 * 过滤流程为 preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登录，如果错误则会抛出异常
        getSubject(request, response).login(token);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                responseLogin(request, response);
                return false;
            }
        }
        return true;
    }

    /**
     * 支持跨域
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        // 将跨域发出的option请求返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 认证失败时重定向到登录页面
     * @param request
     * @param response
     */
    private void responseLogin(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            httpServletResponse.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
