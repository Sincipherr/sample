package com.geekster.Blogging.Platform.API.controller;

import com.geekster.Blogging.Platform.API.model.BlogComment;
import com.geekster.Blogging.Platform.API.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService cs;

    @PostMapping(value = "/comment")
    public String addcomment(@RequestBody BlogComment comment){
        return cs.addcomment(comment);
    }
}
