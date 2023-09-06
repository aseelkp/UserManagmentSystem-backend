package com.aseel.UserManagmentSystem.Service;

import com.aseel.UserManagmentSystem.Entity.UserEntity;
import com.aseel.UserManagmentSystem.Model.UserModel;
import com.aseel.UserManagmentSystem.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user , userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List <UserModel> users = userEntities.stream()
                .map(userEntity -> new UserModel(userEntity.getId() ,
                        userEntity.getFirstName() ,
                        userEntity.getLastName() ,
                        userEntity.getEmail() )
                ).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserModel getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        UserModel user = new UserModel();
        BeanUtils.copyProperties(userEntity , user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {

        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;

    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        return user;
    }


}
