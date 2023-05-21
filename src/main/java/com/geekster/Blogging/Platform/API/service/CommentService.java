package com.geekster.Blogging.Platform.API.service;

import com.geekster.Blogging.Platform.API.Repository.ICommentRepo;
import com.geekster.Blogging.Platform.API.model.BlogComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    ICommentRepo cr;

    public String addcomment(BlogComment comment) {
        BlogComment bcomment=cr.save(comment);
        if(bcomment==null){
            return "Comment is not saved";
        }else{
            return "comment saved successfully..!";
        }
    }
}
