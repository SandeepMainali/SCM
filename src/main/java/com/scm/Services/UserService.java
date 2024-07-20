package com.scm.Services;

import com.scm.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userid);
    boolean isUserExitByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);


}
