package ru.tinkoff.edu.java.t2_seminar2.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.t2_seminar2.dto.UserList;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserList getAllUsers() {
        return new UserList(List.of());
    }
}
