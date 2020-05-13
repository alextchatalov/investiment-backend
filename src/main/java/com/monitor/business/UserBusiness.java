package com.monitor.business;

import com.monitor.domain.User;
import com.monitor.repository.UserRepository;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBusiness {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    public void save(User User) {
        repository.save(User);
    }

    public void delete(String UserName) {
        User User = repository.findByUserName(UserName);
        repository.delete( User);
    }
}
