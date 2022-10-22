package com.azercell.webservices.mapper;

import com.azercell.webservices.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<User> findAll();
    Optional<User> findById(Integer id);
    void save(User user);

    int getInsertedId();

    void deleteById(int id);
}
