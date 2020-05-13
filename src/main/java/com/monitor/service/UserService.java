package com.monitor.service;

import com.monitor.business.UserBusiness;
import com.monitor.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserBusiness business;

    public List<User> getAll(){
        return business.getAll();
    }

    public void save(User user) {
        business.save(user);
    }

    public void delete(String username) {
        business.delete(username);
    }
}
