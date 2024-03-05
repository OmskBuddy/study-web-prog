package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {

    private HttpServletRequest currentRequest;

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        currentRequest = request;
        view.put("userCount", (new UserService()).findCount());

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }

        String message = getMessage();
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void setMessage(String message) {
        currentRequest.getSession().setAttribute("message", message);
    }

    protected String getMessage() {
        return (String) currentRequest.getSession().getAttribute("message");
    }

    protected void setUser(User user) {
        currentRequest.getSession().setAttribute("user", user);
    }

    protected User getUser() {
        return (User) currentRequest.getSession().getAttribute("user");
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {

    }

}
