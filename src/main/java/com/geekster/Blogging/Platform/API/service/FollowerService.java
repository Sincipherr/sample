package com.geekster.Blogging.Platform.API.service;

import com.geekster.Blogging.Platform.API.Repository.IFollowerRepo;
import com.geekster.Blogging.Platform.API.model.Follower;
import com.geekster.Blogging.Platform.API.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {
    @Autowired
    IFollowerRepo fr;

    public void saveFollower(User otheruser, User myuser) {
        Follower followerrecord=new Follower(null,otheruser,myuser);
        fr.save(followerrecord);
    }
}
