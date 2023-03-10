package ru.netology.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler {
    void handle(HttpServletRequest request, HttpServletResponse resp, String path) throws IOException;
}
