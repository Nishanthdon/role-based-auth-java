package com.myFirstTask.authApi.repo;

import com.myFirstTask.authApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

//    public List<User> getLocalUsers() {
//
//        List<User> userList = Arrays.asList(
//                new User(101, "Nishanth", "nishanth@gmail.com", "12345", new Date()),
//                new User(101, "Arun", "arun@gmail.com", "67890", new Date()),
//                new User(101, "Anand", "anand@gmail.com", "11111", new Date())
//        );
//
//        return userList;
//    }

//    User findByUserName(String userName);

}
