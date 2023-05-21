package com.geekster.Blogging.Platform.API.controller;

import com.geekster.Blogging.Platform.API.dto.SignInInput;
import com.geekster.Blogging.Platform.API.dto.SignInOutput;
import com.geekster.Blogging.Platform.API.dto.SignUpOutput;
import com.geekster.Blogging.Platform.API.model.User;
import com.geekster.Blogging.Platform.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @PostMapping(value = "/signup")
    public SignUpOutput signup(@RequestBody User signupdto){
        return us.signup(signupdto);
    }

    @PostMapping(value = "/signin")
    public SignInOutput signin(@RequestBody SignInInput signindto){
        return us.signin(signindto);
    }

    @PostMapping(value = "/follow/{myid}/{oid}")
    public String follow(@PathVariable Long myid,@PathVariable Long oid){
        return us.follow(myid,oid);
    }
}
