package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.model.User;

public interface UserService {
    User login(String email, String password);

    User register(String username, String email, String password);
}
