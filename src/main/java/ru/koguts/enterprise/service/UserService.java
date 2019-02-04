package ru.koguts.enterprise.service;

import ru.koguts.enterprise.model.User;

public interface UserService {

    void create(User user);
    User findByName(String name);
    Iterable<User> findAll();
    User findById(String id);
    void update(User user);
    void delete(User user);

}
