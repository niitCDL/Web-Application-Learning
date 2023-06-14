package com.example.book.controller;

import com.example.book.service.BookService;
import com.example.book.service.UserService;
import com.example.book.service.impl.BookServiceImpl;
import com.example.book.service.impl.UserServiceImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/index","/login.html","/success","/signin","/top"})
public class IndexController extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建模板引擎，用于最终完成最终页面渲染工作
        TemplateEngine engine = new TemplateEngine();
        //创建渲染网页模板的解析器
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(getServletContext());
        //将解析器绑定到模板引擎中
        engine.setTemplateResolver(resolver);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        //创建一个web上下文（里面有一个map结构，存放键值对）
        WebContext wc = new WebContext(request,response,getServletContext());
        //设置一个键值对数据，键为message(模板中的变量)，值为好好学习(要渲染的值),
        String servletPath = request.getServletPath();
        String html = "";
        if ("/index".equals(servletPath)){
            ServletContext application = request.getServletContext();
//            request.getSession().setAttribute("bookList",bookService.getBookList());
            application.setAttribute("bookList",bookService.getBookList());
            html = engine.process("index",wc);
        }else if ("/login.html".equals(servletPath)){
            html = engine.process("login",wc);
        }else if ("/success".equals(servletPath)){
            html = engine.process("success",wc);
        }else if ("/signin".equals(servletPath)){
            html = engine.process("signin",wc);
        } else if ("/top".equals(servletPath)) {
            html = engine.process("top",wc);
        }
        //模板引擎渲染网页模板，第一个参数为模板名称，第二个参数为web上下文
        //根据模板解析器设置的前缀+模板名称+后缀为模板路径，查找到模板，再组织模板内容+数据
        //返回值就是渲染后的网页字符串
        response.setContentType("text/html; charset=utf-8");//设置响应编码
        response.getWriter().write(html);
    }
}
