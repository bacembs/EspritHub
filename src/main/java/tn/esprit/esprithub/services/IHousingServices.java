package tn.esprit.esprithub.services;

import tn.esprit.esprithub.entities.Housing;

import java.util.List;

public interface IHousingServices {
    Housing addHousing(Housing housing);
    void deleteHousing(Long idHousing);
    Housing updateHousing(Housing housing);
    Housing getById(Long idHousing);
    List<Housing> getAll();
}
