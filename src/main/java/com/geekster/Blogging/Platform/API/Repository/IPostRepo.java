package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.controller.PostController;
import com.geekster.Blogging.Platform.API.model.Post;
import com.geekster.Blogging.Platform.API.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends CrudRepository<Post,Long> {
    List<Post> findByUser(User user);


    @Modifying
    @Query(value = "update post set post_data= :data where post_id= :postid",nativeQuery = true)
    void updatepost(Long postid, String data);
}
