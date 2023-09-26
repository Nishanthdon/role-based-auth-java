package com.myFirstTask.authApi.controller;

import com.myFirstTask.authApi.common.ApiResponse;
import com.myFirstTask.authApi.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @GetMapping(value = "/admin/test")
    public ResponseEntity checkAdmin(){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setData("It is Admin");

        return  ResponseEntity.status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
