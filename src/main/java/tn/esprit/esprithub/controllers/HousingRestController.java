package tn.esprit.esprithub.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Housing;
import tn.esprit.esprithub.services.IHousingServices;

import java.util.List;

public class HousingRestController {
    private IHousingServices HousingServices;
    @PostMapping("/add")
    public Housing addHousing(@RequestBody Housing housing){
        return HousingServices.addHousing(housing);
    }
    @PutMapping("/update")
    public Housing updateHousing(@RequestBody Housing housing){
        return HousingServices.updateHousing(housing);
    }
    @GetMapping("/get/{numHousing}")
    public Housing getHousing(@PathVariable Long numHousing){
        return HousingServices.getById(numHousing);
    }
    @DeleteMapping("/delete/{numHousing}")
    public void removeHousing(@PathVariable Long numHousing){
        HousingServices.deleteHousing(numHousing);
    }

    @GetMapping("/all")
    public List<Housing> getAll(){
        return HousingServices.getAll();
    }
}
