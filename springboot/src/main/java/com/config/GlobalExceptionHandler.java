package com.config;

import com.alibaba.fastjson.JSONObject;
import com.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.print(JSONObject.toJSONString(R.error(500, e.getMessage())));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}