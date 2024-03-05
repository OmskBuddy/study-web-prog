package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestServlet extends HttpServlet {
    private ArrayList<PersonRequest> messages = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();

        if (uri.endsWith("/message/auth")) {
            String user = request.getParameter("user");
            String json = "";
            if (user != null) {
                session.setAttribute("user", user);
                json = (String) new Gson().toJson(new Person(user));
            } else if (session.getAttribute("user") != null) {
                json = (String) new Gson().toJson(new Person((String) session.getAttribute("user")));
            }
            response.setContentType("application/json");
            response.getWriter().write(json);
            response.getWriter().flush();
        } else if (uri.endsWith("/message/findAll")) {
            String json = (String) new Gson().toJson(messages);

            response.setContentType("application/json");
            response.getWriter().write(json);
            response.getWriter().flush();
        } else if (uri.endsWith("/message/add")) {
            String text = request.getParameter("text");
            String user = (String) session.getAttribute("user");

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            messages.add(new PersonRequest(user, text));
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

class Person {
    private String user;
    public Person(String user) {
        this.user = user;
    }
}

class PersonRequest {
    private String user;
    private String text;

    public PersonRequest(String user, String text) {
        this.user = user;
        this.text = text;
    }
}