package com.multitenant.resources;

import com.multitenant.entity.Citizen;
import com.multitenant.entity.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CitizensResource {

    @Autowired
    private CitizenRepository citizenRepository;

    @PostMapping("{planetName}/save")
    public Citizen save(@PathVariable String planetName, @RequestBody Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @GetMapping("{planetName}/list")
    public List<Citizen> getCitizen(@PathVariable String planetName) {
        return citizenRepository.findAll();
    }
}
