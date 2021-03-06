package com.monitor.rest;

import com.monitor.domain.User;
import com.monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserRest {

    @Autowired
    private UserService service;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping("/newUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody User userRest) {
        try {
            service.save(userRest);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String userName) {
        try {
            service.delete(userName);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
