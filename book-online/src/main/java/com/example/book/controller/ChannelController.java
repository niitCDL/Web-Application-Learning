package com.example.book.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.book.entity.Channel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/channel")
public class ChannelController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String level = request.getParameter("level");
        String parent = request.getParameter("parent");
        List<Channel> list = new ArrayList<>();
        if ("1".equals(level)){
            list.add(new Channel("人工智能","ai"));
            list.add(new Channel("网页开发","web"));
            list.add(new Channel("深度学习","dl"));
        }else if ("2".equals(level)){
            if ("web".equals(parent)){
                list.add(new Channel("超文本标记语言","html"));
                list.add(new Channel("阿贾克斯","ajax"));
                list.add(new Channel("安克舍斯","axios"));
            }else if ("ai".equals(parent)){
                list.add(new Channel("计算机视觉","cv"));
                list.add(new Channel("速算机器人","sr"));
            } else if ("dl".equals(parent)) {
                list.add(new Channel("慕课网","mukewang"));
                list.add(new Channel("B站","哔哩哔哩大学"));
            }
        }
        response.setContentType("application/json;charset=utf-8");
        System.out.println(list);
        PrintWriter writer = response.getWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        String s = JSONObject.toJSONString(list);
        System.out.println(s);
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
