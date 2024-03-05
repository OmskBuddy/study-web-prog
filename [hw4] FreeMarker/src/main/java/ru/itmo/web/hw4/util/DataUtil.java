package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.User;
import ru.itmo.web.hw4.model.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.itmo.web.hw4.model.User.Color.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", RED),
            new User(6, "pashka", "Pavel Mavrin", GREEN),
            new User(9, "geranazavr555", "Georgiy Nazarov", BLUE),
            new User(11, "tourist", "Gennady Korotkevich", GREEN),
            new User(12, "HarryWotton", "Nikita Klushkin", RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Round 1", "Round 1 starts at 10-00 ".repeat(5), 1),
            new Post(2, "Round 2", "Round 2 starts at 11-00 ".repeat(5), 6),
            new Post(3, "Round 3", "Round 3 starts at 12-00 ".repeat(5), 9),
            new Post(4, "Round 4", "Round 4 starts at 13-00 ".repeat(20), 11)
    );

    private static final Map<String, Integer> countOfPosts = Map.of(
            "1", 1,
            "6", 1,
            "9", 1,
            "11", 1
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        String uri = request.getRequestURI();

        data.put("users", USERS);
        data.put("posts", POSTS);
        data.put("post_count", countOfPosts);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }

        data.put("currentUrl", uri);
    }
}
