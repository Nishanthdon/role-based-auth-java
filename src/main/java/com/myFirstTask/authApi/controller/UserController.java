package com.myFirstTask.authApi.controller;

import com.myFirstTask.authApi.common.ApiResponse;
import com.myFirstTask.authApi.common.JWTUtils;
import com.myFirstTask.authApi.entity.User;
import com.myFirstTask.authApi.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/user/getUserList")
    public ResponseEntity<ApiResponse> getUsers() {
        System.out.println("homeTest");

        ApiResponse apiResponse = new ApiResponse();

        Map<String , Object> data = new HashMap<>();
        List<User> userList = userService.getUsers();
        data.put("userList", userList);

        apiResponse.setData(data);

        return  ResponseEntity.status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
