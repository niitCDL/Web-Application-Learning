package com.example.book.controller;

import com.example.book.entity.User;
import com.example.book.service.UserService;
import com.example.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/login","/signIn","/isExist"})
public class UserController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        response.setCharacterEncoding("UTF-8");
        if ("/login".equals(servletPath)){
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            login(request,response,account,password);
        }else if ("/signIn".equals(servletPath)){
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            signIn(response,account,password);
        }else if ("/isExist".equals(servletPath)){
            isExist(response,request.getParameter("account"));
        }
    }

    public void login(HttpServletRequest request,HttpServletResponse response,String account,String password) throws IOException, ServletException {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        HttpSession session = request.getSession();
        User findUser = userService.findUserByAccountAndPass(user);
        if (findUser != null){
            session.setAttribute("user",findUser);
            System.out.println(findUser);
            response.sendRedirect("index");
        }else {
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("account",user.getAccount());
            request.setAttribute("password",user.getPassword());
            request.getRequestDispatcher("login.html").forward(request,response);
        }
    }

    public void signIn(HttpServletResponse response,String account,String password) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        User user = User.builder().account(account).password(password).build();
        PrintWriter out = response.getWriter();
        boolean flag = userService.signIn(user);
        if (flag){
            out.write("true");
        }else {
            out.write("注册失败");
        }
        out.close();
    }

    public void isExist(HttpServletResponse response,String account) throws IOException {
        PrintWriter out = response.getWriter();
        if (userService.isExist(account)){
            out.write("用户名已存在");
            out.close();
        };
    }

}
