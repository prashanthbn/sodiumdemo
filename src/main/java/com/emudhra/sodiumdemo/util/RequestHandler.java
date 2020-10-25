/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emudhra.sodiumdemo.util;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author prashanth
 */
public class RequestHandler {

    public static <T> T parseRequest(HttpServletRequest request, Gson gson, Class<T> clazz) throws IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }
        return (T) gson.fromJson(sb.toString(), clazz);
    }

}
