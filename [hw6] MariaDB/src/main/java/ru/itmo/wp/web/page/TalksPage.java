package ru.itmo.wp.web.page;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        super.action(request, view);

        if (getUser() == null) {
            setMessage("You need authorise to visit this page.");
            throw new RedirectException("/index");
        }
    }
}
