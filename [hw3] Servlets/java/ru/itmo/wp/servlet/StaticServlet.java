package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] URIes = uri.split("\\+");

        for (int i = 0; i < URIes.length; i++) {
            uri = (URIes[i].charAt(0) == '/' ? "" : "/") + URIes[i];
            System.out.println(uri);

            File file1 = new File("D:/Study/2nd course/3rd semester/web-prog/hw3/src/main/webapp/static" + uri);
            File file2 = new File(getServletContext().getRealPath("/static" + uri));

            if (file1.isFile()) {
                if (i == 0) {
                    response.setContentType(getServletContext().getMimeType(file1.getName()));
                }
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(file1.toPath(), outputStream);
                }
            } else if (file2.isFile()) {
                if (i == 0) {
                    response.setContentType(getServletContext().getMimeType(file2.getName()));
                }
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(file2.toPath(), outputStream);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
