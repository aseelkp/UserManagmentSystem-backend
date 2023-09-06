package com.aseel.UserManagmentSystem.Service;

import com.aseel.UserManagmentSystem.Model.UserModel;

import java.util.List;

public interface UserService {
    UserModel saveUser(UserModel user);

    List<UserModel> getAllUsers();

    UserModel getUserById(Long id);

    boolean deleteUser(Long id);

    UserModel updateUser(Long id, UserModel user);
}
