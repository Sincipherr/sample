package com.geekster.Blogging.Platform.API.service;

import com.geekster.Blogging.Platform.API.Repository.IUserRepo;
import com.geekster.Blogging.Platform.API.dto.SignInInput;
import com.geekster.Blogging.Platform.API.dto.SignInOutput;
import com.geekster.Blogging.Platform.API.dto.SignUpOutput;
import com.geekster.Blogging.Platform.API.model.AuthenticationToken;
import com.geekster.Blogging.Platform.API.model.User;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo ur;

    @Autowired
    AuthenticationService auths;

    @Autowired
    FollowingService fings;

    @Autowired
    FollowerService fs;

    public SignUpOutput signup(User signupdto) {
        User user=ur.findFirstByUserEmail(signupdto.getUserEmail());
        if(user!=null){
            throw new IllegalStateException("User already exist..Sign in instead..!");
        }
        String encryptedpassword=null;
        try {
            encryptedpassword=encryptedpassword(signupdto.getUserPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        signupdto.setUserPassword(encryptedpassword);
        ur.save(signupdto);
        return new SignUpOutput("User created","Signed up successfully..!");

    }

    private String encryptedpassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested=md5.digest();
        String hash= DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signin(SignInInput signindto) {
        User user=ur.findFirstByUserEmail(signindto.getUserEmail());
        if(user==null){
            throw new IllegalStateException("Invalid user..Sign up instead..");
        }
        String encryptedpassword=null;
        try {
            encryptedpassword=encryptedpassword(signindto.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        boolean isvalid=encryptedpassword.equals(user.getUserPassword());
        if(!isvalid){
            throw new IllegalStateException("signup instead..!");
        }
        AuthenticationToken token=new AuthenticationToken(user);
        //generating token and save it
        auths.savetoken(token);
        AuthenticationToken auth=auths.gettoken(user);
        return new SignInOutput("User Signed in successfully",auth.getToken());
    }

    public String follow(Long myid, Long oid) {
        if(myid==oid){
            return "Cannot follow yourself";
        }
        User myuser= ur.findByUserId(myid);
        User otheruser=ur.findByUserId(oid);

        if(myuser!=null && otheruser!=null){
            fings.savefollowing(myuser,otheruser);
            fs.saveFollower(myuser,otheruser);
            return "Followed successfully";
        }else{
            return "User are invalid...!";
        }
    }
}
