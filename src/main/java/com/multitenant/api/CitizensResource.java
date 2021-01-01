package com.multitenant.api;

import com.multitenant.entity.Citizen;
import com.multitenant.entity.CitizenRepository;
import com.multitenant.entity.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CitizensResource {

    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private PlanetRepository planetnRepository;

    @PostMapping("{planetName}/save")
    public Citizen save(@PathVariable String planetName, @RequestBody Citizen citizen) {
        if(planetIsPresent(planetName)) {
            return citizenRepository.save(citizen);
        }
        throw new RuntimeException("The planet does not exist, in shared DB run mongo command to generate entry: use shared; db.planet.insert({_id: \""+planetName+"\"});");
    }

    private boolean planetIsPresent(String planetName) {
        return planetnRepository.findById(planetName).isPresent();
    }

    @GetMapping("{planetName}/list")
    public List<Citizen> getCitizen(@PathVariable String planetName) {
        return citizenRepository.findAll();
    }
}
