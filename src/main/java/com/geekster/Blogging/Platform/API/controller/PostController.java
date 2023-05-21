package com.geekster.Blogging.Platform.API.controller;

import com.geekster.Blogging.Platform.API.model.Post;
import com.geekster.Blogging.Platform.API.model.User;
import com.geekster.Blogging.Platform.API.service.AuthenticationService;
import com.geekster.Blogging.Platform.API.service.PostService;
import com.geekster.Blogging.Platform.API.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService ps;

    @Autowired
    AuthenticationService as;

    @PostMapping(value = "/post")
    public ResponseEntity<String> addpost(@Valid @RequestParam String email, @RequestParam String token, @RequestBody Post post){
        HttpStatus status;
        String message="";
        if(as.authenticate(email,token)){
            User user=as.findUserByToken(token);
            post.setUser(user);
            ps.addpost(post);
            message="Post is added successfully";
            status=HttpStatus.OK;
        }else{
            message="Invalid user";
            status=HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<String>(message,status);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Post>> getallpost(@RequestParam String email,@RequestParam String token){
        HttpStatus status;
        List<Post> postList=null;
        if(as.authenticate(email,token)){
            postList=ps.getallpost(token);
            status=HttpStatus.OK;
        }else{
            status=HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Post>>(postList,status);
    }

    @PutMapping(value = "/update/{postid}/{data}")
    public ResponseEntity<String> updatepost(@PathVariable Long postid,@RequestParam String email,@RequestParam String token,@PathVariable String data){
        HttpStatus status;
        String message="";
        if(as.owner(token,postid)){
            ps.updatepost(postid,data);
            status=HttpStatus.OK;
            message="post updated successfully";
        }else{
            status=HttpStatus.FORBIDDEN;
            message="Cannot update..!, only owner can update.";
        }
        return new ResponseEntity<String>(message,status);
    }

    @DeleteMapping(value = "/delete/{postid}")
    public void deletepost(@PathVariable Long postid){
        ps.deletepost(postid);
    }
}
