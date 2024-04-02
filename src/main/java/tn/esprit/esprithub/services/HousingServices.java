package tn.esprit.esprithub.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprithub.entities.Housing;
import tn.esprit.esprithub.repositories.IHousingRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class HousingServices implements IHousingServices {



    private IHousingRepository housingRepository;


    @Override
    public Housing addHousing(Housing housing) {
        return housingRepository.save(housing);
    }

    @Override
    public void deleteHousing(Long idHousing) {
        housingRepository.deleteById(idHousing);

    }

    @Override
    public Housing updateHousing(Housing housing) {
        return housingRepository.save(housing);
    }

    @Override
    public Housing getById(Long idHousing) {
        return housingRepository.findById(idHousing).orElse(null);
    }

    @Override
    public List<Housing> getAll() {
        return (List<Housing>) housingRepository.findAll();
    }
}
