package com.azercell.webservices.dao;


import com.azercell.webservices.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private static final List<User> userList = new ArrayList<>();

    private static int usersCount = 3;

    static {
        userList.add(new User(1, "John", new Date()));
        userList.add(new User(2, "Sam", new Date()));
        userList.add(new User(3, "Togrul", new Date()));
    }

    public List<User> findAll() {
        return userList;
    }

    public Optional<User> findOne(Integer id) {
        return userList
                .stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        userList.add(user);
        return user;
    }


}
