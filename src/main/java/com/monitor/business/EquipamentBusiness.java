package com.monitor.business;

import com.monitor.domain.Equipament;
import com.monitor.domain.User;
import com.monitor.repository.EquipamentRepository;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipamentBusiness {

    @Autowired
    private EquipamentRepository repository;

    public List<Equipament> getAll() {
        for (int i = 1; i<=10; i++) {
            System.out.println("Create new equipament");
            Equipament equipament = new Equipament();
            equipament.setId(Long.valueOf(i));
            equipament.setName("Teste " + i);
            equipament.setTemperature(Double.valueOf(i));
            equipament.setLocation("Localização " + i);
            save(equipament);
        }
        return (List<Equipament>) repository.findAll();
    }

    public void save(Equipament equipament) {
        repository.save(equipament);
    }

    public void delete(Equipament equipament) {
        repository.delete( equipament);
    }
}
