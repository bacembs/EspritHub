package tn.esprit.esprithub.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprithub.entities.Housing;
import tn.esprit.esprithub.repository.IHousingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HousingServices implements IHousingServices {
    final private IHousingRepository housingrepo;
    @Override
    public List<Housing> getAll() {
        return (List<Housing>) housingrepo.findAll();
    }
}
