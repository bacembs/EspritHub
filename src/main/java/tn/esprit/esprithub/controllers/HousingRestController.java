package tn.esprit.esprithub.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Housing;
import tn.esprit.esprithub.services.IHousingServices;

import java.util.List;
@AllArgsConstructor
@RequestMapping("/Housing")
@RestController
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
    @GetMapping("/get/{idHousing}")
    public Housing getHousing(@PathVariable Long idHousing){
        return HousingServices.getById(idHousing);
    }
    @DeleteMapping("/delete/{idHousing}")
    public void removeHousing(@PathVariable Long idHousing){
        HousingServices.deleteHousing(idHousing);
    }

    @GetMapping("/all")
    public List<Housing> getAll(){
        return HousingServices.getAll();
    }
}
