package com.monitor.service;

import com.monitor.business.EquipamentBusiness;
import com.monitor.business.UserBusiness;
import com.monitor.domain.Equipament;
import com.monitor.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentService {

    @Autowired
    private EquipamentBusiness business;

    public List<Equipament> getAll(){
        return business.getAll();
    }

    public void save(Equipament equipament) {
        business.save(equipament);
    }

    public void delete(Equipament equipament) {
        business.delete(equipament);
    }
}
