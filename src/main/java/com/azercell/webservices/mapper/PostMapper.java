package com.azercell.webservices.mapper;

import com.azercell.webservices.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void createPost(Post post);
    List<Post> getPostsByUserId(Integer userId);

    Integer getCurrentId();
}
