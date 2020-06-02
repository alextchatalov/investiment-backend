package com.monitor.rest;

import com.monitor.domain.Equipament;
import com.monitor.domain.User;
import com.monitor.service.EquipamentService;
import com.monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/equipament")
public class EquipamentRest {

    @Autowired
    private EquipamentService service;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Equipament> getAll() {
        return service.getAll();
    }

    @PostMapping("/newUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody Equipament equipamentRest) {
        try {
            service.save(equipamentRest);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{equipament}")
    public void deleteUser(@PathVariable Equipament equipament) {
        try {
            service.delete(equipament);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
