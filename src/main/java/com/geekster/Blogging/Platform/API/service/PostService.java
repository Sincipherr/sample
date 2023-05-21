package com.geekster.Blogging.Platform.API.service;

import com.geekster.Blogging.Platform.API.Repository.IAuthenticationRepo;
import com.geekster.Blogging.Platform.API.Repository.IPostRepo;
import com.geekster.Blogging.Platform.API.model.Post;
import com.geekster.Blogging.Platform.API.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    IPostRepo pr;

    @Autowired
    IAuthenticationRepo ar;

    public List<Post> getallpost(String token) {
        User user=ar.findFirstByToken(token).getUser();
        List<Post> list=pr.findByUser(user);
        return list;
    }

    @Transactional
    public void updatepost(Long postid,String data) {
        pr.updatepost(postid,data);
    }

    public void addpost(Post post) {
        pr.save(post);
    }

    public void deletepost(Long postid) {
        pr.deleteById(postid);
    }
}
