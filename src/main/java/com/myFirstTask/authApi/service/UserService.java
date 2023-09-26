package com.myFirstTask.authApi.service;

import com.myFirstTask.authApi.entity.User;
import com.myFirstTask.authApi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUserById(int userId){
        System.out.println("FindUser : "+userRepo.findById(userId));
        return userRepo.findById(userId);
    }

    public User getUserByUserName(String userName){

        User currentUser = new User();
        List<User> userList = getUsers();

        for (int i = 0; i < userList.toArray().length; i++) {
            if(userList.get(i).getName().equalsIgnoreCase(userName)){
                currentUser = userList.get(i);
            }
        }
        return currentUser;
    }

}
