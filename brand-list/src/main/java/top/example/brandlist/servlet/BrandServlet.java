package top.example.brandlist.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.example.brandlist.entity.Brand;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/brand")
public class BrandServlet extends HttpServlet {

    static List<Brand> list = new ArrayList<>();

    static {
        list.add(Brand.builder().companyName("apple").brandName("iPhone14").description("苹果-iPhone14").build());
        list.add(Brand.builder().companyName("huawei").brandName("mate40").description("华为-mate40").build());
        list.add(Brand.builder().companyName("benz").brandName("mercedes").description("奔驰-梅赛德斯").build());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 brandList 转成 JSON 串返回给客户端
        resp.setContentType("application/json;charset=utf-8");
        String jsonString = JSONArray.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader br = request.getReader();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }

        // 2. 将JSON字符串转为Java对象
        Brand brand = JSON.parseObject(stringBuilder.toString(), Brand.class);

        list.add(brand);

        response.setContentType("application/json;charset=utf-8");
        String jsonString = JSONArray.toJSONString(brand);
        response.getWriter().write(jsonString);
    }
}