package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 고객의 요청이 올때마다 doFilter가 실행됨.
 */

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();// 사용자의 모든 로그

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}] [{}]", uuid, requestURI);
            chain.doFilter(request, response); // chain을 해주지 않으면 여기서 끝난다!
        } catch (Exception e){
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }

    }

    @Override
    public void destroy() {

    }
}
