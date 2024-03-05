package ru.itmo.web.hw4.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        File file1 = new File("D:/Study/2nd course/3rd semester/web-prog/hw4/src/main/webapp" + uri);
        File file2 = new File(getServletContext().getRealPath(uri));

        if (file1.isFile()) {
            response.setContentType(getServletContext().getMimeType(file1.getName()));
            Files.copy(file1.toPath(), response.getOutputStream());
        } else if (file2.isFile()) {
            response.setContentType(getServletContext().getMimeType(file2.getName()));
            Files.copy(file2.toPath(), response.getOutputStream());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
