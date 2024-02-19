package ru.tinkoff.edu.java.t2_seminar2.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.t2_seminar2.model.UserList;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserList getAllUsers() {
        return new UserList().users(Collections.emptyList());
    }
}
