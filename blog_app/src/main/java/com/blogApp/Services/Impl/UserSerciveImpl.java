package com.blogApp.Services.Impl;

import com.blogApp.Entities.User;
import com.blogApp.Exception.ResourceNotFoundException;
import com.blogApp.Payload.UserDTO;
import com.blogApp.Repository.UserRepo;
import com.blogApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserSerciveImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id",  userId));
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassward(userDTO.getPassward());
        User updatedUser = this.userRepo.save(user);
        UserDTO userDTO1 = this.userToDto(updatedUser);
        return userDTO1;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassward(userDTO.getPassward());
        user.setAbout(userDTO.getAbout());
        return user;
    }

    public UserDTO userToDto(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassward(user.getPassward());
        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
