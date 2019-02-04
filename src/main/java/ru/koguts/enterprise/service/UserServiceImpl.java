package ru.koguts.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koguts.enterprise.model.User;
import ru.koguts.enterprise.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        if (user == null) return;
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        if (name.isEmpty() || name == null) return null;
        return userRepository.findByName(name);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        if (id.isEmpty() || id == null) return null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.orElse(null) == null) return null;
        return optionalUser.get();
    }

    @Override
    public void update(User user) {
        create(user);
    }

    @Override
    public void delete(User user) {
        if (user == null) return;
        userRepository.delete(user);
    }
}
