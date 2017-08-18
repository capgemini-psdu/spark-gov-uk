package app.user;

import com.google.common.collect.*;
import java.util.*;
import java.util.stream.*;

public class UserDao {

    private final List<User> users = ImmutableList.of(
            new User("jdevaney", "James", "Devaney", "$2a$10$h.dl5J86rGH7I8bD9bZeZe",
                    "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO"),
            new User("sbellinger", "Shaun", "Bellinger", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe",
                    "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy"));

    public User getUserByUsername(String username) {
        return users.stream().filter(b -> b.getUsername().equals(username)).findFirst().orElse(null);
    }

    public Iterable<String> getAllUserNames() {
        return users.stream().map(User::getUsername).collect(Collectors.toList());
    }

}
