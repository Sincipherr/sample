package com.geekster.Blogging.Platform.API.service;

import com.geekster.Blogging.Platform.API.Repository.IAuthenticationRepo;
import com.geekster.Blogging.Platform.API.Repository.IPostRepo;
import com.geekster.Blogging.Platform.API.model.AuthenticationToken;
import com.geekster.Blogging.Platform.API.model.Post;
import com.geekster.Blogging.Platform.API.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authr;

    @Autowired
    IPostRepo pr;

    public void savetoken(AuthenticationToken token) {
        authr.save(token);
    }

    public AuthenticationToken gettoken(User user) {
        return authr.findByUser(user);
    }

    public boolean authenticate(String email, String token) {
        AuthenticationToken authtoken= authr.findFirstByToken(token);
        String expectedemail=authtoken.getUser().getUserEmail();
        return expectedemail.equals(email);
    }

    public User findUserByToken(String token) {
        return authr.findFirstByToken(token).getUser();
    }

    public boolean owner(String token,Long postid) {
        AuthenticationToken auth=authr.findFirstByToken(token);
        Long userid=auth.getUser().getUserId();
        Optional<Post> post=pr.findById(postid);
        Long postuserid=post.get().getUser().getUserId();
        if(userid!=postuserid){
            return false;
        }else{
            return true;
        }
    }
}
