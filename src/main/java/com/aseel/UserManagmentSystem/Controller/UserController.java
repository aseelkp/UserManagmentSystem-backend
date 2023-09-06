package com.aseel.UserManagmentSystem.Controller;


import com.aseel.UserManagmentSystem.Model.UserModel;
import com.aseel.UserManagmentSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public UserModel saveUser (@RequestBody UserModel user){
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable(value = "id") Long Id){

        UserModel user = null;
        user = userService.getUserById(Id);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String , Boolean>> deleteUser(@PathVariable(value = "id") Long Id){
        boolean deleted = false;
        deleted = userService.deleteUser(Id);
        Map<String , Boolean> response = new HashMap<>();
        response.put("deleted" , deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable(value = "id") Long Id , @RequestBody UserModel user){
        UserModel updatedUser = userService.updateUser(Id , user);
        return ResponseEntity.ok(updatedUser);
    }


}
