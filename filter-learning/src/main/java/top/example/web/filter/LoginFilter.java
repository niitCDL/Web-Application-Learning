package top.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String[] urls = {"/login.jsp"};
        String url = request.getRequestURL().toString();
        for (String u : urls) {
            if (url.contains(u)) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }
        System.out.println(url);
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        System.out.println(user);

        if (user != null) {
            filterChain.doFilter(request, servletResponse);
        } else {
            String msg = "当前用户没有登录";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("login.jsp").forward(request, servletResponse);
        }
    }
}
