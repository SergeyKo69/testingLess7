package ru.koguts.enterprise.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.koguts.enterprise.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String name);

}
