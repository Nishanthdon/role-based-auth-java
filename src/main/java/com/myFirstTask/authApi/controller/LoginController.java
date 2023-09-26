package com.myFirstTask.authApi.controller;

import com.myFirstTask.authApi.common.ApiResponse;
import com.myFirstTask.authApi.common.JWTUtils;
import com.myFirstTask.authApi.entity.LoginRequest;
import com.myFirstTask.authApi.entity.User;
import com.myFirstTask.authApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LoginController {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest){
        System.out.println("LoginApiTest");
        boolean hasUser = false;
        User currentUser = new User();

        List<User> userList = userService.getUsers();

        for (int i = 0; i < userList.toArray().length; i++) {
            if(userList.get(i).getEmail().equalsIgnoreCase(loginRequest.getEmail()) && userList.get(i).getPassword().equalsIgnoreCase(loginRequest.getPassword())){
                hasUser = true;
                currentUser = userList.get(i);
            }
        }

        Map<String , Object> data = new HashMap<>();
        data.put("login", hasUser);
        if(hasUser){
            String token = jwtUtils.generateJwt(currentUser);
            data.put("token", token);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);

        return ResponseEntity.status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
