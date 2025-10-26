// user-service, routine-service 공통으로 추가 (패키지 경로만 조정)
package routine.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {
    private static final String KEY = "SECRET_MINIPROJECT"; // 데모

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String key = req.getHeader("X-API-KEY");
        if (key == null || !KEY.equals(key)) {
            ((HttpServletResponse) response).setStatus(401);
            return;
        }
        chain.doFilter(request, response);
    }
}
