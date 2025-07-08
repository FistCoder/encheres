package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.dal.EnchereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {
    private EnchereDAO enchereDAO;

    public EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

    @Override
    public List<Enchere> listerEncheres() {
        List<Enchere> encheres = enchereDAO.findAll();
        //Retourner enchere si pas vide sinon retourner une liste vide
        return encheres != null ? encheres : new ArrayList<>();
    }
}