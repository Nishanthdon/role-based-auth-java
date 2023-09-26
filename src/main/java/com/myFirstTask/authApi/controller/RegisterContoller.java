package com.myFirstTask.authApi.controller;

import com.myFirstTask.authApi.common.ApiResponse;
import com.myFirstTask.authApi.entity.RegisterRequest;
import com.myFirstTask.authApi.entity.User;
import com.myFirstTask.authApi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.Name;
import java.util.Date;

@RestController
public class RegisterContoller {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest registerRequest){

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        Date tempDOB = registerRequest.getDOB();
        if(tempDOB == null){
            tempDOB = new Date();
        }
        user.setDOB(tempDOB);

        userRepo.save(user);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData("Registered Successfully " + registerRequest.getName());
        return ResponseEntity.status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
